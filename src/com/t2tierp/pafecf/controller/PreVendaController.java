/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Classe de controle da pre-venda.</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM</p>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 *       The author may be contacted at:
 *           t2ti.com@gmail.com</p>
 *
 * @author Albert Eije (T2Ti.COM)
 * @version 1.0
 */
package com.t2tierp.pafecf.controller;

import com.t2tierp.pafecf.bd.AcessoBanco;
import com.t2tierp.pafecf.infra.Ecf;
import com.t2tierp.pafecf.vo.PreVendaCabecalhoVO;
import com.t2tierp.pafecf.vo.PreVendaDetalheVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PreVendaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public List<PreVendaDetalheVO> carregaPreVenda(Integer pId) {
        try {
            //verifica se existe a pre-venda solicitada
            consultaSQL = "select count(*) as TOTAL from ECF_PRE_VENDA_CABECALHO where SITUACAO <> 'E' and SITUACAO <> 'M' and ID=" + pId;
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                //verifica se existem itens para a pre-venda
                consultaSQL = "select count(*) as TOTAL from ECF_PRE_VENDA_DETALHE where ID_ECF_PRE_VENDA_CABECALHO=" + pId;
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);

                rs.first();
                totalRegistros = rs.getInt("TOTAL");

                //caso existam itens no detalhe
                if (totalRegistros > 0) {
                    List<PreVendaDetalheVO> listaPV = new ArrayList<PreVendaDetalheVO>();
                    consultaSQL = "select * from ECF_PRE_VENDA_DETALHE where ID_ECF_PRE_VENDA_CABECALHO=" + pId;
                    stm = bd.conectar().createStatement();
                    rs = stm.executeQuery(consultaSQL);

                    rs.beforeFirst();
                    while (rs.next()) {
                        PreVendaDetalheVO preVendaDetalhe = new PreVendaDetalheVO();
                        preVendaDetalhe.setId(rs.getInt("ID"));
                        preVendaDetalhe.setIdPreVendaCabecalho(pId);
                        preVendaDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                        preVendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                        preVendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                        preVendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                        listaPV.add(preVendaDetalhe);
                    }
                    return listaPV;
                } else {
                    //caso nao existam registros retorna nulo
                    return null;
                }
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void fechaPreVenda(Integer pId, Integer pCCF) {
        consultaSQL =
                "update ECF_PRE_VENDA_CABECALHO set "
                + "SITUACAO=?, "
                + "CCF=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, "E");
            pstm.setInt(2, pCCF);
            pstm.setInt(3, pId);
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    //TODO : O que está faltando para completar essa rotina mesclaPreVenda?
    public Integer mesclaPreVenda(List<PreVendaCabecalhoVO> pListaPreVendaCabecalho) {
        //inicia e configura a nova Pre-Venda
        PreVendaCabecalhoVO novaPreVenda = new PreVendaCabecalhoVO();
        java.util.Date data = new java.util.Date();
        java.sql.Timestamp hoje = new java.sql.Timestamp(data.getTime());
        novaPreVenda.setDataHoraEmissao(hoje);
        novaPreVenda.setSituacao("P");
        //atualiza a tabela de cabecalho
        for (int i = 0; i < pListaPreVendaCabecalho.size() - 1; i++) {
            //altera a situacao da PV selecionada para M de mesclada
            consultaSQL =
                    "update ECF_PRE_VENDA_CABECALHO set "
                    + "SITUACAO=? "
                    + " where ID = ?";

            try {
                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setString(1, "M");
                pstm.setInt(2, pListaPreVendaCabecalho.get(i).getId());
                pstm.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bd.desconectar();
            }
        }

        //cria uma nova PV
        consultaSQL =
                "insert into ECF_PRE_VENDA_CABECALHO ("
                + "DATA_HORA_PV,"
                + "SITUACAO) values ("
                + "?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setTimestamp(1, novaPreVenda.getDataHoraEmissao());
            pstm.setString(2, novaPreVenda.getSituacao());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from ECF_PRE_VENDA_CABECALHO");
                rs.first();
                novaPreVenda.setId(rs.getInt("ID"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }

        try {
            //atualiza a tabela de detalhes
            List<PreVendaDetalheVO> listaPreVendaDetalhe = new ArrayList<PreVendaDetalheVO>();
            String insiraDetalhe =
                    "insert into ECF_PRE_VENDA_DETALHE ("
                    + "ID_PRODUTO,"
                    + "ID_ECF_PRE_VENDA_CABECALHO,"
                    + "QUANTIDADE,"
                    + "VALOR_UNITARIO,"
                    + "VALOR_TOTAL) values ("
                    + "?,?,?,?,?)";
            for (int j = 0; j < pListaPreVendaCabecalho.size(); j++) {
                if (pListaPreVendaCabecalho.get(j).getSelecao() != null) {
                    listaPreVendaDetalhe = listaPreVendaDetalhePendente(pListaPreVendaCabecalho.get(j).getId());
                    if (listaPreVendaDetalhe != null) {
                        for (int i = 0; i < listaPreVendaDetalhe.size(); i++) {
                            pstm = bd.conectar().prepareStatement(insiraDetalhe);
                            pstm.setInt(1, listaPreVendaDetalhe.get(i).getIdProduto());
                            pstm.setInt(2, novaPreVenda.getId());
                            pstm.setDouble(3, listaPreVendaDetalhe.get(i).getQuantidade());
                            pstm.setDouble(4, listaPreVendaDetalhe.get(i).getValorUnitario());
                            pstm.setDouble(5, listaPreVendaDetalhe.get(i).getValorTotal());
                            pstm.executeUpdate();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return novaPreVenda.getId();
    }

    public void cancelaPreVendasPendentes() {
        //verifica se existem PV pendentes
        consultaSQL =
                "select count(*) as TOTAL from ECF_PRE_VENDA_CABECALHO where SITUACAO = 'P'";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            //caso existam PV pendentes emite o cupom fiscal e cancela posteriormente
            if (totalRegistros > 0) {
                consultaSQL =
                        "select * from ECF_PRE_VENDA_CABECALHO where SITUACAO = 'P'";
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    String mensagem = "PV" + rs.getInt("ID");
                    Ecf.abreCupom("");
                    //TODO : É necessário imprimir os itens da pre-venda?
                    /*
                    Ecf.vendeItem("1", "CANCELAMENTO DE PRE-VENDA", "NN", 1, 1);
                    Ecf.efetuaPagamento("01",1);
                    Ecf.fechaCupom(mensagem);
                    Ecf.CancelaCupom;
                     */
                }
            }

            //atualiza situacao no banco de dados
            consultaSQL =
                    "update ECF_PRE_VENDA_CABECALHO set "
                    + "SITUACAO='C' "
                    + " where situacao = 'P'";
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public List<PreVendaCabecalhoVO> listaPreVendaPendente() {
        consultaSQL =
                "select count(*) AS TOTAL from ECF_PRE_VENDA_CABECALHO where SITUACAO = 'P'";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<PreVendaCabecalhoVO> listaPreVenda = new ArrayList<PreVendaCabecalhoVO>();
                consultaSQL =
                        "select * from ECF_PRE_VENDA_CABECALHO where SITUACAO = 'P'  ORDER BY ID";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    PreVendaCabecalhoVO PreVendaCabecalho = new PreVendaCabecalhoVO();
                    PreVendaCabecalho.setId(rs.getInt("ID"));
                    PreVendaCabecalho.setDataHoraEmissao(rs.getTimestamp("DATA_HORA_PV"));
                    PreVendaCabecalho.setValor(rs.getDouble("VALOR"));
                    listaPreVenda.add(PreVendaCabecalho);
                }
                return listaPreVenda;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public List<PreVendaDetalheVO> listaPreVendaDetalhePendente(Integer IdPreVendaCabecalho) {
        consultaSQL =
                "SELECT count(*) as TOTAL FROM ECF_PRE_VENDA_DETALHE D JOIN PRODUTO P "
                + "ON D.ID_PRODUTO=P.ID where ID_ECF_PRE_VENDA_CABECALHO=" + IdPreVendaCabecalho;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<PreVendaDetalheVO> listaPreVendaDetalhe = new ArrayList<PreVendaDetalheVO>();
                consultaSQL =
                        "SELECT "
                        + "D.ID,D.ID_PRODUTO,D.ID_ECF_PRE_VENDA_CABECALHO,D.QUANTIDADE, "
                        + "D.VALOR_UNITARIO, D.VALOR_TOTAL, P.DESCRICAO_PDV "
                        + "FROM ECF_PRE_VENDA_DETALHE D JOIN PRODUTO P "
                        + "ON D.ID_PRODUTO=P.ID "
                        + "where ID_ECF_PRE_VENDA_CABECALHO=" + IdPreVendaCabecalho;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    PreVendaDetalheVO PreVendaDetalhe = new PreVendaDetalheVO();
                    PreVendaDetalhe.setIdPreVendaCabecalho(rs.getInt("ID_ECF_PRE_VENDA_CABECALHO"));
                    PreVendaDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    PreVendaDetalhe.setDescricaoProduto(rs.getString("DESCRICAO_PDV"));
                    PreVendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    PreVendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    PreVendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    listaPreVendaDetalhe.add(PreVendaDetalhe);
                }
                return listaPreVendaDetalhe;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

}
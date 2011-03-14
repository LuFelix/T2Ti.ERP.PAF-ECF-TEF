/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Classe de controle do DAV.</p>
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
import com.t2tierp.pafecf.vo.DAVCabecalhoVO;
import com.t2tierp.pafecf.vo.DAVDetalheVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAVController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();
    java.util.Date d = new java.util.Date();

    public List<DAVDetalheVO> carregaDAV(Integer pId) {
        try {
            //verifica se existe o DAV solicitado
            consultaSQL = "select count(*) as TOTAL from ECF_DAV_CABECALHO where SITUACAO <> 'E' and SITUACAO <> 'M' and ID=" + pId;
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                //verifica se existem itens para o DAV
                consultaSQL = "select count(*) as TOTAL from ECF_DAV_DETALHE where ID_ECF_DAV=" + pId;
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);

                rs.first();
                totalRegistros = rs.getInt("TOTAL");

                //caso existam itens no detalhe
                if (totalRegistros > 0) {
                    List<DAVDetalheVO> listaDAV = new ArrayList<DAVDetalheVO>();
                    consultaSQL = "select * from ECF_DAV_DETALHE where ID_ECF_DAV=" + pId;
                    stm = bd.conectar().createStatement();
                    rs = stm.executeQuery(consultaSQL);

                    rs.beforeFirst();
                    while (rs.next()) {
                        DAVDetalheVO DAVDetalhe = new DAVDetalheVO();
                        DAVDetalhe.setId(rs.getInt("ID"));
                        DAVDetalhe.setIdDAVCabecalho(pId);
                        DAVDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                        DAVDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                        DAVDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                        DAVDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                        listaDAV.add(DAVDetalhe);
                    }
                    return listaDAV;
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

    public void fechaDAV(Integer pId, Integer pCCF, Integer pCOO) {
        consultaSQL =
                "update ECF_DAV_CABECALHO set "
                + "SITUACAO=?, "
                + "CCF=?, "
                + "COO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, "E");
            pstm.setInt(2, pCCF);
            pstm.setInt(3, pCOO);
            pstm.setInt(4, pId);
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    //TODO : O que está faltando para completar essa rotina mesclaDAV?
    public Integer mesclaDAV(List<DAVCabecalhoVO> pListaDAVCabecalho) {
        //inicia e configura o novo DAV
        DAVCabecalhoVO novoDAV = new DAVCabecalhoVO();
        java.util.Date data = new java.util.Date();
        java.sql.Timestamp hoje = new java.sql.Timestamp(data.getTime());
        novoDAV.setDataHoraEmissao(hoje);
        novoDAV.setSituacao("P");
        novoDAV.setNomeDestinatario(pListaDAVCabecalho.get(0).getNomeDestinatario());
        novoDAV.setCpfCnpjDestinatario(pListaDAVCabecalho.get(0).getCpfCnpjDestinatario());

        //atualiza a tabela de cabecalho
        for (int i = 0; i < pListaDAVCabecalho.size() - 1; i++) {
            if (pListaDAVCabecalho.get(i).getSelecao() != null) {
                //altera a situacao do DAV selecionado para M de mesclado
                consultaSQL =
                        "update ECF_DAV_CABECALHO set "
                        + "SITUACAO=? "
                        + " where ID = ?";

                try {
                    pstm = bd.conectar().prepareStatement(consultaSQL);
                    pstm.setString(1, "M");
                    pstm.setInt(2, pListaDAVCabecalho.get(i).getId());
                    pstm.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    bd.desconectar();
                }
            }
        }

        //cria um novo DAV
        consultaSQL =
                "insert into ECF_DAV_CABECALHO ("
                + "NOME_DESTINATARIO,"
                + "CPF_CNPJ_DESTINATARIO,"
                + "DATA_HORA_EMISSAO,"
                + "SITUACAO) values ("
                + "?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setString(1, novoDAV.getNomeDestinatario());
            pstm.setString(2, novoDAV.getCpfCnpjDestinatario());
            pstm.setTimestamp(3, novoDAV.getDataHoraEmissao());
            pstm.setString(4, novoDAV.getSituacao());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from ECF_DAV_CABECALHO");
                rs.first();
                novoDAV.setId(rs.getInt("ID"));
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
            List<DAVDetalheVO> listaDAVDetalhe = new ArrayList<DAVDetalheVO>();
            String insiraDetalhe =
                    "insert into ECF_DAV_DETALHE ("
                    + "ID_PRODUTO,"
                    + "ID_ECF_DAV,"
                    + "QUANTIDADE,"
                    + "VALOR_UNITARIO,"
                    + "VALOR_TOTAL) values ("
                    + "?,?,?,?,?)";
            for (int j = 0; j < pListaDAVCabecalho.size(); j++) {
                if (pListaDAVCabecalho.get(j).getSelecao() != null) {
                    listaDAVDetalhe = listaDAVDetalhePendente(pListaDAVCabecalho.get(j).getId());
                    if (listaDAVDetalhe != null) {
                        for (int i = 0; i < listaDAVDetalhe.size(); i++) {
                            pstm = bd.conectar().prepareStatement(insiraDetalhe);
                            pstm.setInt(1, listaDAVDetalhe.get(i).getIdProduto());
                            pstm.setInt(2, novoDAV.getId());
                            pstm.setDouble(3, listaDAVDetalhe.get(i).getQuantidade());
                            pstm.setDouble(4, listaDAVDetalhe.get(i).getValorUnitario());
                            pstm.setDouble(5, listaDAVDetalhe.get(i).getValorTotal());
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

        return novoDAV.getId();
    }

    public List<DAVCabecalhoVO> listaDAVPeriodo(String pDataInicio, String pDataFim) {
        consultaSQL =
                "select count(*) AS TOTAL from ECF_DAV_CABECALHO where SITUACAO = 'E' and DATA_HORA_EMISSAO between '"
                + pDataInicio + "' and '" + pDataFim + "'";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVCabecalhoVO> listaDAV = new ArrayList<DAVCabecalhoVO>();
                consultaSQL =
                        "select * from ECF_DAV_CABECALHO where SITUACAO = 'E' and DATA_HORA_EMISSAO between '"
                        + pDataInicio + "' and '" + pDataFim + "'";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    DAVCabecalhoVO DAVCabecalho = new DAVCabecalhoVO();
                    DAVCabecalho.setId(rs.getInt("ID"));
                    DAVCabecalho.setDataHoraEmissao(rs.getTimestamp("DATA_HORA_EMISSAO"));
                    DAVCabecalho.setValor(rs.getDouble("VALOR"));
                    listaDAV.add(DAVCabecalho);
                }
                return listaDAV;
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

    public List<DAVCabecalhoVO> listaDAVPendente() {
        consultaSQL =
                "select count(*) AS TOTAL from ECF_DAV_CABECALHO where SITUACAO = 'P'";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVCabecalhoVO> listaDAV = new ArrayList<DAVCabecalhoVO>();
                consultaSQL =
                        "select * from ECF_DAV_CABECALHO where SITUACAO = 'P'  ORDER BY ID";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    DAVCabecalhoVO DAVCabecalho = new DAVCabecalhoVO();
                    DAVCabecalho.setId(rs.getInt("ID"));
                    DAVCabecalho.setNomeDestinatario(rs.getString("NOME_DESTINATARIO"));
                    DAVCabecalho.setCpfCnpjDestinatario(rs.getString("CPF_CNPJ_DESTINATARIO"));
                    DAVCabecalho.setDataHoraEmissao(rs.getTimestamp("DATA_HORA_EMISSAO"));
                    DAVCabecalho.setValor(rs.getDouble("VALOR"));
                    listaDAV.add(DAVCabecalho);
                }
                return listaDAV;
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

    public List<DAVDetalheVO> listaDAVDetalhePendente(Integer IdDAVCabecalho) {
        consultaSQL =
                "SELECT count(*) as TOTAL FROM ECF_DAV_DETALHE D JOIN PRODUTO P "
                + "ON D.ID_PRODUTO=P.ID where id_ecf_dav=" + IdDAVCabecalho;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<DAVDetalheVO> listaDAVDetalhe = new ArrayList<DAVDetalheVO>();
                consultaSQL =
                        "SELECT "
                        + "D.ID,D.ID_PRODUTO,D.ID_ECF_DAV,D.QUANTIDADE, "
                        + "D.VALOR_UNITARIO, D.VALOR_TOTAL, P.DESCRICAO_PDV "
                        + "FROM ECF_DAV_DETALHE D JOIN PRODUTO P "
                        + "ON D.ID_PRODUTO=P.ID "
                        + "where id_ecf_dav=" + IdDAVCabecalho;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    DAVDetalheVO DAVDetalhe = new DAVDetalheVO();
                    DAVDetalhe.setIdDAVCabecalho(rs.getInt("ID_ECF_DAV"));
                    DAVDetalhe.setIdProduto(rs.getInt("ID_PRODUTO"));
                    DAVDetalhe.setDescricaoProduto(rs.getString("DESCRICAO_PDV"));
                    DAVDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    DAVDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    DAVDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    listaDAVDetalhe.add(DAVDetalhe);
                }
                return listaDAVDetalhe;
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

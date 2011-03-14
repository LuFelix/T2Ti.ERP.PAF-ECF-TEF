/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Regras de negócio/persistência da Venda.</p>
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
import com.t2tierp.pafecf.vo.VendaCabecalhoVO;
import com.t2tierp.pafecf.vo.VendaDetalheVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public VendaCabecalhoVO iniciaVenda(VendaCabecalhoVO pVendaCabecalho) {
        consultaSQL =
                "insert into ECF_VENDA_CABECALHO ("
                + "STATUS_VENDA,"
                + "ID_ECF_MOVIMENTO,"
                + "ID_CFOP,"
                + "COO,"
                + "CCF,"
                + "ID_CLIENTE,"
                + "NOME_CLIENTE,"
                + "CPF_CNPJ_CLIENTE,"
                + "DATA_HORA_VENDA) values ("
                + "?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, pVendaCabecalho.getStatusVenda());
            pstm.setInt(2, pVendaCabecalho.getIdMovimento());
            pstm.setInt(3, pVendaCabecalho.getIdCFOP());
            pstm.setInt(4, pVendaCabecalho.getCOO());
            pstm.setInt(5, pVendaCabecalho.getCCF());
            if (pVendaCabecalho.getIdCliente() != null) {
                pstm.setInt(6, pVendaCabecalho.getIdCliente());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }
            if (pVendaCabecalho.getNomeCliente() != null) {
                pstm.setString(7, pVendaCabecalho.getNomeCliente());
            } else {
                pstm.setNull(7, java.sql.Types.VARCHAR);
            }
            if (pVendaCabecalho.getCPFouCNPJCliente() != null) {
                pstm.setString(8, pVendaCabecalho.getCPFouCNPJCliente());
            } else {
                pstm.setNull(8, java.sql.Types.VARCHAR);
            }
            pstm.setTimestamp(9, pVendaCabecalho.getDataHoraVenda());
            pstm.executeUpdate();

            try {
                stm = bd.conectar().createStatement();
                rs = stm.executeQuery("select max(ID) as ID from ECF_VENDA_CABECALHO");
                rs.first();
                pVendaCabecalho.setId(rs.getInt("ID"));
                return pVendaCabecalho;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public VendaDetalheVO inserirItem(VendaDetalheVO pVendaDetalhe) {

        consultaSQL =
                "insert into ECF_VENDA_DETALHE ("
                + "ID_CFOP,"
                + "ID_ECF_TRIBUTO_PRODUTO,"
                + "ID_ECF_PRODUTO,"
                + "ID_ECF_VENDA_CABECALHO,"
                + "ITEM,"
                + "QUANTIDADE,"
                + "VALOR_UNITARIO,"
                + "VALOR_TOTAL,"
                + "TOTALIZADOR_PARCIAL,"
                + "CST,"
                + "MOVIMENTA_ESTOQUE) values ("
                + "?,?,?,?,?,?,?,?,?,?,?)";

        //TODO : Como se calcula a base e o valor do ICMS?
        //TODO : Como é feito o calculo dos demais impostos?
        //TODO : Devemos ratear o desconto para os itens? Como fazer isso?

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, pVendaDetalhe.getIdCFOP());
            pstm.setInt(2, pVendaDetalhe.getIdTributo());
            pstm.setInt(3, pVendaDetalhe.getIdProduto());
            pstm.setInt(4, pVendaDetalhe.getIdVendaCabecalho());
            pstm.setInt(5, pVendaDetalhe.getItem());
            pstm.setDouble(6, pVendaDetalhe.getQuantidade());
            pstm.setDouble(7, pVendaDetalhe.getValorUnitario());
            pstm.setDouble(8, pVendaDetalhe.getValorTotal());
            pstm.setString(9, pVendaDetalhe.getTotalizadorParcial());
            pstm.setString(10, pVendaDetalhe.getCST());
            pstm.setString(11, pVendaDetalhe.getMovimentaEstoque());
            pstm.executeUpdate();
            return pVendaDetalhe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void encerraVenda(VendaCabecalhoVO pVendaCabecalho) {
        //TODO : Como se calcula a base e o valor do ICMS?
        consultaSQL =
                "update ECF_VENDA_CABECALHO set "
                + "VALOR_VENDA=?, "
                + "TOTAL_PRODUTOS=?, "
                + "TOTAL_DOCUMENTO=?, "
                + "BASE_ICMS=?, "
                + "VALOR_FINAL=?, "
                + "VALOR_RECEBIDO=?, "
                + "TAXA_DESCONTO=?, "
                + "DESCONTO=?, "
                + "TAXA_ACRESCIMO=?, "
                + "ACRESCIMO=?, "
                + "TROCO=?, "
                + "ID_ECF_DAV=?, "
                + "ID_ECF_PRE_VENDA_CABECALHO=?, "
                + "STATUS_VENDA=?, "
                + "ID_ECF_FUNCIONARIO=? "
                + " where ID = ?";

        //TODO : Como é feito o calculo dos demais impostos?
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setDouble(1, pVendaCabecalho.getValorVenda());
            pstm.setDouble(2, pVendaCabecalho.getTotalProdutos());
            pstm.setDouble(3, pVendaCabecalho.getTotalDocumento());
            pstm.setDouble(4, pVendaCabecalho.getBaseICMS());
            pstm.setDouble(5, pVendaCabecalho.getValorFinal());
            pstm.setDouble(6, pVendaCabecalho.getValorRecebido());
            pstm.setDouble(7, pVendaCabecalho.getTaxaDesconto());
            pstm.setDouble(8, pVendaCabecalho.getDesconto());
            pstm.setDouble(9, pVendaCabecalho.getTaxaAcrescimo());
            pstm.setDouble(10, pVendaCabecalho.getAcrescimo());
            pstm.setDouble(11, pVendaCabecalho.getTroco());
            if (pVendaCabecalho.getIdDAV() != null) {
                pstm.setInt(12, pVendaCabecalho.getIdDAV());
                DAVController DAVControl = new DAVController();
                DAVControl.fechaDAV(pVendaCabecalho.getIdDAV(), pVendaCabecalho.getCCF(), pVendaCabecalho.getCOO());
            } else {
                pstm.setNull(12, java.sql.Types.INTEGER);
            }
            if (pVendaCabecalho.getIdPreVenda() != null) {
                pstm.setInt(13, pVendaCabecalho.getIdPreVenda());
                PreVendaController preVendaControl = new PreVendaController();
                preVendaControl.fechaPreVenda(pVendaCabecalho.getIdPreVenda(), pVendaCabecalho.getCCF());
            } else {
                pstm.setNull(13, java.sql.Types.INTEGER);
            }
            pstm.setString(14, "F");
            if (pVendaCabecalho.getIdVendedor() != null) {
                pstm.setInt(15, pVendaCabecalho.getIdVendedor());
            } else {
                pstm.setNull(15, java.sql.Types.INTEGER);
            }
            pstm.setInt(16, pVendaCabecalho.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public List<VendaDetalheVO> vendaAberta() {

        //verifica se existe alguma venda aberta
        consultaSQL =
                "select count(*) as TOTAL from ECF_VENDA_CABECALHO where STATUS_VENDA = 'A'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {

                //verifica se existem itens para a venda aberta
                consultaSQL =
                        "select C.ID as CID, D.ID as DID, C.STATUS_VENDA, D.ID_ECF_PRODUTO, "
                        + "D.QUANTIDADE, D.VALOR_UNITARIO, C.CPF_CNPJ_CLIENTE, "
                        + "D.VALOR_TOTAL, D.ID_ECF_TRIBUTO_PRODUTO, D.ID_CFOP, P.GTIN, P.ID "
                        + "from ECF_VENDA_CABECALHO C LEFT JOIN ECF_VENDA_DETALHE D ON C.ID=D.ID_ECF_VENDA_CABECALHO, PRODUTO P "
                        + "where C.STATUS_VENDA = 'A' and D.CANCELADO is null and D.ID_ECF_PRODUTO=P.ID";

                List<VendaDetalheVO> listaVendaDetalhe = new ArrayList<VendaDetalheVO>();

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    VendaDetalheVO vendaDetalhe = new VendaDetalheVO();
                    vendaDetalhe.setId(rs.getInt("DID"));
                    vendaDetalhe.setIdVendaCabecalho(rs.getInt("CID"));
                    vendaDetalhe.setIdCFOP(rs.getInt("ID_CFOP"));
                    vendaDetalhe.setIdProduto(rs.getInt("ID_ECF_PRODUTO"));
                    vendaDetalhe.setIdTributo(rs.getInt("ID_ECF_TRIBUTO_PRODUTO"));
                    vendaDetalhe.setQuantidade(rs.getDouble("QUANTIDADE"));
                    vendaDetalhe.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                    vendaDetalhe.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                    vendaDetalhe.setGTIN(rs.getString("GTIN"));
                    vendaDetalhe.setIdentificacaoCliente(rs.getString("CPF_CNPJ_CLIENTE"));
                    listaVendaDetalhe.add(vendaDetalhe);
                }
                //caso existam itens, continua com a recuperação da venda
                if (listaVendaDetalhe.size() > 0) {
                    return listaVendaDetalhe;
                } //caso tenha sido aberto um cupom, mas não tenha sido inserido nenhum item
                //altera o status da venda para cancelado e chama o método para cancelamento do cupom
                else {
                    consultaSQL =
                            "update ECF_VENDA_CABECALHO set STATUS_VENDA='C' "
                            + " where STATUS_VENDA = 'A'";
                    try {
                        pstm = bd.conectar().prepareStatement(consultaSQL);
                        pstm.executeUpdate();
                        Ecf.cancelaCupom();
                        return null;
                    } catch (Exception e) {
                        //TODO : Pode ocorrer algum erro nessa ocasião?
                        return null;
                    }
                }
                //caso não exista uma venda aberta, retorna um ponteiro nulo
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void cancelaVenda(VendaCabecalhoVO pVendaCabecalho) {
        consultaSQL =
                "update ECF_VENDA_CABECALHO set "
                + "STATUS_VENDA=?, "
                + "VALOR_VENDA=?, "
                + "VALOR_CANCELADO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, "C");
            pstm.setDouble(2, pVendaCabecalho.getValorVenda());
            pstm.setDouble(3, pVendaCabecalho.getValorCancelado());
            pstm.setInt(4, pVendaCabecalho.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void cancelaItem(VendaDetalheVO pVendaDetalhe) {
        consultaSQL =
                "update ECF_VENDA_DETALHE set "
                + "CANCELADO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setString(1, pVendaDetalhe.getCancelado());
            pstm.setInt(2, pVendaDetalhe.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }
}

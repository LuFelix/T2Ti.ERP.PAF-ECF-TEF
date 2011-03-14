/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Regras de negócio/persistência do Produto.</p>
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
import com.t2tierp.pafecf.vo.ProdutoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ProdutoVO consulta(String codigo) {
        ProdutoVO produtoVO = new ProdutoVO();
        consultaSQL =
                "select P.ID, P.ID_ECF_TRIBUTOS, P.ID_UNIDADE_PRODUTO, P.GTIN, P.CODIGO_INTERNO, "
                + "P.NOME AS NOME_PRODUTO, P.DESCRICAO, P.DESCRICAO_PDV, P.VALOR_VENDA, P.QTD_ESTOQUE, P.ESTOQUE_MIN, "
                + "P.ESTOQUE_MAX, P.IAT, P.IPPT, P.NCM, U.NOME AS NOME_UNIDADE, T.ECF_ICMS, T.CST, T.TOTALIZADOR_PARCIAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T where GTIN=" + codigo;

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produtoVO.setId(rs.getInt("ID"));
                produtoVO.setGTIN(rs.getString("GTIN"));
                produtoVO.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                produtoVO.setValorVenda(rs.getDouble("VALOR_VENDA"));
                produtoVO.setIdTributo(rs.getInt("ID_ECF_TRIBUTOS"));
                produtoVO.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                produtoVO.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                produtoVO.setNome(rs.getString("NOME_PRODUTO"));
                produtoVO.setDescricao(rs.getString("DESCRICAO"));
                produtoVO.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                produtoVO.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                produtoVO.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                produtoVO.setIAT(rs.getString("IAT"));
                produtoVO.setIPPT(rs.getString("IPPT"));
                produtoVO.setNCM(rs.getString("NCM"));
                produtoVO.setUnidadeProduto(rs.getString("NOME_UNIDADE"));
                produtoVO.setSituacaoTributaria(rs.getString("CST"));
                produtoVO.setECFICMS(rs.getString("ECF_ICMS"));
                produtoVO.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                return produtoVO;
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

    public ProdutoVO consultaId(Integer pId) {
        ProdutoVO produto = new ProdutoVO();
        consultaSQL =
                "select P.ID, P.ID_ECF_TRIBUTOS, P.ID_UNIDADE_PRODUTO, P.GTIN, P.CODIGO_INTERNO, "
                + "P.NOME AS NOME_PRODUTO, P.DESCRICAO, P.DESCRICAO_PDV, P.VALOR_VENDA, P.QTD_ESTOQUE, P.ESTOQUE_MIN, "
                + "P.ESTOQUE_MAX, P.IAT, P.IPPT, P.NCM, U.NOME AS NOME_UNIDADE, T.ECF_ICMS, T.CST, T.TOTALIZADOR_PARCIAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T where P.ID=" + pId;

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produto.setId(rs.getInt("ID"));
                produto.setGTIN(rs.getString("GTIN"));
                produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                produto.setIdTributo(rs.getInt("ID_ECF_TRIBUTOS"));
                produto.setIdUnidade(rs.getInt("ID_UNIDADE_PRODUTO"));
                produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                produto.setNome(rs.getString("NOME_PRODUTO"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                produto.setIAT(rs.getString("IAT"));
                produto.setIPPT(rs.getString("IPPT"));
                produto.setNCM(rs.getString("NCM"));
                produto.setUnidadeProduto(rs.getString("NOME_UNIDADE"));
                produto.setSituacaoTributaria(rs.getString("CST"));
                produto.setECFICMS(rs.getString("ECF_ICMS"));
                produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                return produto;
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

    public List<ProdutoVO> tabelaProduto() {
        consultaSQL =
                "select count(*) as TOTAL "
                + "from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T "
                + "where P.ID_UNIDADE_PRODUTO = U.ID AND P.ID_ECF_TRIBUTOS = T.ID ";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "select P.ID AS PID,P.ID_UNIDADE_PRODUTO,P.GTIN,P.DESCRICAO_PDV, "
                        + "P.IAT,P.IPPT,P.VALOR_VENDA,P.QTD_ESTOQUE,P.DESCRICAO,P.NOME AS PNOME, "
                        + "P.ID_ECF_TRIBUTOS, U.ID AS UID, U.NOME AS UNOME, T.ID AS TID, T.CST, T.ECF_ICMS, "
                        + "T.TOTALIZADOR_PARCIAL, T.TAXA_ICMS "
                        + "from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T "
                        + "where P.ID_UNIDADE_PRODUTO = U.ID AND P.ID_ECF_TRIBUTOS = T.ID";


                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    produto.setId(rs.getInt("PID"));
                    produto.setGTIN(rs.getString("produto"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setNome(rs.getString("PNOME"));
                    produto.setUnidadeProduto(rs.getString("UNOME"));
                    produto.setIAT(rs.getString("IAT"));
                    produto.setIPPT(rs.getString("IPPT"));
                    produto.setSituacaoTributaria(rs.getString("CST"));
                    produto.setECFICMS(rs.getString("ECF_ICMS"));
                    produto.setAliquotaICMS(rs.getDouble("TAXA_ICMS"));
                    produto.setTotalizadorParcial(rs.getString("TOTALIZADOR_PARCIAL"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

    public List<ProdutoVO> produtoFiltro(String filtroNome) {
        String procurePor = "%" + filtroNome + "%";
        consultaSQL =
                "SELECT COUNT(*) as TOTAL FROM PRODUTO "
                + "WHERE NOME LIKE '" + procurePor + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
                consultaSQL =
                        "SELECT * FROM PRODUTO "
                        + "WHERE NOME LIKE '" + procurePor + "'"
                        + " ORDER BY NOME";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ProdutoVO produto = new ProdutoVO();
                    produto.setNome(rs.getString("NOME"));
                    produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
                    produto.setDescricaoPDV(rs.getString("DESCRICAO_PDV"));
                    produto.setGTIN(rs.getString("GTIN"));
                    produto.setCodigoInterno(rs.getString("CODIGO_INTERNO"));
                    produto.setQuantidadeEstoque(rs.getDouble("QTD_ESTOQUE"));
                    produto.setEstoqueMinimo(rs.getDouble("ESTOQUE_MIN"));
                    produto.setEstoqueMaximo(rs.getDouble("ESTOQUE_MAX"));
                    listaProduto.add(produto);
                }
                return listaProduto;
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

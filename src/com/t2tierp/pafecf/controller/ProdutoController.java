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
import java.sql.ResultSet;
import java.sql.Statement;

public class ProdutoController {

    Statement stm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ProdutoVO consulta(String codigo) {
        ProdutoVO produtoVO = new ProdutoVO();
        String consultaSQL = "select "
                + "ID,"
                + "GTIN,"
                + "DESCRICAO_PDV,"
                + "VALOR_VENDA,"
                + "MOVIMENTA_ESTOQUE,"
                + "ID_ECF_TRIBUTOS,"
                + "ID_UNIDADE_PRODUTO"
                + " from PRODUTO where GTIN=" + codigo;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                produtoVO.setId(rs.getInt(1));
                produtoVO.setGtin(rs.getString(2));
                produtoVO.setDescricaoPdv(rs.getString(3));
                produtoVO.setValorVenda(rs.getDouble(4));
                produtoVO.setMovimentaEstoque(rs.getString(5));
                produtoVO.setIdTributo(rs.getInt(6));
                produtoVO.setIdUnidade(rs.getInt(7));
            } else {
                produtoVO.setId(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return produtoVO;
    }
}

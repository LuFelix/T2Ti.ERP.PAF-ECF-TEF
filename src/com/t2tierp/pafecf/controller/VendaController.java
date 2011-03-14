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
import com.t2tierp.pafecf.vo.VendaCabecalhoVO;
import com.t2tierp.pafecf.vo.VendaDetalheVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VendaController {

    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public VendaCabecalhoVO iniciaVenda(VendaCabecalhoVO vendaCabecalhoVO) {
        String consultaSQL =
        "insert into ECF_VENDA_CABECALHO ("+
        "ID_ECF_CAIXA,"+
        "ID_OPERADOR,"+
        "ID_IMPRESSORA,"+
        "ID_ECF_MOVIMENTO,"+
        "DATA_HORA_VENDA) values (?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, vendaCabecalhoVO.getIdCaixa());
            pstm.setInt(2, vendaCabecalhoVO.getIdOperador());
            pstm.setInt(3, vendaCabecalhoVO.getIdImpressora());
            pstm.setInt(4, vendaCabecalhoVO.getIdMovimento());
            pstm.setDate(5, vendaCabecalhoVO.getDataHoraVenda());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } 

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select max(ID) as ID from ECF_VENDA_CABECALHO");
            rs.first();
            vendaCabecalhoVO.setId(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }

        return vendaCabecalhoVO;
    }

    public void inserirItem(VendaDetalheVO vendaDetalheVO) {

        String consultaSQL =
        "insert into ECF_VENDA_DETALHE ("+
        "ID_CFOP,"+
        "ID_ECF_TRIBUTO_PRODUTO,"+
        "ID_ECF_PRODUTO,"+
        "ID_ECF_VENDA_CABECALHO,"+
        "QUANTIDADE,"+
        "VALOR_UNITARIO,"+
        "VALOR_TOTAL,"+
        "MOVIMENTA_ESTOQUE) values (?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, vendaDetalheVO.getIdCFOP());
            pstm.setInt(2, vendaDetalheVO.getIdTributo());
            pstm.setInt(3, vendaDetalheVO.getIdProduto());
            pstm.setInt(4, vendaDetalheVO.getIdVendaCabecalho());
            pstm.setDouble(5, vendaDetalheVO.getQuantidade());
            pstm.setDouble(6, vendaDetalheVO.getValorUnitario());
            pstm.setDouble(7, vendaDetalheVO.getValorTotal());
            pstm.setString(8, vendaDetalheVO.getMovimentaEstoque());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void encerraVenda(VendaCabecalhoVO vendaCabecalhoVO) {

        String consultaSQL =
        "update ECF_VENDA_CABECALHO set VALOR_VENDA=? " +
        " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setDouble(1, vendaCabecalhoVO.getValorFinal());
            pstm.setInt(2, vendaCabecalhoVO.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }
}

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Regras de negócio/persistência do Movimento.</p>
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
import com.t2tierp.pafecf.vo.MovimentoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovimentoController {

    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public void consulta() {
        String consultaSQL = "select * from ECF_MOVIMENTO";
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public MovimentoVO verificaMovimento() {
        MovimentoVO movimentoVO = new MovimentoVO();
        String consultaSQL = "select M.ID, M.ID_OPERADOR, O.LOGIN, M.ID_ECF_CAIXA, "
                + "M.ID_IMPRESSORA, M.DATA_HORA_ABERTURA from ECF_MOVIMENTO M, "
                + "ECF_OPERADOR O where STATUS_MOVIMENTO='A' and M.ID_OPERADOR=O.ID";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                movimentoVO.setId(rs.getInt(1));
                movimentoVO.setIdOperador(rs.getInt(2));
                movimentoVO.setIdCaixa(rs.getInt(4));
                movimentoVO.setIdImpressora(rs.getInt(5));
                movimentoVO.setDataHoraAbertura(rs.getDate(6));
            } else {
                movimentoVO.setId(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return movimentoVO;
    }

    public void encerraMovimento(MovimentoVO movimentoVO) {
        String consultaSQL =
        "update ECF_MOVIMENTO set DATA_HORA_FECHAMENTO=?, STATUS_MOVIMENTO=? " +
        " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setDate(1, movimentoVO.getDataHoraFechamento());
            pstm.setString(2, movimentoVO.getStatusMovimento());
            pstm.setInt(3, movimentoVO.getId());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public boolean insere(MovimentoVO movimento) {
        return true;
    }

    public MovimentoVO iniciaMovimento(MovimentoVO movimentoVO) {

        String consultaSQL =
        "insert into ECF_MOVIMENTO ("+
        "ID_ECF_CAIXA,"+
        "ID_OPERADOR,"+
        "ID_IMPRESSORA,"+
        "DATA_HORA_ABERTURA,"+
        "TOTAL_SUPRIMENTO,"+
        "STATUS_MOVIMENTO,"+
        "SINCRONIZADO) values (?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, movimentoVO.getIdCaixa());
            pstm.setInt(2, movimentoVO.getIdOperador());
            pstm.setInt(3, movimentoVO.getIdImpressora());
            pstm.setDate(4, movimentoVO.getDataHoraAbertura());
            pstm.setDouble(5, movimentoVO.getTotalSuprimento());
            pstm.setString(6, movimentoVO.getStatusMovimento());
            pstm.setString(7, movimentoVO.getSincronizado());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select max(ID) as ID from ECF_MOVIMENTO");
            rs.first();
            movimentoVO.setId(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }

        return movimentoVO;
    }
}

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
import com.t2tierp.pafecf.vo.SangriaVO;
import com.t2tierp.pafecf.vo.SuprimentoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovimentoController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public MovimentoVO verificaMovimento() {
        MovimentoVO movimento = new MovimentoVO();
        consultaSQL =
                "select "
                + "M.ID as MID, T.ID as TID, T.DESCRICAO, C.ID as CID, C.NOME, "
                + "O.ID as OID, O.LOGIN, I.ID as IID, I.IDENTIFICACAO, "
                + "M.DATA_HORA_ABERTURA, M.ID_GERENTE_SUPERVISOR, M.STATUS_MOVIMENTO "
                + "from "
                + "ECF_MOVIMENTO M, ECF_TURNO T, ECF_CAIXA C, ECF_OPERADOR O, ECF_IMPRESSORA I "
                + "where "
                + "M.ID_ECF_TURNO = T.ID AND "
                + "M.ID_ECF_IMPRESSORA = I.ID AND "
                + "M.ID_ECF_OPERADOR = O.ID AND "
                + "M.ID_ECF_CAIXA = C.ID AND"
                + "(STATUS_MOVIMENTO='A' or STATUS_MOVIMENTO='T')";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                movimento.setId(rs.getInt("MID"));
                movimento.setIdTurno(rs.getInt("TID"));
                movimento.setIdImpressora(rs.getInt("IID"));
                movimento.setIdOperador(rs.getInt("OID"));
                movimento.setIdCaixa(rs.getInt("CID"));
                movimento.setIdGerenteSupervisor(rs.getInt("ID_GERENTE_SUPERVISOR"));
                movimento.setDataHoraAbertura(rs.getTimestamp("DATA_HORA_ABERTURA"));
                movimento.setStatusMovimento(rs.getString("STATUS_MOVIMENTO"));
                movimento.setLoginOperador(rs.getString("LOGIN"));
                movimento.setNomeCaixa(rs.getString("NOME"));
                movimento.setDescricaoTurno(rs.getString("DESCRICAO"));
                movimento.setIdentificacaoImpressora(rs.getString("IDENTIFICACAO"));
                return movimento;
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

    public void encerraMovimento(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set DATA_HORA_FECHAMENTO=?, STATUS_MOVIMENTO=? "
                + " where ID = ?";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setTimestamp(1, movimento.getDataHoraFechamento());
            pstm.setString(2, movimento.getStatusMovimento());
            pstm.setInt(3, movimento.getId());
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

    public MovimentoVO iniciaMovimento(MovimentoVO movimento) {

        consultaSQL =
                "insert into ECF_MOVIMENTO ("
                + "ID_ECF_TURNO,"
                + "ID_ECF_IMPRESSORA,"
                + "ID_ECF_OPERADOR,"
                + "ID_ECF_CAIXA,"
                + "ID_GERENTE_SUPERVISOR,"
                + "DATA_HORA_ABERTURA,"
                + "TOTAL_SUPRIMENTO,"
                + "STATUS_MOVIMENTO,"
                + "SINCRONIZADO) "
                + "values (?,?,?,?,?,?,?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getIdTurno());
            pstm.setInt(2, movimento.getIdImpressora());
            pstm.setInt(3, movimento.getIdOperador());
            pstm.setInt(4, movimento.getIdCaixa());
            pstm.setInt(5, movimento.getIdGerenteSupervisor());
            pstm.setTimestamp(6, movimento.getDataHoraAbertura());
            pstm.setDouble(7, movimento.getTotalSuprimento());
            pstm.setString(8, movimento.getStatusMovimento());
            pstm.setString(9, movimento.getSincronizado());
            pstm.executeUpdate();

            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select max(ID) as ID from ECF_MOVIMENTO");
            rs.first();
            movimento.setId(rs.getInt(1));
            return movimento;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void saidaTemporaria(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set STATUS_MOVIMENTO='T' "
                + " where ID = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public void retornoOperador(MovimentoVO movimento) {
        consultaSQL =
                "update ECF_MOVIMENTO set STATUS_MOVIMENTO='A' "
                + " where ID = ?";
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, movimento.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public boolean suprimento(SuprimentoVO suprimento) {
        String consultaSQL =
        "insert into ECF_SUPRIMENTO (ID_ECF_MOVIMENTO,DATA_SUPRIMENTO,VALOR)" +
        " values (?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, suprimento.getIdMovimento());
            pstm.setDate(2, suprimento.getDataSuprimento());
            pstm.setDouble(3, suprimento.getValor());
            pstm.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public boolean sangria(SangriaVO sangria) {
        String consultaSQL =
        "insert into ECF_SANGRIA (ID_ECF_MOVIMENTO,DATA_SANGRIA,VALOR)" +
        " values (?,?,?)";

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);

            pstm.setInt(1, sangria.getIdMovimento());
            pstm.setDate(2, sangria.getDataSangria());
            pstm.setDouble(3, sangria.getValor());
            pstm.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }
}

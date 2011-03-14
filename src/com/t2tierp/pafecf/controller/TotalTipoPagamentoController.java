/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Regras de negócio/persistência dos Valores pagos
 * em determinada venda com os respectivos tipos de pagamento.</p>
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
import com.t2tierp.pafecf.vo.TotalTipoPgtoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TotalTipoPagamentoController {

    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public void gravaTotaisVenda(ArrayList<TotalTipoPgtoVO> totais) {
        String consultaSQL =
        "insert into ECF_TOTAL_TIPO_PGTO (ID_ECF_VENDA_CABECALHO,ID_ECF_TIPO_PAGAMENTO,VALOR) "
        + "values (?, ?, ?)";
        
        for (int i=0; i<3; i++) {
            try {
                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setInt(1, totais.get(i).getIdVendaCabecalho());
                pstm.setInt(2, totais.get(i).getIdTipoPagamento());
                pstm.setDouble(3, totais.get(i).getValor());
                pstm.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bd.desconectar();
            }
            
        }
    }
}

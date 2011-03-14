/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe que será utilizada como modelo da Tabela.</p>
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
package com.t2tierp.pafecf.infra;

import com.t2tierp.pafecf.vo.ProdutoVO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {

    private List<ProdutoVO> listaProduto;

    public ProdutoTableModel(List<ProdutoVO> listaCliente) {
        this.listaProduto = listaCliente;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        ProdutoVO produto = listaProduto.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getNome();
            case 1:
                return produto.getValorVenda();
            case 2:
                return produto.getDescricaoPDV();
            case 3:
                return produto.getGTIN();
            case 4:
                return produto.getCodigoInterno();
            case 5:
                return produto.getQuantidadeEstoque();
            case 6:
                return produto.getEstoqueMinimo();
            case 7:
                return produto.getEstoqueMaximo();
        }
        return null;
    }

    public int getRowCount() {

        return listaProduto.size();
    }

    public int getColumnCount() {
        return 8;
    }

    public ProdutoVO getValues(int rowIndex) {
        return listaProduto.get(rowIndex);
    }

}

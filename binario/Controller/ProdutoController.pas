{*******************************************************************************
Title: T2Ti ERP
Description: Classe de controle do produto.

The MIT License

Copyright: Copyright (C) 2010 T2Ti.COM

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

       The author may be contacted at:
           t2ti.com@gmail.com</p>

@author Albert Eije (T2Ti.COM)
@version 1.0
*******************************************************************************}
unit ProdutoController;

interface

uses
  Classes, SQLExpr, SysUtils, ProdutoVO, Generics.Collections;

type
  TProdutoController = class
  protected
  public
    Function TabelaProduto: TObjectList<TProdutoVO>;
  end;

implementation

uses UDataModule;

var
  ConsultaSQL: String;
  Query: TSQLQuery;

function TProdutoController.TabelaProduto: TObjectList<TProdutoVO>;
var
  ListaProduto: TObjectList<TProdutoVO>;
  Produto: TProdutoVO;
  TotalRegistros: Integer;
begin
  try
    try
      //verifica se existem produtos
      ConsultaSQL :=
        'select count(*) as TOTAL '+
        'from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T '+
        'where P.ID_UNIDADE_PRODUTO = U.ID AND P.ID_ECF_TRIBUTOS = T.ID ';

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        //continua com a execução do procedimento
        ConsultaSQL :=
          'select P.ID AS PID,P.ID_UNIDADE_PRODUTO,P.GTIN,P.DESCRICAO_PDV, '+
          'P.IAT,P.IPPT,P.VALOR_VENDA,P.QTD_ESTOQUE,P.DESCRICAO,P.NOME AS PNOME, '+
          'P.ID_ECF_TRIBUTOS, U.ID AS UID, U.NOME AS UNOME, T.ID AS TID, T.CST, T.ECF_ICMS, '+
          'T.TOTALIZADOR_PARCIAL, T.TAXA_ICMS '+
          'from PRODUTO P, UNIDADE_PRODUTO U, ECF_TRIBUTO_PRODUTO T '+
          'where P.ID_UNIDADE_PRODUTO = U.ID AND P.ID_ECF_TRIBUTOS = T.ID';

        Query.sql.Text := ConsultaSQL;
        Query.Open;

        ListaProduto := TObjectList<TProdutoVO>.Create;

        Query.First;
        while not Query.Eof do
        begin
          Produto := TProdutoVO.Create;
          Produto.Id := Query.FieldByName('PID').AsInteger;
          Produto.GTIN := Query.FieldByName('GTIN').AsString;
          Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
          Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
          Produto.Nome := Query.FieldByName('PNOME').AsString;
          Produto.UnidadeProduto := Query.FieldByName('UNOME').AsString;
          Produto.IAT := Query.FieldByName('IAT').AsString;
          Produto.IPPT := Query.FieldByName('IPPT').AsString;
          Produto.SituacaoTributaria := Query.FieldByName('CST').AsString;
          Produto.ECFICMS := Query.FieldByName('ECF_ICMS').AsString;
          Produto.AliquotaICMS := Query.FieldByName('TAXA_ICMS').AsFloat;
          Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
          Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
          Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
          ListaProduto.Add(Produto);
          Query.next;
        end;
        result := ListaProduto;
      end
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

end.

{*******************************************************************************
Title: T2Ti ERP
Description: Classe de controle dos totais de tipos de pagamento.

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
unit TotalTipoPagamentoController;

interface

uses
  Classes, SQLExpr, SysUtils, TipoPagamentoVO, Generics.Collections, TotalTipoPagamentoVO,
  DB, MeiosPagamentoVO;

type
  TTotalTipoPagamentoController = class
  protected
  public
    function MeiosPagamento(DataInicio:String;DataFim:String;IdImpressora:Integer): TObjectList<TMeiosPagamentoVO>;
  end;

implementation

uses UDataModule, R07VO, RegistroRController;

var
  Query: TSQLQuery;
  ConsultaSQL: String;

function TTotalTipoPagamentoController.MeiosPagamento(DataInicio:String;DataFim:String;IdImpressora:Integer): TObjectList<TMeiosPagamentoVO>;
var
  ListaMeiosPagamento: TObjectList<TMeiosPagamentoVO>;
  MeiosPagamento: TMeiosPagamentoVO;
begin
  DataInicio := FormatDateTime('yyyy-mm-dd', StrToDate(DataInicio));
  DataFim := FormatDateTime('yyyy-mm-dd', StrToDate(DataFim));

  ConsultaSQL :=
    'SELECT V.DATA_HORA_VENDA, M.ID_ECF_IMPRESSORA, P.DESCRICAO, SUM(TP.VALOR) AS TOTAL '+
    'FROM '+
    'ECF_VENDA_CABECALHO V, ECF_MOVIMENTO M, ECF_TIPO_PAGAMENTO P, ECF_TOTAL_TIPO_PGTO TP '+
    'WHERE '+
    'V.ID_ECF_MOVIMENTO = M.ID AND '+
    'TP.ID_ECF_VENDA_CABECALHO=V.ID AND '+
    'TP.ID_ECF_TIPO_PAGAMENTO = P.ID AND '+
    'M.ID_ECF_IMPRESSORA = '+ IntToStr(idImpressora) + ' AND '+
    '(V.DATA_HORA_VENDA BETWEEN ' + QuotedStr(DataInicio) + ' and ' + QuotedStr(DataFim) +
    ') GROUP BY '+
    'P.DESCRICAO,V.DATA_HORA_VENDA,M.ID_ECF_IMPRESSORA';

  try
    try
      ListaMeiosPagamento := TObjectList<TMeiosPagamentoVO>.Create;

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      Query.First;
      while not Query.Eof do
      begin
        MeiosPagamento := TMeiosPagamentoVO.Create;
        MeiosPagamento.Descricao := Query.FieldByName('DESCRICAO').AsString;
        MeiosPagamento.DataHora := Query.FieldByName('DATA_HORA_VENDA').AsString;
        MeiosPagamento.Total := Query.FieldByName('TOTAL').AsFloat;
        ListaMeiosPagamento.Add(MeiosPagamento);
        Query.next;
      end;
      result := ListaMeiosPagamento;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

end.

{ *******************************************************************************
  Title: T2Ti ERP
  Description: Classe de controle do Sintegra.

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
  ******************************************************************************* }
unit SintegraController;

interface

uses
  Classes, SQLExpr, SysUtils, Sintegra60MVO, Sintegra60AVO, Sintegra60DVO, Generics.Collections;

type
  TSintegraController = class
  protected
  public
    function Tabela60M: TObjectList<TSintegra60MVO>;
    function Tabela60A(Id: Integer): TObjectList<TSintegra60AVO>;
    function Tabela60D: TObjectList<TSintegra60DVO>;
 end;

implementation

uses UDataModule;

var
  ConsultaSQL: String;
  Query: TSQLQuery;

function TSintegraController.Tabela60M: TObjectList<TSintegra60MVO>;
var
  Lista60M: TObjectList<TSintegra60MVO>;
  Sintegra60M: TSintegra60MVO;
  TotalRegistros: Integer;
begin
  ConsultaSQL := 'select count(*) as TOTAL from SINTEGRA_60M';
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;
      if TotalRegistros > 0 then
      begin
        Lista60M := TObjectList<TSintegra60MVO>.Create;

        ConsultaSQL := 'select * from SINTEGRA_60M';
        Query.sql.Text := ConsultaSQL;
        Query.Open;
        Query.First;
        while not Query.Eof do
        begin
          Sintegra60M := TSintegra60MVO.Create;
          Sintegra60M.Id := Query.FieldByName('ID').AsInteger;
          Sintegra60M.DataEmissao := Query.FieldByName('DATA_EMISSAO').AsString;
          Sintegra60M.SerieImpressora := Query.FieldByName('NUMERO_SERIE_ECF').AsString;
          Sintegra60M.NumeroEquipamento := Query.FieldByName('NUMERO_EQUIPAMENTO').AsInteger;
          Sintegra60M.ModeloDocumentoFiscal := Query.FieldByName('MODELO_DOCUMENTO_FISCAL').AsString;
          Sintegra60M.COOInicial := Query.FieldByName('COO_INICIAL').AsInteger;
          Sintegra60M.COOFinal := Query.FieldByName('COO_FINAL').AsInteger;
          Sintegra60M.CRZ := Query.FieldByName('CRZ').AsInteger;
          Sintegra60M.CRO := Query.FieldByName('CRO').AsInteger;
          Sintegra60M.VendaBruta := Query.FieldByName('VALOR_VENDA_BRUTA').AsFloat;
          Sintegra60M.GrandeTotal := Query.FieldByName('VALOR_GRANDE_TOTAL').AsFloat;
          Lista60M.Add(Sintegra60M);
          Query.next;
        end;
        result := Lista60M;
      end
      // caso não exista a relacao, retorna um ponteiro nulo
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

function TSintegraController.Tabela60A(Id: Integer): TObjectList<TSintegra60AVO>;
var
  Lista60A: TObjectList<TSintegra60AVO>;
  Sintegra60A: TSintegra60AVO;
  TotalRegistros: Integer;
begin
  ConsultaSQL := 'select count(*) AS TOTAL from SINTEGRA_60A where ID_SINTEGRA_60M='+IntToStr(Id);
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        Lista60A := TObjectList<TSintegra60AVO>.Create;

        ConsultaSQL := 'select * from SINTEGRA_60A where ID_SINTEGRA_60M='+IntToStr(Id);
        Query.sql.Text := ConsultaSQL;
        Query.Open;
        Query.First;
        while not Query.Eof do
        begin
          Sintegra60A := TSintegra60AVO.Create;
          Sintegra60A.Id := Query.FieldByName('ID').AsInteger;
          Sintegra60A.Id60M := Query.FieldByName('ID_SINTEGRA_60M').AsInteger;
          Sintegra60A.SituacaoTributaria := Query.FieldByName('SITUACAO_TRIBUTARIA').AsString;
          Sintegra60A.Valor := Query.FieldByName('VALOR').AsFloat;
          Lista60A.Add(Sintegra60A);
          Query.next;
        end;
        result := Lista60A;
      end
      // caso não exista a relacao, retorna um ponteiro nulo
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

function TSintegraController.Tabela60D: TObjectList<TSintegra60DVO>;
var
  Lista60D: TObjectList<TSintegra60DVO>;
  Sintegra60D: TSintegra60DVO;
begin
  ConsultaSQL :=
    'select '+
    'P.GTIN, P.DESCRICAO_PDV, U.NOME, VD.QUANTIDADE, VD.TOTAL_ITEM, VD.BASE_ICMS, VD.CST, VD.ICMS, VD.TAXA_ICMS '+
    'from ECF_VENDA_DETALHE VD, PRODUTO P, UNIDADE_PRODUTO U '+
    'where VD.ID_ECF_PRODUTO=P.ID and P.ID_UNIDADE_PRODUTO=U.ID';

  try
    try
      Lista60D := TObjectList<TSintegra60DVO>.Create;

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      Query.First;
      while not Query.Eof do
      begin
        Sintegra60D := TSintegra60DVO.Create;
        Sintegra60D.GTIN := Query.FieldByName('GTIN').AsString;
        Sintegra60D.Descricao := Query.FieldByName('DESCRICAO_PDV').AsString;
        Sintegra60D.SiglaUnidade := Query.FieldByName('NOME').AsString;
        Sintegra60D.Quantidade := Query.FieldByName('QUANTIDADE').AsFloat;
        Sintegra60D.ValorLiquido := Query.FieldByName('TOTAL_ITEM').AsFloat;
        Sintegra60D.BaseICMS := Query.FieldByName('BASE_ICMS').AsFloat;
        Sintegra60D.SituacaoTributaria := Query.FieldByName('CST').AsString;
        Sintegra60D.ValorICMS := Query.FieldByName('ICMS').AsFloat;
        Sintegra60D.AliquotaICMS := Query.FieldByName('TAXA_ICMS').AsFloat;
        Lista60D.Add(Sintegra60D);
        Query.next;
      end;
      result := Lista60D;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

end.

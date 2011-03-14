{ *******************************************************************************
  Title: T2Ti ERP
  Description: Funções e procedimentos do Sintegra;

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

unit USintegra;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Dbtables, Inifiles, Generics.Collections, ACBrSintegra;

procedure GerarRegistro10(DataIni:String;DataFim:String);
procedure GerarRegistro11;
procedure GerarRegistro60M;
procedure GerarRegistro60D;
procedure GerarArquivoSintegra(DataIni:String;DataFim:String);

implementation

uses
UDataModule, EmpresaController, EmpresaVO, Sintegra60MVO, SintegraController,
Sintegra60AVO, Sintegra60DVO;

var
  Empresa : TEmpresaVO;
  SerieImpressora: String;

{
Registro mestre do estabelecimento, destinado à identificação do estabelecimento
informante
}
procedure GerarRegistro10(DataIni:String;DataFim:String);
var
  EmpresaControl: TEmpresaController;
begin
  EmpresaControl := TEmpresaController.Create;
  Empresa := EmpresaControl.PegaEmpresa(StrToInt(FDataModule.IdEmpresa));
  with FDataModule.ACBrSintegra do
  begin
    Registro10.CNPJ := Empresa.CNPJ;
    Registro10.Inscricao := Empresa.InscricaoEstadual;
    Registro10.RazaoSocial := Empresa.RazaoSocial;
    Registro10.Cidade := Empresa.Cidade;
    Registro10.Estado := Empresa.UF;
    Registro10.Telefone := Empresa.Fone1;
    Registro10.DataInicial := StrToDate(DataIni);
    Registro10.DataFinal := StrToDate(DataFim);

    { TODO : Devemos permitir que o usuário informe os três itens abaixo? }
    {
    1 - Convênio 57/95 Versão 31/99 Alt. 30/02
    2 - Convênio 57/95 Versão 69/02 Alt. 142/02
    3 - Convênio 57/95 Alt. 76/03
    }
    Registro10.CodigoConvenio := '1';
    {
    1 - Interestaduais - Somente operações sujeitas ao regime de Substituição Tributária
    2 - Interestaduais - Operações com ou sem Substituição Tributária
    3 - Totalidade das operações do informante
    }
    Registro10.NaturezaInformacoes := '3';
    {
    1 - Normal
    2 - Retificação total de arquivo: substituição total de informações prestadas pelo contribuinte referentes a este período
    3 - Retificação aditiva de arquivo: acréscimo de informação não incluída em arquivos já apresentados
    5 - Desfazimento: arquivo de informação referente a operações/prestações não efetivadas .
        Neste caso, o arquivo deverá conter, além dos registros tipo 10 e tipo 90, apenas os registros referentes as operações/prestações não efetivadas
    }
    Registro10.FinalidadeArquivo := '1';
  end;
end;

{
Dados complementares do informante
}
procedure GerarRegistro11;
begin
  with FDataModule.ACBrSintegra do
  begin
    Registro11.Endereco := Empresa.Endereco;
    Registro11.Numero := '';
    Registro11.Bairro := Empresa.Bairro;
    Registro11.Cep := Empresa.CEP;
    Registro11.Responsavel := Empresa.Contato;
    Registro11.Telefone := Empresa.Fone1;
  end;
end;

{
Registro destinado a informar as operações e prestações realizadas com os documentos
fiscais emitidos por equipamento emissor de cupom fiscal os quais são: Cupom Fiscal,
Cupom Fiscal - PDV, Bilhete de Passagem Rodoviário, modelo 13, Bilhete de Passagem
Aquaviário, modelo 14, Bilhete de Passagem e Nota de Bagagem, modelo 15, Bilhete
de Passagem Ferroviário, modelo 16, e Nota Fiscal de Venda a Consumidor, modelo 2;

60M-MESTRE    <------
60A-ANALITO   <------
60D-DIARIO
60I-ITEM
}
procedure GerarRegistro60M;
var
  wregistro60M: TRegistro60M;
  wregistro60A: TRegistro60A;
  Lista60M: TObjectList<TSintegra60MVO>;
  Lista60A: TObjectList<TSintegra60AVO>;
  SintegraControl : TSintegraController;
  i,j:integer;
begin
  { TODO : Este procedimento está correto? Deve-se gerar 60M e 60A juntos? }
  SintegraControl := TSintegraController.Create;
  Lista60M := SintegraControl.Tabela60M;
  SerieImpressora := TSintegra60MVO(Lista60M.Items[0]).SerieImpressora;
  for i := 0 to Lista60M.Count - 1 do
  begin
    wregistro60M := TRegistro60M.Create;
    wregistro60M.Emissao := StrToDateTime(TSintegra60MVO(Lista60M.Items[i]).DataEmissao);
    wregistro60M.NumSerie := TSintegra60MVO(Lista60M.Items[i]).SerieImpressora;
    wregistro60M.NumOrdem := TSintegra60MVO(Lista60M.Items[i]).NumeroEquipamento;
    wregistro60M.ModeloDoc := TSintegra60MVO(Lista60M.Items[i]).ModeloDocumentoFiscal;
    wregistro60M.CooInicial := TSintegra60MVO(Lista60M.Items[i]).COOInicial;
    wregistro60M.CooFinal := TSintegra60MVO(Lista60M.Items[i]).COOFinal;
    wregistro60M.CRZ := TSintegra60MVO(Lista60M.Items[i]).CRZ;
    wregistro60M.CRO := TSintegra60MVO(Lista60M.Items[i]).CRO;
    wregistro60M.VendaBruta := TSintegra60MVO(Lista60M.Items[i]).VendaBruta;
    wregistro60M.ValorGT := TSintegra60MVO(Lista60M.Items[i]).GrandeTotal;
    FDataModule.ACBrSintegra.Registros60M.Add(wregistro60M);
    Lista60A := SintegraControl.Tabela60A(TSintegra60MVO(Lista60M.Items[i]).Id);
    if Assigned(Lista60A) then
    begin
      for j := 0 to Lista60A.Count - 1 do
      begin
        wregistro60A := TRegistro60A.Create;
        wregistro60A.Emissao := FDataModule.ACBrSintegra.Registro10.DataInicial;
        wregistro60A.NumSerie := TSintegra60MVO(Lista60M.Items[i]).SerieImpressora;
        wregistro60A.StAliquota := TSintegra60AVO(Lista60A.Items[j]).SituacaoTributaria;
        wregistro60A.Valor := TSintegra60AVO(Lista60A.Items[j]).Valor;
        FDataModule.ACBrSintegra.Registros60A.Add(wregistro60A);
      end;
    end;
  end;
end;

{
Registro destinado a informar as operações e prestações realizadas com os documentos
fiscais emitidos por equipamento emissor de cupom fiscal os quais são: Cupom Fiscal,
Cupom Fiscal - PDV, Bilhete de Passagem Rodoviário, modelo 13, Bilhete de Passagem
Aquaviário, modelo 14, Bilhete de Passagem e Nota de Bagagem, modelo 15, Bilhete
de Passagem Ferroviário, modelo 16, e Nota Fiscal de Venda a Consumidor, modelo 2;

60M-MESTRE
60A-ANALITO
60D-DIARIO    <------
60I-ITEM
}
procedure GerarRegistro60D;
var
  wregistro60D: TRegistro60D;
  wregistro75: TRegistro75;
  Lista60D: TObjectList<TSintegra60DVO>;
  SintegraControl : TSintegraController;
  i:integer;
begin
  SintegraControl := TSintegraController.Create;
  Lista60D := SintegraControl.Tabela60D;
  for i := 0 to Lista60D.Count - 1 do
  begin
    wregistro60D := TRegistro60D.Create;
    wregistro60D.Emissao := FDataModule.ACBrSintegra.Registro10.DataInicial;
    wregistro60D.NumSerie := SerieImpressora;
    wregistro60D.Codigo := TSintegra60DVO(Lista60D.Items[i]).GTIN;
    wregistro60D.Quantidade := TSintegra60DVO(Lista60D.Items[i]).Quantidade;
    wregistro60D.Valor := TSintegra60DVO(Lista60D.Items[i]).ValorLiquido;
    wregistro60D.BaseDeCalculo := TSintegra60DVO(Lista60D.Items[i]).BaseICMS;
    wregistro60D.StAliquota := TSintegra60DVO(Lista60D.Items[i]).SituacaoTributaria;
    wregistro60D.ValorIcms := TSintegra60DVO(Lista60D.Items[i]).ValorICMS;
    FDataModule.ACBrSintegra.Registros60D.Add(wregistro60D);

    wregistro75 := TRegistro75.Create;
    wregistro75.Codigo := wregistro60D.Codigo;
    wregistro75.AliquotaICMS := TSintegra60DVO(Lista60D.Items[i]).AliquotaICMS;
    wregistro75.DataInicial := FDataModule.ACBrSintegra.Registro10.DataInicial;
    wregistro75.DataFinal := FDataModule.ACBrSintegra.Registro10.DataFinal;
    wregistro75.Descricao := TSintegra60DVO(Lista60D.Items[i]).Descricao;
    wregistro75.Unidade := TSintegra60DVO(Lista60D.Items[i]).SiglaUnidade;
    FDataModule.ACBrSintegra.Registros75.Add(wregistro75);
  end;
end;

{ TODO : Quais as incorreções na geração desse arquivo? }
{ TODO : Está faltando algum registro? Qual? }
procedure GerarArquivoSintegra(DataIni:String;DataFim:String);
begin
  { TODO : Deixa o usuário configurar o nome/caminho do arquivo? }
  FDataModule.ACBrSintegra.FileName := 'sintegra.txt';
  { TODO : Onde armazenar o dado da versão do validador? Solicitar essa informação para o usuário? }
  FDataModule.ACBrSintegra.VersaoValidador := TVersaoValidador(0);
  GerarRegistro10(DataIni,DataFim);
  GerarRegistro11;
  GerarRegistro60M;
  GerarRegistro60D;
  FDataModule.ACBrSintegra.GeraArquivo;
end;

end.


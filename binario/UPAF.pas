{ *******************************************************************************
  Title: T2Ti ERP
  Description: Funções e procedimentos do PAF;

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

unit UPAF;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Dbtables, Inifiles, Generics.Collections, Biblioteca, ProdutoController,
  ACBrPAF, ACBrPAF_D, ACBrPAF_E, ACBrPAF_P, ACBrPAF_R, ACBrPAF_T,
  ACBrPAFRegistros, Math;

procedure PreencherHeader(Header: TRegistroX1);
procedure GeraTabelaProdutos;
procedure GeraArquivoEstoque;
procedure GeraMovimentoECF;
procedure DAVEmitidos(DataIni: String; DataFim:String);

implementation

uses
  R02VO, R03VO, R04VO, R05VO, R06VO, R07VO, RegistroRController,
  EmpresaController, EmpresaVO, UDataModule, ProdutoVO, ImpressoraController,
  ImpressoraVO, TotalTipoPagamentoController, MeiosPagamentoVO, SintegraController,
  Sintegra60MVO, Sintegra60AVO, DAVController, DAVVO;

procedure PreencherHeader(Header: TRegistroX1);
var
  EmpresaControl: TEmpresaController;
  Empresa: TEmpresaVO;
begin
  EmpresaControl := TEmpresaController.Create;
  Empresa := EmpresaControl.PegaEmpresa(StrToInt(FDataModule.IdEmpresa));
  Header.UF := Empresa.UF;
  Header.CNPJ := Empresa.CNPJ;
  Header.IE := Empresa.InscricaoEstadual;
  Header.IM := Empresa.InscricaoMunicipal;
  Header.RAZAOSOCIAL := Empresa.RAZAOSOCIAL;
end;

procedure GeraTabelaProdutos;
var
  P2: TRegistroP2;
  i: integer;
  ListaProduto: TObjectList<TProdutoVO>;
  ProdutoControl: TProdutoController;
begin
  ListaProduto := ProdutoControl.TabelaProduto;
  if Assigned(ListaProduto) then
  begin
    // registro P1
    PreencherHeader(FDataModule.ACBrPAF.PAF_P.RegistroP1);
    // preencher header do arquivo
    // registro P2
    FDataModule.ACBrPAF.PAF_P.RegistroP2.Clear;
    for i := 0 to ListaProduto.Count - 1 do
    begin
      P2 := FDataModule.ACBrPAF.PAF_P.RegistroP2.New;
      P2.COD_MERC_SERV := TProdutoVO(ListaProduto.Items[i]).GTIN;
      P2.DESC_MERC_SERV := TProdutoVO(ListaProduto.Items[i]).DescricaoPDV;
      P2.UN_MED := TProdutoVO(ListaProduto.Items[i]).UnidadeProduto;
      P2.IAT := TProdutoVO(ListaProduto.Items[i]).IAT;
      P2.IPPT := TProdutoVO(ListaProduto.Items[i]).IPPT;
      P2.ST := TProdutoVO(ListaProduto.Items[i]).SituacaoTributaria;
      P2.ALIQ := StrToFloat(TProdutoVO(ListaProduto.Items[i]).ECFICMS);
      P2.VL_UNIT := TProdutoVO(ListaProduto.Items[i]).ValorVenda;
    end;
    FDataModule.ACBrPAF.SaveFileTXT_P('PAF_P.txt');
  end
  else
    Application.MessageBox('Não existem produtos na tabela.',
      'Informação do Sistema', MB_OK + MB_ICONINFORMATION);
end;

procedure GeraArquivoEstoque;
var
  E2: TRegistroE2;
  i: integer;
  ListaProduto: TObjectList<TProdutoVO>;
  ProdutoControl: TProdutoController;
begin
  ListaProduto := ProdutoControl.TabelaProduto;
  if Assigned(ListaProduto) then
  begin
    // registro E1
    PreencherHeader(FDataModule.ACBrPAF.PAF_E.RegistroE1);
    // preencher header do arquivo
    // registro E2
    FDataModule.ACBrPAF.PAF_E.RegistroE2.Clear;
    for i := 0 to ListaProduto.Count - 1 do
    begin
      E2 := FDataModule.ACBrPAF.PAF_E.RegistroE2.New;
      E2.COD_MERC := TProdutoVO(ListaProduto.Items[i]).GTIN;
      E2.DESC_MERC := TProdutoVO(ListaProduto.Items[i]).DescricaoPDV;
      E2.UN_MED := TProdutoVO(ListaProduto.Items[i]).UnidadeProduto;
      E2.QTDE_EST := TProdutoVO(ListaProduto.Items[i]).QtdeEstoque;
      E2.DT_EST := Date;
    end;
    FDataModule.ACBrPAF.SaveFileTXT_E('PAF_E.txt');
  end
  else
    Application.MessageBox('Não existem produtos na tabela.',
      'Informação do Sistema', MB_OK + MB_ICONINFORMATION);
end;

procedure GeraMovimentoECF;
var
  i, j: integer;
  ImpressoraControl: TImpressoraController;
  Impressora: TImpressoraVO;
  EmpresaControl: TEmpresaController;
  Empresa: TEmpresaVO;
  ListaR02: TObjectList<TR02VO>;
  ListaR03: TObjectList<TR03VO>;
  ListaR04: TObjectList<TR04VO>;
  ListaR05: TObjectList<TR05VO>;
  ListaR06: TObjectList<TR06VO>;
  ListaR07: TObjectList<TR07VO>;
  RegistroRContol: TRegistroRController;
begin
  RegistroRContol := TRegistroRController.Create;
  // dados da impressora
  ImpressoraControl := TImpressoraController.Create;
  Impressora := ImpressoraControl.PegaImpressora
    (StrToInt(FDataModule.IdImpressora));
  // dados da empresa
  EmpresaControl := TEmpresaController.Create;
  Empresa := EmpresaControl.PegaEmpresa(StrToInt(FDataModule.IdEmpresa));
  // Registro R1
  with FDataModule.ACBrPAF.PAF_R.RegistroR01 do
  begin
    NUM_FAB := Impressora.Serie;
    MF_ADICIONAL := Impressora.MFD;
    TIPO_ECF := Impressora.Tipo;
    MARCA_ECF := Impressora.Marca;
    MODELO_ECF := Impressora.Modelo;
    VERSAO_SB := FDataModule.ACBrECF.NumVersao;
    DT_INST_SB := FDataModule.ACBrECF.DataHoraSB;
    HR_INST_SB := FDataModule.ACBrECF.DataHoraSB;
    NUM_SEQ_ECF := Impressora.Id;
    CNPJ := Empresa.CNPJ;
    IE := Empresa.InscricaoEstadual;
    { TODO : Onde vamos armazenar os dados da Software House? No BD? Arquivo INI? }
    CNPJ_SH := '10793118000178';
    IE_SH := 'ISENTO';
    IM_SH := 'ISENTO';
    NOME_SH := 'T2TI.COM';
    NOME_PAF := 'T2Ti PDV';
    VER_PAF := '0100';
    COD_MD5 := '9e107d9d372bb6826bd81d3542a419d6';
    DT_INI := Date;
    DT_FIN := Date;
    ER_PAF_ECF := '0105';
  end;
  // Registro R02 e R03
  ListaR02 := RegistroRContol.TabelaR02;
  for i := 0 to ListaR02.Count - 1 do
  begin
    with FDataModule.ACBrPAF.PAF_R.RegistroR02.New do
    begin
      NUM_USU := TR02VO(ListaR02.Items[i]).IdOperador;
      CRZ := TR02VO(ListaR02.Items[i]).CRZ;
      COO := TR02VO(ListaR02.Items[i]).COO;
      CRO := TR02VO(ListaR02.Items[i]).CRO;
      DT_MOV := StrToDateTime(TR02VO(ListaR02.Items[i]).DataMovimento);
      DT_EMI := StrToDateTime(TR02VO(ListaR02.Items[i]).DataEmissao);
      HR_EMI := StrToDateTime(TR02VO(ListaR02.Items[i]).HoraEmissao);
      VL_VBD := TR02VO(ListaR02.Items[i]).VendaBruta;
      PAR_ECF := '';
      // Registro R03 - FILHO
      ListaR03 := RegistroRContol.TabelaR03(TR02VO(ListaR02.Items[i]).Id);
      if Assigned(ListaR03) then
      begin
        for j := 0 to ListaR03.Count - 1 do
        begin
          with RegistroR03.New do
          begin
            TOT_PARCIAL := TR03VO(ListaR03.Items[j]).TotalizadorParcial;
            VL_ACUM := TR03VO(ListaR03.Items[j]).ValorAcumulado;
          end;
        end;
      end;
    end;
  end;
  // Registro R04 e R05
  ListaR04 := RegistroRContol.TabelaR04;
  for i := 0 to ListaR04.Count - 1 do
  begin
    with FDataModule.ACBrPAF.PAF_R.RegistroR04.New do
    begin
      NUM_USU := TR04VO(ListaR04.Items[i]).IdOperador;
      NUM_CONT := TR04VO(ListaR04.Items[i]).CCF;
      COO := TR04VO(ListaR04.Items[i]).COO;
      DT_INI := StrToDateTime(TR04VO(ListaR04.Items[i]).DataEmissao);
      SUB_DOCTO := TR04VO(ListaR04.Items[i]).SubTotal;
      SUB_DESCTO := TR04VO(ListaR04.Items[i]).Desconto;
      TP_DESCTO := TR04VO(ListaR04.Items[i]).IndicadorDesconto;
      SUB_ACRES := TR04VO(ListaR04.Items[i]).Acrescimo;
      TP_ACRES := TR04VO(ListaR04.Items[i]).IndicadorAcrescimo;
      VL_TOT := TR04VO(ListaR04.Items[i]).ValorLiquido;
      CANC := TR04VO(ListaR04.Items[i]).Cancelado;
      VL_CA := TR04VO(ListaR04.Items[i]).CancelamentoAcrescimo;
      ORDEM_DA := TR04VO(ListaR04.Items[i]).OrdemDescontoAcrescimo;
      NOME_CLI := TR04VO(ListaR04.Items[i]).Cliente;
      CNPJ_CPF := TR04VO(ListaR04.Items[i]).CPFCNPJ;
      // Registro R05 - FILHO
      ListaR05 := RegistroRContol.TabelaR05(TR04VO(ListaR04.Items[i]).Id);
      if Assigned(ListaR05) then
      begin
        for j := 0 to ListaR05.Count - 1 do
        begin
          with RegistroR05.New do
          begin
            NUM_ITEM := TR05VO(ListaR05.Items[j]).Item;
            COD_ITEM := TR05VO(ListaR05.Items[j]).GTIN;
            DESC_ITEM := TR05VO(ListaR05.Items[j]).DescricaoPDV;
            QTDE_ITEM := TR05VO(ListaR05.Items[j]).Quantidade;
            UN_MED := TR05VO(ListaR05.Items[j]).SiglaUnidade;
            VL_UNIT := TR05VO(ListaR05.Items[j]).ValorUnitario;
            DESCTO_ITEM := TR05VO(ListaR05.Items[j]).Desconto;
            ACRES_ITEM := TR05VO(ListaR05.Items[j]).Acrescimo;
            VL_TOT_ITEM := TR05VO(ListaR05.Items[j]).TotalItem;
            COD_TOT_PARC := TR05VO(ListaR05.Items[j]).TotalizadorParcial;
            IND_CANC := TR05VO(ListaR05.Items[j]).IndicadorCancelamento;
            QTDE_CANC := TR05VO(ListaR05.Items[j]).QuantidadeCancelada;
            VL_CANC := TR05VO(ListaR05.Items[j]).ValorCancelado;
            VL_CANC_ACRES := TR05VO(ListaR05.Items[j]).CancelamentoAcrescimo;
            IAT := TR05VO(ListaR05.Items[j]).IAT;
            IPPT := TR05VO(ListaR05.Items[j]).IPPT;
            QTDE_DECIMAL := TR05VO(ListaR05.Items[j]).CasasDecimaisQuantidade;
            VL_DECIMAL := TR05VO(ListaR05.Items[j]).CasasDecimaisValor;
          end;
        end;
      end;
    end;
  end;
  // Registro R06 e R07
  ListaR06 := RegistroRContol.TabelaR06;
  for i := 0 to ListaR06.Count - 1 do
  begin
    with FDataModule.ACBrPAF.PAF_R.RegistroR06.New do
    begin
      NUM_USU := TR06VO(ListaR06.Items[i]).IdOperador;
      COO := TR06VO(ListaR06.Items[i]).COO;
      GNF := TR06VO(ListaR06.Items[i]).GNF;
      GRG := TR06VO(ListaR06.Items[i]).GRG;
      CDC := TR06VO(ListaR06.Items[i]).CDC;
      DENOM := TR06VO(ListaR06.Items[i]).Denominacao;
      DT_FIN := StrToDateTime(TR06VO(ListaR06.Items[i]).DataEmissao);
      HR_FIN := StrToDateTime(TR06VO(ListaR06.Items[i]).HoraEmissao);
      // Registro R07 - FILHO
      ListaR07 := RegistroRContol.TabelaR07(TR06VO(ListaR06.Items[i]).Id);
      if Assigned(ListaR07) then
      begin
        for j := 0 to ListaR07.Count - 1 do
        begin
          with RegistroR07.New do
          begin
            CCF := TR07VO(ListaR07.Items[j]).CCF;
            MP := TR07VO(ListaR07.Items[j]).MeioPagamento;
            VL_PAGTO := TR07VO(ListaR07.Items[j]).ValorPagamento;
            IND_EST := TR07VO(ListaR07.Items[j]).IndicadorEstorno;
            VL_EST := TR07VO(ListaR07.Items[j]).ValorEstorno;
          end;
        end;
      end;
    end;
  end;
  FDataModule.ACBrPAF.SaveFileTXT_R('PAF_R.txt');
end;

procedure DAVEmitidos(DataIni: String; DataFim:String);
var
  ListaDAV: TObjectList<TDAVVO>;
  DAVControl: TDAVController;
  ImpressoraControl: TImpressoraController;
  Impressora: TImpressoraVO;
  D2: TRegistroD2;
  i:integer;
begin
  ListaDAV := DavControl.ListaDAVPeriodo(DataIni,DataFim);
  if Assigned(ListaDAV) then
  begin
    // registro D1
    UPAF.PreencherHeader(FDataModule.ACBrPAF.PAF_D.RegistroD1); // preencher header do arquivo
    // registro D2
    FDataModule.ACBrPAF.PAF_D.RegistroD2.Clear;
    //dados da impressora
    ImpressoraControl := TImpressoraController.Create;
    Impressora := ImpressoraControl.PegaImpressora(StrToInt(FDataModule.IdImpressora));
    for i := 0 to ListaDAV.Count - 1 do
    begin
       D2              := FDataModule.ACBrPAF.PAF_D.RegistroD2.New;
       D2.NUM_FAB      := Impressora.Serie;
       D2.MF_ADICIONAL := Impressora.MFD;
       D2.TIPO_ECF     := Impressora.Tipo;
       D2.MARCA_ECF    := Impressora.Marca;
       D2.MODELO_ECF   := Impressora.Modelo;
       D2.COO          := IntToStr(TDAVVO(ListaDAV.Items[i]).COO);
       D2.NUM_DAV      := StringOfChar('0',10-Length(IntToStr(TDAVVO(ListaDAV.Items[i]).Id))) + IntToStr(TDAVVO(ListaDAV.Items[i]).Id);
       D2.DT_DAV       := StrToDateTime(TDAVVO(ListaDAV.Items[i]).DataHoraEmissao);
       { TODO : Devemos configurar o titulo de cada DAV? Ou pelo menos deixar em aberto a configuração do titulo dos DAV (orçamento/pedido/etc)? }
       D2.TIT_DAV      := 'ORÇAMENTO';
       D2.VLT_DAV      := TDAVVO(ListaDAV.Items[i]).Valor;
    end;
    { TODO : Devemos configurar o caminho para salvar os arquivos ou salvamos na propria pasta da aplicação? }
    FDataModule.ACBrPAF.SaveFileTXT_D('PAF_D.txt');
  end;
end;

end.

{*******************************************************************************
Title: T2Ti ERP
Description: VO do produto.

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
unit ProdutoVO;

interface

type
  TProdutoVO = class
  private
    FID: Integer;
    FID_ECF_TRIBUTOS: Integer;
    FID_UNIDADE_PRODUTO: Integer;
    FGTIN: String;
    FCODIGO_INTERNO: String;
    FNOME: String;
    FDESCRICAO: String;
    FDESCRICAO_PDV: String;
    FVALOR_VENDA: Double;
    FQTD_ESTOQUE: Double;
    FESTOQUE_MIN: Double;
    FESTOQUE_MAX: Double;
    FIAT: String;
    FIPPT: String;
    FNCM: String;
    FUnidadeProduto: String;
    FSituacaoTributaria: String;
    FTotalizadorParcial: String;
    FECFICMS: String;
    FAliquotaICMS: Double;

  published
    property Id: Integer read FID write FID;
    property IdTributo: Integer read FID_ECF_TRIBUTOS write FID_ECF_TRIBUTOS;
    property IdUnidade: Integer read FID_UNIDADE_PRODUTO write FID_UNIDADE_PRODUTO;
    property GTIN: String read FGTIN write FGTIN;
    property CodigoInterno: String read FCODIGO_INTERNO write FCODIGO_INTERNO;
    property Nome: String read FNOME write FNOME;
    property Descricao: String read FDESCRICAO write FDESCRICAO;
    property DescricaoPDV: String read FDESCRICAO_PDV write FDESCRICAO_PDV;
    property ValorVenda: Double read FVALOR_VENDA write FVALOR_VENDA;
    property QtdeEstoque: Double read FQTD_ESTOQUE write FQTD_ESTOQUE;
    property EstoqueMinimo: Double read FESTOQUE_MIN write FESTOQUE_MIN;
    property EstoqueMaximo: Double read FESTOQUE_MAX write FESTOQUE_MAX;
    property IAT: String read FIAT write FIAT;
    property IPPT: String read FIPPT write FIPPT;
    property NCM: String read FNCM write FNCM;
    property UnidadeProduto: String read FUnidadeProduto write FUnidadeProduto;
    property SituacaoTributaria: String read FSituacaoTributaria write FSituacaoTributaria;
    property TotalizadorParcial: String read FTotalizadorParcial write FTotalizadorParcial;
    property ECFICMS: String read FECFICMS write FECFICMS;
    property AliquotaICMS: Double read FAliquotaICMS write FAliquotaICMS;
  end;

implementation

end.

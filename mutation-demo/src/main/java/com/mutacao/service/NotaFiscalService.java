package com.mutacao.service;


public class NotaFiscalService {

    private static final double ALIQUOTA_ISS  = 0.05;  
    private static final double ALIQUOTA_ICMS = 0.12;  
    private static final double ALIQUOTA_IPI  = 0.10;  

    public enum TipoServico { SERVICO, PRODUTO, PRODUTO_COM_IPI }

    public double calcularImpostos(double valorBase, TipoServico tipo) {
        if (valorBase < 0) throw new IllegalArgumentException("Valor não pode ser negativo.");
        return switch (tipo) {
            case SERVICO         -> valorBase * ALIQUOTA_ISS;
            case PRODUTO         -> valorBase * ALIQUOTA_ICMS;
            case PRODUTO_COM_IPI -> valorBase * (ALIQUOTA_ICMS + ALIQUOTA_IPI);
        };
    }

    public double calcularTotal(double valorBase, TipoServico tipo) {
        return valorBase + calcularImpostos(valorBase, tipo);
    }

    public boolean requerRetencao(double valorBase) {
        return valorBase > 5000.0;
    }

    public double calcularMultaAtraso(double valor, int diasAtraso) {
        if (diasAtraso <= 0) return 0.0;
        double multa = valor * 0.02;          // 2 % fixo
        double juros = valor * 0.001 * diasAtraso; // 0,1 % ao dia
        return multa + juros;
    }
}

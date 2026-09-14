package br.org.sesisenai.estoqueapi.enums;


public enum StatusPurchaseRequest {
    PENDING,    // Pendente de aprovação/compras
    APPROVED,   // Pedido realizado ao fornecedor
    RECEIVED,   // Material recebido no estoque
    CANCELLED   // Cancelada
}


package com.desafio.desafio.model;

public enum TransactionTypeEnum {

    CUSTOMER(1), SELLER(2);

    private final int typeNum;

    TransactionTypeEnum(int typeNum) {
        this.typeNum = typeNum;
    }
}

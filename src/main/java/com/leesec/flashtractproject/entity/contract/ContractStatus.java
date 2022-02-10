package com.leesec.flashtractproject.entity.contract;

public enum ContractStatus {
    SUBMITTED,
    IN_PROGRESS,
    APPROVED,
    VOID;

//    public static ContractStatus validateContractStatus(String status) {
//        try {
//            return ContractStatus.valueOf(status);
//        } catch (IllegalArgumentException e) {
//            throw new InvalidOperationException
//        }
//    }
}

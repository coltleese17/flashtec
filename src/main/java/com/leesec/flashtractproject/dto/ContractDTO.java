package com.leesec.flashtractproject.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ContractDTO {

    private Long id;

    private String contractStatus;

    private Long clientId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long vendorId;

    private String description;

    private double totalCost;

    private double remainingCost;

    private LocalDateTime createdDate;

    private LocalDateTime updated_date;

    public enum UserRole {
        CLIENT,
        VENDOR,
    }
}

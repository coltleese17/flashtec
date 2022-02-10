package com.leesec.flashtractproject.dto;

import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.entity.invoice.InvoiceStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceDTO {

    private Long contractId;

    private double contractAmount;

    private String contractDescription;

    private double cost;

    private int hours;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
}

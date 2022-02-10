package com.leesec.flashtractproject.dto;

import com.leesec.flashtractproject.entity.invoice.InvoiceStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InvoiceDTO {

    private Long id;

    private Long contractId;

    private double contractAmount;

    private String contractDescription;

    private double cost;

    private int hours;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String description;

    private InvoiceStatus status;
}

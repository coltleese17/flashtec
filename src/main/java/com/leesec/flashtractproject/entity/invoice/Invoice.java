package com.leesec.flashtractproject.entity.invoice;

import com.leesec.flashtractproject.dto.InvoiceDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long contractId;

    @Column
    private double contractAmount;

    @Column
    private String contractDescription;

    @Column
    private double cost;

    @Column
    private int hours;

    @Column
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public static Invoice mapInvoiceDTOtoInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO, invoice,"createdDate", "updatedDate");

        //For demo purposes, auto-approve the invoice
        invoice.setStatus(InvoiceStatus.APPROVED);

        return invoice;
    }
}

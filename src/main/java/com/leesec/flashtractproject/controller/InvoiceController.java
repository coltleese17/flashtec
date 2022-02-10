package com.leesec.flashtractproject.controller;

import com.leesec.flashtractproject.dto.InvoiceDTO;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import com.leesec.flashtractproject.service.InvoiceControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    private final InvoiceControllerService invoiceControllerService;


    public InvoiceController(InvoiceControllerService invoiceControllerService) {
        this.invoiceControllerService = invoiceControllerService;
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> postInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        System.out.println("controller");
        return ResponseEntity.ok(invoiceControllerService.saveInvoice(invoiceDTO));
    }
}

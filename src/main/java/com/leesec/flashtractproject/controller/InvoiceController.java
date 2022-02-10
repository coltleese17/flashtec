package com.leesec.flashtractproject.controller;

import com.leesec.flashtractproject.dto.InvoiceDTO;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import com.leesec.flashtractproject.service.InvoiceControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    private final InvoiceControllerService invoiceControllerService;

    public InvoiceController(InvoiceControllerService invoiceControllerService) {
        this.invoiceControllerService = invoiceControllerService;
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> postInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(invoiceControllerService.saveInvoice(invoiceDTO));
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceByID(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceControllerService.getInvoiceByID(invoiceId));
    }
}

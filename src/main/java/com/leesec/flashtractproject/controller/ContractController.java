package com.leesec.flashtractproject.controller;

import com.leesec.flashtractproject.dto.ContractDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import com.leesec.flashtractproject.service.ContractControllerService;
import com.leesec.flashtractproject.service.InvoiceControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {

    private final ContractControllerService contractControllerService;
    private final InvoiceControllerService invoiceControllerService;

    public ContractController(ContractControllerService contractControllerService,
                              InvoiceControllerService invoiceControllerService) {
        this.contractControllerService = contractControllerService;
        this.invoiceControllerService = invoiceControllerService;
    }

    @GetMapping(value = "/contract/{contractId}")
    public ResponseEntity<Contract> getContractByID(@PathVariable Long contractId) {
        return ResponseEntity.ok(contractControllerService.getContractByID(contractId));
    }

    @PostMapping(value = "/contract")
    public ResponseEntity<Contract> postContract(@RequestBody ContractDTO contractDTO) {
        return ResponseEntity.ok(contractControllerService.saveContract(contractDTO));
    }

    @GetMapping("/contract/{contractId}/invoice")
    public ResponseEntity<List<Invoice>> getAllInvoicesByContractID(@PathVariable Long contractId) {
        return ResponseEntity.ok(invoiceControllerService.getAllInvoicesByContractID(contractId));
    }
}
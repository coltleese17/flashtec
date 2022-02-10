package com.leesec.flashtractproject.controller;

import com.leesec.flashtractproject.dto.ContractDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.service.ContractControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {

    private final ContractControllerService contractControllerService;

    public ContractController(ContractControllerService contractControllerService) {
        this.contractControllerService = contractControllerService;
    }

    @GetMapping(value = "/contract/{contractId}")
    public ResponseEntity<Contract> getContract (@PathVariable Long contractId) {
        return ResponseEntity.ok(contractControllerService.getContractByID(contractId));
    }

    @PostMapping(value = "/contract")
    public ResponseEntity<Contract> postContract(@RequestBody ContractDTO contractDTO) {
        return ResponseEntity.ok(contractControllerService.saveContract(contractDTO));
    }
}
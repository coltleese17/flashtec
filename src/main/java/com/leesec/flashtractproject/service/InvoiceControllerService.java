package com.leesec.flashtractproject.service;

import com.leesec.flashtractproject.dto.InvoiceDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import com.leesec.flashtractproject.exception.ContractNotFoundException;
import com.leesec.flashtractproject.exception.DatabaseSaveFailedException;
import com.leesec.flashtractproject.exception.InvoiceCostExceedsContractLimitException;
import com.leesec.flashtractproject.exception.NoContractFoundForInvoiceContractIDException;
import com.leesec.flashtractproject.repository.ContractRepository;
import com.leesec.flashtractproject.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceControllerService {

    private final InvoiceRepository invoiceRepository;

    private final ContractControllerService contractControllerService;

    private final ContractRepository contractRepository;

    public InvoiceControllerService(InvoiceRepository invoiceRepository,
                                    ContractControllerService contractControllerService,
                                    ContractRepository contractRepository) {
        this.invoiceRepository = invoiceRepository;
        this.contractControllerService = contractControllerService;
        this.contractRepository = contractRepository;
    }
    public Invoice saveInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = Invoice.mapInvoiceDTOtoInvoice(invoiceDTO);

        Optional<Contract> contract = contractRepository.findById(invoice.getContractId());

        validate(contract.get(),invoice);

        populateInvoiceContractDetails(invoice,contract.get());

        //TODO: abstract the try catch to a DAO class
        try{
            invoiceRepository.save(invoice);
        } catch(RuntimeException e){
            //log.info("Save to DB failed for invoiceID:" + invoice.getId());
            throw new DatabaseSaveFailedException(e);
        }

        updateContractRemainingAmount(contract.get(), invoice);

        return invoice;
    }

    private void validate(Contract contract, Invoice invoice) {
        if (contract == null) {
            throw new NoContractFoundForInvoiceContractIDException();
        }
        if (invoice.getCost() > contract.getRemainingCost()){
            throw new InvoiceCostExceedsContractLimitException();
        }
    }

    private void updateContractRemainingAmount(Contract contract, Invoice invoice) {
        contract.setRemainingCost(contract.getRemainingCost() - invoice.getCost());
        try{
            contractRepository.save(contract);
        } catch(RuntimeException e){
            //log.info("Save to DB failed for contractID:" + contract.getId());
            throw new DatabaseSaveFailedException(e);
        }
    }

    private void populateInvoiceContractDetails(Invoice invoice, Contract contract) {
        invoice.setContractAmount(contract.getTotalCost());
        invoice.setContractDescription(contract.getDescription());
    }
}

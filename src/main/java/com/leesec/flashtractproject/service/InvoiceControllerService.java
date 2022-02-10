package com.leesec.flashtractproject.service;

import com.leesec.flashtractproject.dto.InvoiceDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import com.leesec.flashtractproject.exception.*;
import com.leesec.flashtractproject.repository.ContractRepository;
import com.leesec.flashtractproject.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/***
 * Due to time constraints I have not implemented validation nor user management,
 * though I would be happy to talk through my approaches for how I would implement
 * them in something like Spring Security or Shiro.
 *
 *  I've also only added a few tests but would with time would add more coverage with
 *  unit and integration tests.
 */

//@Log4j
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

    public Invoice saveInvoice(InvoiceDTO invoiceDTO) throws NoContractFoundForInvoiceContractIDException,
                                                                InvoiceCostExceedsContractLimitException,
                                                                DatabaseSaveFailedException {
        Invoice invoice = Invoice.mapInvoiceDTOtoInvoice(invoiceDTO);

        Optional<Contract> contract = contractRepository.findById(invoice.getContractId());

        validate(contract,invoice);

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

    private void validate(Optional<Contract> contract, Invoice invoice) {
        if (contract.isEmpty()) {
            throw new NoContractFoundForInvoiceContractIDException();
        }
        if (invoice.getCost() > contract.get().getRemainingCost()){
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

    public Invoice getInvoiceByID(Long invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);

        if (invoice.isEmpty()){
            throw new InvoiceNotFoundException();
        }

        return invoice.get();
    }

    public List<Invoice> getAllInvoicesByContractID(Long contractId) {
        List<Invoice> invoices = invoiceRepository.findAllByContractId(contractId);

        if (invoices == null || invoices.isEmpty()){
            throw new NoInvoicesForThisContractIDException();
        }

        return invoices;
    }
}

package com.leesec.flashtractproject.repository;

import com.leesec.flashtractproject.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    List<Invoice> findAllByContractId(Long contractId);
}

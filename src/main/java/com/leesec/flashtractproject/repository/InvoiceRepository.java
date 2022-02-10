package com.leesec.flashtractproject.repository;

import com.leesec.flashtractproject.entity.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}

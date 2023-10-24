package com.giriraaj.demo.service;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice findByInvoiceValue(String invoiceValue) {
        return invoiceRepository.findByInvValue(invoiceValue);
    }
}

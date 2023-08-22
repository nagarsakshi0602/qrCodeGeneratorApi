package com.giriraaj.demo.service;


import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> findByIrn(String irn) {
        return invoiceRepository.findByIrn(irn);
    }

    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice findByInvoiceValue(String invoiceValue) {
        return invoiceRepository.findByInvValue(invoiceValue);
    }
}

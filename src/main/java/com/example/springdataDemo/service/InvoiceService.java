package com.example.springdataDemo.service;


import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CSVService csvService;

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

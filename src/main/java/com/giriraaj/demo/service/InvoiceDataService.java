package com.giriraaj.demo.service;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.model.InvoiceData;
import com.giriraaj.demo.repository.InvoiceDataRepository;
import com.giriraaj.demo.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDataService {

    private InvoiceDataRepository invoiceDataRepository;

    public InvoiceDataService(InvoiceDataRepository invoiceDataRepository) {
        this.invoiceDataRepository = invoiceDataRepository;
    }

    public List<InvoiceData> getAllInvoices() {
        return invoiceDataRepository.findAll();
    }

    public Optional<InvoiceData> findById(Long id) {
        return invoiceDataRepository.findById(id);
    }

    public List<InvoiceData> findByInvoiceValue(String invoiceValue) {
        return invoiceDataRepository.findByInvValue(invoiceValue);
    }
}

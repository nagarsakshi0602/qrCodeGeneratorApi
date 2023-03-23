package com.example.springdataDemo.service;

import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.repository.InvoiceRepository;
import com.example.springdataDemo.utilities.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    InvoiceRepository invoiceRepository;


    public void saveInvoice(MultipartFile file) throws Exception {
        try {
            List<Invoice> namedColumn = (List<Invoice>) CSVHelper.csvToBean(file, Invoice.class);
            invoiceRepository.saveAll(namedColumn);

        } catch (IOException exception) {
            throw new RuntimeException("fail to store csv data: " + exception.getMessage());
        }
    }
}

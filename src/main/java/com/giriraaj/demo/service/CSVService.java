package com.giriraaj.demo.service;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.repository.InvoiceRepository;
import com.giriraaj.demo.utilities.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CSVService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public CSVService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }


    public void saveInvoice(MultipartFile file, Map<String, String[]> parameterMap){
       /* for (String name: parameterMap.keySet()) {
            String key = name.toString();
            String value = parameterMap.get(name).toString();
            Logger.getGlobal().log(System.Logger.Level.INFO,key + " " + value);
        }*/
        try {
            List<Invoice> namedColumn = (List<Invoice>) CSVHelper.csvToBean(file, Invoice.class);
            invoiceRepository.saveAll(namedColumn);

        } catch (Exception exception) {
            throw new RuntimeException("fail to store csv data: " + exception.getMessage());
        }
    }
}

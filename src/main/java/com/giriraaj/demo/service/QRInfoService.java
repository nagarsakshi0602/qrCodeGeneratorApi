package com.giriraaj.demo.service;


import com.giriraaj.demo.model.QRInfo;
import com.giriraaj.demo.repository.QRInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QRInfoService {
    private QRInfoRepository QRInfoRepository;

    @Autowired
    public QRInfoService(QRInfoRepository QRInfoRepository) {
        this.QRInfoRepository = QRInfoRepository;
    }

    public List<QRInfo> getAllInvoices() {
        return QRInfoRepository.findAll();
    }

    public List<QRInfo> findByIrn(String irn) {
        return QRInfoRepository.findByIrn(irn);
    }

    public Optional<QRInfo> findById(Long id) {
        return QRInfoRepository.findById(id);
    }

    public QRInfo findByInvoiceValue(String invoiceValue) {
        return QRInfoRepository.findByInvValue(invoiceValue);
    }
}

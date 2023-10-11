package com.giriraaj.demo.service;

import com.giriraaj.demo.model.QRInfo;
import com.giriraaj.demo.repository.QRInfoRepository;
import com.giriraaj.demo.utilities.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVService {
    private QRInfoRepository QRInfoRepository;

    @Autowired
    public CSVService(QRInfoRepository QRInfoRepository) {
        this.QRInfoRepository = QRInfoRepository;
    }


    public void saveInvoice(MultipartFile file) {
        try {
            List<QRInfo> namedColumn = (List<QRInfo>) CSVHelper.csvToBean(file, QRInfo.class);
            QRInfoRepository.saveAll(namedColumn);

        } catch (Exception exception) {
            throw new RuntimeException("fail to store csv data: " + exception.getMessage());
        }
    }
}

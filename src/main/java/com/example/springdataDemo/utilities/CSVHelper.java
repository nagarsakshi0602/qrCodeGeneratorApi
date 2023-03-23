package com.example.springdataDemo.utilities;


import com.example.springdataDemo.model.Invoice;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVHelper {


    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<?> csvToBean(MultipartFile file, Class clazz) throws Exception {
        return CSVHelper.convertToBean(file.getInputStream(), clazz);
    }

    private static List<?> convertToBean(InputStream is, Class clazz) {
        List<?> list;

        try (Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder<>(reader)
                    .withType(clazz)
                    .build();

            list = csvToBean.parse();
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse csv file: " + e.getMessage());
        }
    }
}

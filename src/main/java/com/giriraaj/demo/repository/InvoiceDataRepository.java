package com.giriraaj.demo.repository;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.model.InvoiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDataRepository extends JpaRepository<InvoiceData, Long> {

    List<InvoiceData> findByInvValue(String invValue);
}

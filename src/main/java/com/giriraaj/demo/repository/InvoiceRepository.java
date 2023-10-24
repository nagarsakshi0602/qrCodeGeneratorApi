package com.giriraaj.demo.repository;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.model.QRInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findByInvValue(String invValue);
}

package com.example.springdataDemo.repository;

import com.example.springdataDemo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByIrn(String irn);

    Invoice findByInvValue(String invValue);
}

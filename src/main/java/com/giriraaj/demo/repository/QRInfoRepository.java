package com.giriraaj.demo.repository;

import com.giriraaj.demo.model.QRInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRInfoRepository extends JpaRepository<QRInfo, Long> {

    List<QRInfo> findByIrn(String irn);

    QRInfo findByInvValue(String invValue);
}

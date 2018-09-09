package com.cvbank.repository;

import com.cvbank.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {


    List<CV> findCVByProfileId(Long l);
}

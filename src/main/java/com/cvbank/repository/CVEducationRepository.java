package com.cvbank.repository;


import com.cvbank.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CVEducationRepository extends JpaRepository<Education, Long> {
}

package com.t2404e.jobboard.repository;

import com.t2404e.jobboard.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
// JpaRepository đã cung cấp đủ các hàm CRUD cơ bản
//
}
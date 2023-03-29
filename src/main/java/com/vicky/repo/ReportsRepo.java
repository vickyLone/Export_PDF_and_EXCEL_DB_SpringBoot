package com.vicky.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicky.entity.Reports;

public interface ReportsRepo extends JpaRepository<Reports, Integer> {

}

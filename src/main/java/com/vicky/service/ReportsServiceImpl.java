package com.vicky.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicky.entity.Reports;
import com.vicky.repo.ReportsRepo;

@Service
public class ReportsServiceImpl implements ReportsService{
	
	@Autowired(required=true)
	ReportsRepo reportsRepo;

	@Override
	public List<Reports> getListOfReports() {
		// TODO Auto-generated method stub
		return reportsRepo.findAll();
	}


	

}

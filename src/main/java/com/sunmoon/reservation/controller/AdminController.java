package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.model.AdminInfo;
import com.sunmoon.reservation.service.AdminService;

@RestController
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/dentistInfo")
	public List<AdminInfo> getDentistInfo() {

		return adminService.dentistInfoService();

	}

	@RequestMapping("/patientInfo")
	public List<AdminInfo> getPatientInfo() {

		return adminService.PatientInfoService();

	}

	@RequestMapping("/clinicInfo")
	public List<AdminInfo> getClinicInfo() {

		return adminService.ClinicInfoService();

	}

	@RequestMapping("/sendCertify")
	public void getPostContent(@RequestBody Map<String, Object> data) {

		System.out.println("in controller");

		String c_name = data.get("c_name").toString();
		logger.info("c_name: " + c_name);

		adminService.CertifyService(c_name);

	}

}
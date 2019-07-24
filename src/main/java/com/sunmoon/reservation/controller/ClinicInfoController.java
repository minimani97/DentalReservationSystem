package com.sunmoon.reservation.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.service.ClinicInfoService;

@RestController
public class ClinicInfoController {
	private static final Logger logger = LoggerFactory.getLogger(ClinicInfoController.class);
	private ClinicInfoService clinicInfoService;
	
	@Autowired
	public void setClinicInfoService(ClinicInfoService clinicInfoService) {
		this.clinicInfoService = clinicInfoService;
	}
	
	
	@RequestMapping(value="/registerInfo")
	public void getPostContent(@RequestBody Map<String, Object> data)  {
		
		System.out.println("in controller");
		
		String c_name = data.get("c_name").toString();
		String c_tel = data.get("c_tel").toString();
		String addr_y = data.get("addr_y").toString();
		String addr_x = data.get("addr_x").toString();
		String c_license = data.get("c_license").toString();
		String c_address = data.get("c_address").toString();

		logger.info("data: "+addr_y+"  data2 :"+addr_x);
		
		clinicInfoService.setInfoService(c_name, c_tel, addr_y, addr_x, c_license, c_address);
	}

}


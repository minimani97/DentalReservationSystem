package com.sunmoon.reservation.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.service.RegisterService;

@RestController
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	private RegisterService registerService;
	
	@Autowired
	public void setMypageService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@ResponseBody
	@RequestMapping("/getClinicList")
	public List<ClinicMapInfo> getClinicList() {
		return registerService.getClinicList();
	}
	
	@ResponseBody
	@RequestMapping("/isDuplicateId")
	public int isDuplicateId(@RequestBody Map<String, Object> data) {
		String inputIdValue = data.get("id").toString();
		return registerService.isDuplicateId(inputIdValue);
	}
	
	@ResponseBody
	@RequestMapping("/patientUserRegister")
	public void patientUserRegister(@RequestBody Map<String, Object> data) {
		PatientInfo info = new PatientInfo();
		
		info.setId(data.get("id").toString());
		info.setPassword(data.get("password").toString());
		info.setName(data.get("name").toString());
		info.setBirth(data.get("birth").toString());
		info.setAddr(data.get("addr").toString());
		info.setTel(data.get("tel").toString());
		
		registerService.patientUserRegister(info);
	}
	
	@RequestMapping("/dentistUserRegister")
	public void dentistUserRegister(@RequestParam Map<String, Object> data, MultipartHttpServletRequest request) throws Exception {
		DentistInfo info = new DentistInfo();
		
		info.setId(data.get("id").toString());
		info.setPassword(data.get("password").toString());
		info.setD_name(data.get("name").toString());
		info.setD_tel(data.get("tel").toString());
		info.setEmail(data.get("email").toString());
		info.setC_code(Integer.parseInt(data.get("clinic").toString()));
		info.setF_codes(data.get("field").toString());
		info.setEdu(data.get("edu").toString());
		info.setCareer(data.get("career").toString());
		
		int index = 0;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root + "resources/dentistProfile/";
		
		logger.info("path : " + path);
		
		File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		
		MultipartFile profile = request.getFile("profile");
		
		if(profile == null) {
			info.setProfile("");
		} else {
			String fileName = data.get("id").toString() + "_profile_img." + profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);		
			info.setProfile(fileName);
			
			try {
				profile.transferTo(new File(path + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		registerService.dentistUserRegister(info);		
	}
}

package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.UserInfo;
import com.sunmoon.reservation.service.LoginService;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService;
	
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveUserInfo")
	public void saveUserInfo(@RequestBody Map<String, Object> data) {
		logger.info("gController_saveUserInfo Function");
		
		 UserInfo info = new UserInfo();
		 info.setId(data.get("id").toString());
		 info.setPassword(data.get("password").toString());
		 info.setGroup(data.get("group").toString());
		 
		 info.setP_name(data.get("p_name").toString());
		 info.setBirth(data.get("birth").toString());
		 info.setP_tel(data.get("p_tel").toString());
		 info.setAddr(data.get("addr").toString());
		 
		 loginService.saveUserInfo(info);
	}
	
	@RequestMapping("/goLogin")
	public List<UserInfo> goLogin(@RequestBody Map<String, Object> data) {
		logger.info("gController_goLogin Function");
		
		 UserInfo info = new UserInfo();
		 info.setId(data.get("id").toString());
		 info.setPassword(data.get("password").toString());
		 info.setGroup(data.get("group").toString());
		
		return loginService.goLogin(info);
	}
	
	@RequestMapping("/sessionP")
	public UserInfo sessionP(@RequestBody Map<String, Object> data, HttpServletRequest request) throws Exception {
		logger.info("sessionP controllesr OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info("여기는????????????????" + data.get("id").toString());
		 UserInfo userinfo = new UserInfo();
		 userinfo.setId(data.get("id").toString());
		 logger.info("여기는???");
		 logger.info("여기는 컨트롤러~~~~~~~~~~~~~~~~~~`:" + userinfo.getId());
		 
		 UserInfo DBuserInfo = new UserInfo();
         DBuserInfo = loginService.sessionP(userinfo.getId());
         //request.getSession().setAttribute("signedUser", DBuserInfo.getUser_num());
         request.getSession().setAttribute("id", DBuserInfo.getId());
       //  request.getSession().setAttribute("password", DBuserInfo.getPassword());
         request.getSession().setAttribute("p_code", DBuserInfo.getP_code());
         request.getSession().setAttribute("p_name", DBuserInfo.getP_name());
         request.getSession().setAttribute("birth", DBuserInfo.getBirth());
         request.getSession().setAttribute("addr", DBuserInfo.getAddr());
         request.getSession().setAttribute("p_tel", DBuserInfo.getP_tel());
         
         logger.info("DAO다녀와씀ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
         logger.info("DBuserInfo.getId~ : " + DBuserInfo.getId());
         
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("p_name"));
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("birth"));
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("addr"));
         
        
		
		return DBuserInfo;
	}
	
	@RequestMapping("/sessionD")
	public DentistInfo sessionD(@RequestBody Map<String, Object> data, HttpServletRequest request) throws Exception {
		logger.info("sessionDDDDD controllesr OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info("여기는????????????????" + data.get("id").toString());
		DentistInfo dentistInfo = new DentistInfo();
		dentistInfo.setId(data.get("id").toString());
		 logger.info("여기는???");
		 logger.info("여기는 컨트롤러~~~~~~~~~~~~~~~~~~`:" + dentistInfo.getId());
		 
		 DentistInfo DBdentistInfo = new DentistInfo();
		 DBdentistInfo = loginService.sessionD(dentistInfo.getId());
         //request.getSession().setAttribute("signedUser", DBuserInfo.getUser_num());
         request.getSession().setAttribute("id", DBdentistInfo.getId());
       //  request.getSession().setAttribute("password", DBuserInfo.getPassword());
         request.getSession().setAttribute("d_code", DBdentistInfo.getD_code());
         request.getSession().setAttribute("c_code", DBdentistInfo.getC_code());
         request.getSession().setAttribute("profile", DBdentistInfo.getProfile());
         request.getSession().setAttribute("d_name", DBdentistInfo.getD_name());
         request.getSession().setAttribute("d_tel", DBdentistInfo.getD_tel());
         request.getSession().setAttribute("eamil", DBdentistInfo.getEmail());
         request.getSession().setAttribute("edu", DBdentistInfo.getEdu());
         request.getSession().setAttribute("career", DBdentistInfo.getCareer());
         
         logger.info("DAO다녀와씀ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
         logger.info("DBdentistInfo.getId~ : " + DBdentistInfo.getId());
         
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("d_name"));
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("edu"));
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("career"));
         
        
		
		return DBdentistInfo;
	}
	
	@RequestMapping("/sessionA")
	public void sessionA(HttpServletRequest request) throws Exception {
		 request.getSession().setAttribute("id", "admin");
		 request.getSession().setAttribute("name", "관리자");
		 
		 logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("id"));
         logger.info("세션값~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + request.getSession().getAttribute("name"));
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	

}


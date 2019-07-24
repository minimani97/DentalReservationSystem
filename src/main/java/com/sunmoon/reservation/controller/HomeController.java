package com.sunmoon.reservation.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("home enter!");

		return "home";
	}

	@RequestMapping(value = "/homeForPatient", method = RequestMethod.GET)
	public String homeForPatient(Model model) {
		logger.info("homeForPatient enter!");

		return "home_patient";
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String reservationPage(Model model) {
		logger.info("reservationPage enter!");

		return "reservation";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String myPage(Model model) {
		logger.info("myPage enter!");

		return "mypage";
	}

	@RequestMapping(value = "/reservationList", method = RequestMethod.GET)
	public String reservationListPage(Model model) {
		logger.info("reservationListPage enter!");

		return "reservation_list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		logger.info("registerPage enter!");

		return "register";
	}

	@RequestMapping(value = "/schedule_check", method = RequestMethod.GET)
	public String myschedulePage(Model model) {
		logger.info("scheduleCheckPage enter!");

		return "schedule_check";
	}

	@RequestMapping(value = "/dentist_main", method = RequestMethod.GET)
	public String godentist_main(Model model) {
		logger.info("godentist_main enter!");

		return "dentist_main";
	}

	@RequestMapping(value = "/dentist_list", method = RequestMethod.GET)
	public String goDentist_list(Model model) {
		logger.info("goDentist_list enter!");

		return "dentist_list";
	}

	@RequestMapping(value = "/patient_list", method = RequestMethod.GET)
	public String goPatient_list(Model model) {
		logger.info("goPatient_list enter!");

		return "patient_list";
	}

	@RequestMapping(value = "/patient_record", method = RequestMethod.GET)
	public String goPatient_record(Model model) {
		logger.info("goPatient_record enter!");

		return "patient_record";
	}

	@RequestMapping(value = "/patient_transfer", method = RequestMethod.GET)
	public String goPatient_transfer(Model model) {
		logger.info("goPatient_transfer enter!");

		return "patient_transfer";
	}

	@RequestMapping(value = "/receiveTransfer", method = RequestMethod.GET)
	public String goReceiveTransfer(Model model) {
		logger.info("goReceiveTransfer enter!");

		return "receiveTransfer";
	}

	@RequestMapping(value = "/close_notice", method = RequestMethod.GET)
	public String goClose_notice(Model model) {
		logger.info("goClose_notice enter!");

		return "close_notice";
	}

	@RequestMapping(value = "/clinic_register", method = RequestMethod.GET)
	public String goClinic_register(Model model) {
		logger.info("Clinic_register enter!");

		return "clinic_register";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String goAdimin(Model model) {
		logger.info("goAdimin enter!");

		return "admin";
	}

	@RequestMapping(value = "/admin2", method = RequestMethod.GET)
	public String goAdimin2(Model model) {
		logger.info("goAdimin2 enter!");

		return "admin2";
	}

	@RequestMapping(value = "/admin3", method = RequestMethod.GET)
	public String goAdimin3(Model model) {
		logger.info("goAdimin3 enter!");

		return "admin3";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLoginPage(Model model) {
		return "login";
	}

}

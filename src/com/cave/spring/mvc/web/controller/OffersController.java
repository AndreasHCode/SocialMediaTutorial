package com.cave.spring.mvc.web.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cave.spring.mvc.web.dao.Offer;
import com.cave.spring.mvc.web.service.OffersService;

@Controller
public class OffersController {

	// @RequestMapping("/")
	// public ModelAndView showHome(HttpSession session) {
	// ModelAndView mv = new ModelAndView("home");
	// Map<String, Object> model = mv.getModel();
	// model.put("name", "<b>River</b>");
	//
	// session.setAttribute("name", "Boris");
	//
	// return mv;
	// }

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(OffersController.class);
	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {
		Offer offer = null;

		if (principal != null) {
			String name = principal.getName();

			offer = offersService.getOffer(name);
		}

		if (offer == null) {
			offer = new Offer();
		}

		model.addAttribute("offer", offer);

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		if (result.hasErrors()) {
			return "createoffer";
		}

		if (delete == null) {
			offersService.saveOrUpdateOffer(offer, principal.getName());
			return "offercreated";
		} else {
			offersService.delete(offer.getOfferId());
			return "offerdeleted";
		}

	}
}

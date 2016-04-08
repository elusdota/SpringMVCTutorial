package org.shawn.tutorials.spring.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private Log log = LogFactory.getLog(HomeController.class);

	@RequestMapping(path = "/home")
	public String getHomePage() {
		log.info("Get homepage.");
		return "/WEB-INF/pages/home.jsp";
	}
	@RequestMapping(path = "/index")
	public String getIndexPage() {
		log.info("Get homepage.");
		return "/WEB-INF/pages/index.jsp";
	}
}

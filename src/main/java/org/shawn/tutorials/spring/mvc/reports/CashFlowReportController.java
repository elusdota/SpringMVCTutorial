package org.shawn.tutorials.spring.mvc.reports;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.shawn.tutorials.spring.service.ReportAdapterService;
import org.shawn.tutorials.spring.service.ReportExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CashFlowReportController {

	@Autowired
	@Qualifier(value = "dataSource")
	private DataSource datasource;
	@Autowired
	@Qualifier(value = "dataSourceSecond")
	private DataSource dataSourceSecond;
	@Autowired
	private ReportExportService reportExportservice;
	@Autowired
	private ReportAdapterService reportAdapterService;

	@RequestMapping(path = "/CashFlow")
	public String getCashFlowReport(Model model, HttpServletRequest rq,
			HttpServletResponse response, @RequestParam("code") int code,
			@RequestParam("state") boolean state) throws SQLException {
		Map<?, ?> map = reportAdapterService.getParameters(rq, state,
				"CashFlow");
		model.addAttribute("format", "html");
		if (code == 10) {
			model.addAttribute("datasource", datasource);
		} else {
			model.addAttribute("datasource", dataSourceSecond);
		}
		System.out.println(code);
		Iterator<?> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			model.addAttribute((String) entry.getKey(), entry.getValue());
		}
		response.setContentType("text/html;charset=UTF-8");
		if (!state) {
			return "/WEB-INF/reports/CashFlow.jasper";
		} else {
			return "/WEB-INF/reports/CashFlowCompleted.jasper";
		}

	}

}

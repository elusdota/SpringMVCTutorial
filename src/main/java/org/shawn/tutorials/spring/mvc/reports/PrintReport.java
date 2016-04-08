package org.shawn.tutorials.spring.mvc.reports;

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
public class PrintReport {
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

	@RequestMapping(path = "/print")
	public String printreport(Model model,
			@RequestParam("filename") String filename,
			@RequestParam("type") String type, @RequestParam("code") int code,
			@RequestParam("state") boolean state, HttpServletRequest request,
			HttpServletResponse response) {
		String path = reportAdapterService.getPath(request);
		reportExportservice.buildJrprintFile(filename, type, path, request,
				code, state);
		String filepath;
		if (!state) {
			filepath = filename + ".jasper";
		} else {
			filepath = filename + "Completed.jasper";
		}
		Map<?, ?> map = (Map<?, ?>) reportAdapterService.getParameters(request,
				state, filename);
		Iterator<?> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			model.addAttribute((String) entry.getKey(), entry.getValue());
		}
		if (code == 10) {
			model.addAttribute("datasource", datasource);
		} else {
			model.addAttribute("datasource", dataSourceSecond);
		}
		model.addAttribute("format", "pdf");
		response.setContentType("text/html;charset=UTF-8");
		return "/WEB-INF/reports/" + filepath;
	}
}

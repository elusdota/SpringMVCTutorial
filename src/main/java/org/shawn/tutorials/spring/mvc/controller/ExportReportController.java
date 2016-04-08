package org.shawn.tutorials.spring.mvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shawn.tutorials.spring.service.ReportAdapterService;
import org.shawn.tutorials.spring.service.ReportExportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
public class ExportReportController {
	@Autowired
	private ReportExportService reportExportservice;
	@Autowired
	private ReportAdapterService reportAdapterService;

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void exportreport(@RequestParam("filename") String filename,
			@RequestParam("type") String type, @RequestParam("code") int code,
			@RequestParam("state") boolean state, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String path = reportAdapterService.getPath(request);
			reportExportservice.buildJrprintFile(filename, type, path, request,
					code,state);
			reportExportservice.buildExportFile(filename, type, path,state);
			String filepath = path + File.separator + filename + "." + type;
			File f = new File(filepath);
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ f.getName());
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			out.flush();
			br.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

	}
}

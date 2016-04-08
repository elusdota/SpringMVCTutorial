package org.shawn.tutorials.spring.service;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Service
public class ReportExportService {
	@Autowired
	@Qualifier(value = "dataSource")
	private DataSource datasource;
	@Autowired
	@Qualifier(value = "dataSourceSecond")
	private DataSource dataSourceSecond;
	@Autowired
	private ReportAdapterService reportAdapterService;

	/**
	 * 初始化生成jrprint文件，elus，2016.3.25
	 * 
	 * @param parameters
	 * @param pathjrprint
	 * @throws JRException
	 */
	public void fill(Map<String, Object> parameters, String pathjrprint,
			int code) throws JRException {
		try {
			if (code == 10) {
				JasperFillManager.fillReportToFile(pathjrprint, parameters,
						datasource.getConnection());
			} else {
				JasperFillManager.fillReportToFile(pathjrprint, parameters,
						dataSourceSecond.getConnection());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成docx文件,elus 2016.3.24
	 * 
	 * @throws JRException
	 */
	public void docx(String pathjrprint) throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File(pathjrprint);
		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);
		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".docx");
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
				destFile));
		exporter.exportReport();
		System.err.println("DOCX creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 * 生成pdf文件,elus,2016.3.24
	 * 
	 * @throws JRException
	 */
	public void pdf(String pathjrprint) throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(pathjrprint);
		System.err.println("PDF creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 * 生成excel工作表文件，elus,2016.3.24
	 * 
	 * @throws JRException
	 */
	public void xls(String pathjrprint) throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File(pathjrprint);
		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);
		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".xls");
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
				destFile));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		System.err.println("XLS creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 * 创建Jrprint文件,elus,2016.3.25
	 * 
	 * @param filename
	 * @param type
	 */
	public void buildJrprintFile(String filename, String type, String path,
			HttpServletRequest request, int code, boolean state) {

		try {
			if (!state) {
				fill(reportAdapterService.getParameters(request, false,
						filename),
						path + File.separator + filename + ".jasper", code);
			} else {
				fill(reportAdapterService
						.getParameters(request, true, filename),
						path + File.separator + filename + "Completed.jasper",
						code);
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成需要导出的文件，elus,2016.3.25
	 * 
	 * @param filename
	 * @param type
	 * @param path
	 */
	public void buildExportFile(String filename, String type, String path,
			boolean state) {
		String filepath;
		if (!state) {
			filepath = path + File.separator + filename + ".jrprint";
		} else {
			filepath = path + File.separator + filename + "Completed.jrprint";
		}
		try {
			switch (type) {
			case "xls":
				xls(filepath);
				break;
			case "pdf":
				pdf(filepath);
				break;
			case "docx":
				docx(filepath);
				break;
			default:
				System.out.println("typeother");
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

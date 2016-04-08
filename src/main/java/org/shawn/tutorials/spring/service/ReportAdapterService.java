package org.shawn.tutorials.spring.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportAdapterService {
	/**
	 * 获取报表参数，elus,2016.3.31
	 * 
	 * @param rq
	 * @param state
	 * @param filename
	 * @return
	 */
	public Map<String, Object> getParameters(HttpServletRequest rq,
			boolean state, String filename) {
		String path = getPath(rq) + File.separator + "subreport";
		Map<String, Object> parameters = new HashMap<String, Object>();
		switch (filename) {
		case "Profit":
			if (!state) {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "Profit_Subreport1.jasper"));
				// parameters.put("subReport2", getSubreport(path +
				// File.separator
				// + "ProfitSupplementary.jasper"));
				// parameters.put("subReport3", getSubreport(path +
				// File.separator
				// + "ProfitSupplementary.jasper"));
			} else {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "ProfitCompleted_Subreport1.jasper"));
			}
			return parameters;
		case "CashFlow":
			if (!state) {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "CashFlow_Subreport1.jasper"));
				parameters.put("subReport2", getSubreport(path + File.separator
						+ "CashFlowSupplementary.jasper"));
			} else {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "CashFlowCompleted_Subreport1.jasper"));
				// parameters.put("subReport2", getSubreport(path +
				// File.separator
				// + "CashFlowSupplementary.jasper"));
			}
			return parameters;
		case "AssetLiabilities":
			if (!state) {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "AssetLiabilities_Subreport1.jasper"));
			} else {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "AssetLiabilitiesCompleted_Subreport1.jasper"));
			}
			return parameters;
		case "AssetLiabilitiesContinued":
			if (!state) {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "AssetLiabilities_Subreport1.jasper"));
			} else {
				parameters.put("subReport1", getSubreport(path + File.separator
						+ "AssetLiabilitiesCompleted_Subreport1.jasper"));
			}
			return parameters;
		default:
			System.out.println("other");
		}
		return parameters;
	}

	/**
	 * 获取报表路劲，elus,2016.3.25
	 * 
	 * @param rq
	 * @return
	 */
	public String getPath(HttpServletRequest rq) {
		String path = rq
				.getSession()
				.getServletContext()
				.getRealPath(
						File.separator + "WEB-INF" + File.separator + "reports");
		return path;
	}

	/**
	 * 获取报表文件，elus,2016.3.25
	 * 
	 * @param filename
	 * @return
	 */
	public JasperReport getSubreport(String filename) {
		JasperReport subreport = null;
		try {
			subreport = (JasperReport) JRLoader.loadObjectFromFile(filename);
			return subreport;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}

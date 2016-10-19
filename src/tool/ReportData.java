package tool;

import java.util.LinkedHashMap;

/***
 *
 * @author KISSCO
 *
 */
public class ReportData {

	// default
	public String templetePath = "";
	public String reportId = "";
	public String exportFolder = "";
	public String exportFileNM = "";
	public int maxLstPerPage;
	public int reportType = 0;

	// for mail
	public String userId = "";
	public String password = "";
	public String smtpHost = "";
	public String smtpPort = "";

	// XMLファイルから取得するもの
	public LinkedHashMap<String, ReportItem> items = null;

	public ReportItem getItem(String key) {

		if (null == items.get(key)) {
			return null;
		}
		return items.get(key);
	}


	public String getTempletePath() {
		return System.getProperty("user.dir") + templetePath;
	}

	public String getExportFolder() {
		return System.getProperty("user.dir") + exportFolder;
	}
}

package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
	    String path = System.getProperty("user.dir")+"\\reports\\extentReport.html";
    	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    	reporter.config().setReportName("APT Enterprise UI Automation");
    	reporter.config().setDocumentTitle("APT Enterprise UI Automation Result");
    	extent = new ExtentReports();
    	extent.attachReporter(reporter);
    	extent.setSystemInfo("QA Team", "Akash Shinde");
    	return extent;
	}

}

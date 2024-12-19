package extentReportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportConfig 
{

	
	
public static ExtentReports getExtentreportObject()
{
	
	String reportpath = System.getProperty("user.dir")+"//reports//index.html";
	
	ExtentSparkReporter sparkReporter=new ExtentSparkReporter(reportpath);

	sparkReporter.config().setResourceCDN("<link rel=\"apple-touch-icon\" href=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png\">\r\n"
			+ "");
	sparkReporter.config().setDocumentTitle("TEST RESULTS");
	sparkReporter.config().setReportName("Rest Api Automation Tests");
	sparkReporter.config().setTheme(Theme.DARK);	
	
	ExtentReports extentReports=new ExtentReports();
	
	extentReports.attachReporter(sparkReporter);
	
	extentReports.setSystemInfo("Tester Name","Karan");
	
	return extentReports;

	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

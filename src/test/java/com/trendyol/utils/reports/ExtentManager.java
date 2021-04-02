package com.trendyol.utils.reports;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + File.separator + "target" + File.separator + "reports" + File.separator + "ExtentReportResults.html", true);
        }
        return extent;
    }
}
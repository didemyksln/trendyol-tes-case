package com.trendyol.utils;

import com.trendyol.TestCaseInformation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private FileUtils() {

    }

    public static List<TestCaseInformation> readFromCSVFile() {

        List<TestCaseInformation> testCaseInformations = new ArrayList<>();


        Reader reader = new InputStreamReader(FileUtils.class.getResourceAsStream("/csv/testCaseInfo.csv"));
        try {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().parse(reader);
            for (CSVRecord record : records) {
                String testCase = record.get("TestCaseName");
                String email = record.get("Email");
                String password = record.get("Password");
                testCaseInformations.add(new TestCaseInformation(testCase, email, password));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testCaseInformations;

    }
}

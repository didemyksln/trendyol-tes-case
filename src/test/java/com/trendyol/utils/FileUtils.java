package com.trendyol.utils;

import com.trendyol.TestCaseInformation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void writeToCSVFile(String filename, String[] headers, List<List<String>> contentList) throws IOException {
        String parentDirectory = System.getProperty("user.dir") + File.separator + "target" + File.separator + "cases";
        Files.createDirectories(Paths.get(parentDirectory));
        String filePath = parentDirectory + File.separator + filename;

        try (FileWriter csvWriter = new FileWriter(filePath);
             CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                     .withHeader(headers).withDelimiter(';'))) {
            for (List<String> content : contentList) {
                printer.printRecord(content);
            }
            csvWriter.flush();
        }
    }

}
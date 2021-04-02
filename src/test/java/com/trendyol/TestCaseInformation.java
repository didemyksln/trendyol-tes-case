package com.trendyol;

public class TestCaseInformation {

    private String testCase;
    private String email;
    private String password;

    public TestCaseInformation(String testCase, String email, String password) {
        this.testCase = testCase;
        this.email = email;
        this.password = password;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}

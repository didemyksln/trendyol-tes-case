package com.trendyolLogin;

public class UserInformation {

    public UserInformation(String testCase, String email, String password) {
        this.testCase = testCase;
        this.email = email;
        this.password = password;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String testCase;
    String email;
    String password;


}

package com.example.form.Form.response;

public class TestSuiteResponse {
	
	String responseMessage;

	public TestSuiteResponse(String string) {
		this.responseMessage = string;
	}
	
	public TestSuiteResponse() {
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}

package com.example.form.Form.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.Form.pojo.Form;
import com.example.form.Form.response.TestSuiteResponse;

@RestController
public class FormController {
	
	private boolean isElementPresent;
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/submit")
	public ResponseEntity<TestSuiteResponse> submitForm(@RequestBody Form formData) {
		System.out.println("Testing");
		List<String> selectedBrowsers = formData.getBrowsers();
		String url = formData.getUrl();
		TestSuiteResponse response = new TestSuiteResponse();
		for(String browser : selectedBrowsers) {
			openUrlInSelectedBrowser(url, browser);
			
		}
		response.setResponseMessage("Form Submitted Successfully");
		return ResponseEntity.ok(response);
	}
	
	private void openUrlInSelectedBrowser(String url, String browser) {
		WebDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
		if("chrome".equals(browser)) {
			System.setProperty(
		            "webdriver.chrome.driver",
		            "E:/Java_Projects/chromedriver-win64/chromedriver-win64/chromedriver.exe/");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	        driver.get(url);
	        boolean notExists = driver.findElements(By.className("lnXdpd")).isEmpty();
	        if(notExists) {
	        	isElementPresent = false;
	        } else {
	        	isElementPresent = true;
	        }

		}
		
		if("MicrosoftEdge".equals(browser)) {
			System.setProperty(
		            "webdriver.edge.driver",
		            "E:/Java_Projects/edgedriver_win64/msedgedriver.exe");
			driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.get(url);
	        boolean notExists = driver.findElements(By.className("lnXdpd")).isEmpty();
	        if(notExists) {
	        	isElementPresent = false;
	        } else {
	        	isElementPresent = true;
	        }
		}
	}
	
	
}

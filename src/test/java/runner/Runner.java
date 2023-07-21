package runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)


@CucumberOptions(features ={"src/test/resources/featureFiles/01LMS_Program.feature"}, 
tags= "@positive",
glue = {"stepDefinition"}, 
monochrome = true, 
plugin = {"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		
		"html:target/cucmberreport",
		
		})

public class Runner {

}

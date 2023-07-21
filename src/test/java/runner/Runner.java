package runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)


@CucumberOptions(features ={"src/test/resources/featureFiles/01LMS_Program.feature"}, 
tags= "@positive",
glue = {"stepDefinition"}, 
monochrome = true, 
plugin = {"pretty","html:target/cucumber.html" ,"json:target/cucumber.json",
		 "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		
		})

public class Runner {

}

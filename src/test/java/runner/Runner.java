package runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)


@CucumberOptions(features ={"src/test/resources/featureFiles"}, 
tags= "@schema",
glue = {"stepDefinition"}, 
monochrome = false, 
plugin = {"pretty","html:target/cucumber.html" ,"json:target/cucumber-reports/cucumber.json"
		})

public class Runner {

}

//"src/test/resources/featureFiles/01LMS_Program_PS.feature"

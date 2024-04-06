package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {".//FeatureFile"},
		//features= {"@target/rerun.txt"},
		glue="stepdefinitions",
		plugin={"pretty","html:Report/report.html",
				"rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}
		//dryRun=true
		//publish=true
		//tags="@sanity"
		)
public class TestRun {

}

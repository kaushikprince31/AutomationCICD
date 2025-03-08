package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="princesharma.stepDefinitions",
monochrome=true, tags="@Regression", plugin= {"html:target/cucumber.html"})
// Parameters used in Cucumber Options are features which is used to fetch feature file
// glue is used to fetch the stepDefinitions file means the code attached to the feature file
// By default Cucumber provides an Encrypted Output to convert it in readable mode we are using Monochrome
// To generate report we have to use plugin and define the report type and path in it

// By default Cucumber cannot scan the TestNG Assertions and TestNG libraries, for that we can use AbstractTestNGCucumberTests
// If we wanna run Junit tests then we don't have to use this class because Cucumber have built-in capability to do it
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	

}

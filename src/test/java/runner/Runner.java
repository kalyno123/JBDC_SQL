package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        plugin = {"pretty", "html: target/cucumber.html", "json:target/cucumber.json"},
        features = "src/test/resources",
        glue = {"stepDef", "utils"}
)

public class Runner extends AbstractTestNGCucumberTests {
    private static TestNGCucumberRunner testNGCucumberRunner;

    /* Before entire test suite we need to setup everything we will need. */
    @BeforeSuite(alwaysRun = true)
    public void setupSuite() throws IOException {

    } // End TestSetup

    /* Before class setup to initialize the cucumber runner */
    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    /* After class to tear down the runner for cucumber. */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        testNGCucumberRunner.finish();
    }

    /* After the entire test suite clean up rest assured */
    @AfterSuite(alwaysRun = true)
    public void cleanUp() {

        generatePrettyReportsLocally();

    } // end cleanUp

    private void generatePrettyReportsLocally() {
        String projectName = "b4-backend-automation";
        String reportFilePath = "target";
        File reportOutputDirectory = new File(reportFilePath);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();

        Reporter.log("\n**************************************************************************************************************" +
                "\n--------------------------------------   LOCAL PRETTY REPORT CREATED   ----------------------------------------" +
                "\nLink: http://localhost:63342/" + projectName + "/" + reportFilePath + "/cucumber-html-reports/overview-features.html" +
                "\n---------------------------------------------------------------------------------------------------------------" +
                "\n***************************************************************************************************************" );
    }
}

package stepDef.databaseStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import util.DBUtil;

import java.math.BigDecimal;

public class DatabaseStepDef {
    String classQuery;

    @Given("is able to connect to database")
    public void is_able_to_connect_to_database() {
        DBUtil.createDBConnection();
    }

    @When("User send the {string} to database")
    public void user_send_the_to_database(String query) {
        classQuery = query;
        DBUtil.executeQuery(query);
        System.out.println("Query execution is successful");
    }

    @Then("Validate the {int}")
    public void validate_the(Integer expectedSalary) {
        Object actualMinSalary = DBUtil.getCellValue(classQuery);
        Assert.assertEquals(actualMinSalary, new BigDecimal(expectedSalary));
    }
}

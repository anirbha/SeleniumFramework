package ui.StepDefinitions;

import ui.Actions.CompareAction;
import ui.Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CompareSteps {
    private WebDriver driver;
    private CompareAction compareAction;

    public CompareSteps()
    {
        this.driver= DriverManager.getDriver();
        this.compareAction=new CompareAction(driver);
    }
    
    @And("I enter beds in searchbox")
    public void iEnterBedsInSearchbox()  {
        compareAction.enterInSearchBox();
    }

    @Then("beds should be appeared in the search results")
    public void bedsShouldBeAppearedInTheSearchResults() {
        compareAction.verifyTheSearchHeader();
    }

    @And("I add two products to compare")
    public void iAddTwoProductsToCompare() {
        compareAction.addProductsToCompare();
        compareAction.retrieveProductsToCompareItems();
    }

    @When("I navigated to the compare page")
    public void iNavigatedToTheComparePage() {
        compareAction.navigationToComparePage();
        compareAction.validateComparePageNav();
    }

    @Then("the same two items should be present")
    public void theSameTwoItemsShouldBePresent()  {
        compareAction.validateComparePageItems();
        
    }

    @Then("I retrieve all the specifications values into excel")
    public void iRetrieveAllTheSpecificationsValuesIntoExcel()  {
        compareAction.writeProductSpecificationIntoExcel();
    }

    @And("I enter Recliners in the searchbox")
    public void iEnterReclinersInTheSearchbox() {
        compareAction.enterReclinerinTheSearchbox();
    }

    @Then("Recliners should be appeared in the searchbox")
    public void reclinersShouldBeAppearedInTheSearchbox() {
        compareAction.verifyTheSearchHeaderForRecliner();
    }

    @And("I add one product to compare")
    public void iAddOneProductToCompare() {
        compareAction.addProductToCompare();
    }
    @And("I add Mattress in the searchbox")
    public void iAddMattressInTheSearchbox() {
        compareAction.enterMattressInTheSearchBox();
    }

    @When("I try to add one product to compare")
    public void iTryToAddOneProductToCompare() {
        compareAction.tryToAddProductToCompare();
    }

    @Then("I should receive message ‘Can’t compare recliners with mattresses’")
    public void iShouldReceiveMessageCanTCompareReclinersWithMattresses() {
        compareAction.validateUnableToCompare();
    }

}

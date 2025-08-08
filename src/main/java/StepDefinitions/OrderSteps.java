package StepDefinitions;

import Actions.LoginActions;
import Actions.OrderActions;
import Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class OrderSteps {
    private WebDriver driver;
    private OrderActions orderActions;

    public OrderSteps()
    {
        this.driver= DriverManager.getDriver();
        this.orderActions =new OrderActions(driver);
    }


    @Then("I searched for the ‘Crockery units’")
    public void iSearchedForTheCrockeryUnits() throws IOException, ParseException {

        orderActions.navigateToCrokery();
        orderActions.validateNavToCrokeryPage();
    }

    @When("I select all the filters")
    public void iSelectAllTheFilters() throws IOException, ParseException, InterruptedException {
        orderActions.selectPriceFilter();
        orderActions.selectBrandFilter();
        orderActions.storageFilter();
        orderActions.materialFilter();
    }

    @Then("all the filters should be present")
    public void allTheFiltersShouldBePresent() throws IOException, ParseException {
        orderActions.validatePriceFilter();
        orderActions.validateBrandFilter();
        orderActions.validateStorageFilter();
        orderActions.validateMaterialFilter();
    }

    @And("I click on any three items")
    public void iClickOnAnyThreeItems() throws IOException, ParseException, InterruptedException {
        orderActions.clickOnAnyThreeItems();
    }

    @When("I checkout from the cart")
    public void iCheckoutFromTheCart() throws IOException, ParseException {
        orderActions.checkOutFromTheCart();
    }

    @Then("I add address into the shipping address")
    public void iAddAddressIntoTheShippingAddress() throws IOException, ParseException {
        orderActions.fillUpAddress();
    }

    @Then("I click on Save and Continue button to navigate to payment page")
    public void iClickOnSaveAndContinueButtonToNavigateToPaymentPage() throws IOException, ParseException {
        orderActions.clickSaveAndContBtn();
    }

    @When("I verify the items and the prices it should match with the earlier")
    public void iVerifyTheItemsAndThePricesItShouldMatchWithTheEarlier() throws IOException, ParseException, InterruptedException {
        orderActions.validateFinalPrice();
        orderActions.validateFinalItems();
    }
}

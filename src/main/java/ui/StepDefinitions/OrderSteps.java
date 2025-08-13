package ui.StepDefinitions;

import ui.Actions.OrderActions;
import ui.Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    public void iSearchedForTheCrockeryUnits()  {

        orderActions.navigateToCrokery();
        orderActions.validateNavToCrokeryPage();
    }

    @When("I select all the filters")
    public void iSelectAllTheFilters()  {
        orderActions.selectPriceFilter();
        orderActions.selectBrandFilter();
        orderActions.selectStorageFilter();
        orderActions.selectMaterialFilter();
    }

    @Then("all the filters should be present")
    public void allTheFiltersShouldBePresent()  {
        orderActions.validatePriceFilter();
        orderActions.validateBrandFilter();
        orderActions.validateStorageFilter();
        orderActions.validateMaterialFilter();
    }

    @And("I click on any three items")
    public void iClickOnAnyThreeItems() {
        orderActions.clickOnAnyThreeItems();
    }

    @When("I checkout from the cart")
    public void iCheckoutFromTheCart()  {
        orderActions.checkOutFromTheCart();
    }

    @Then("I add address into the shipping address")
    public void iAddAddressIntoTheShippingAddress() throws IOException {
        orderActions.fillUpAddress();
    }

    @Then("I click on Save and Continue button to navigate to payment page")
    public void iClickOnSaveAndContinueButtonToNavigateToPaymentPage()  {
        orderActions.clickSaveAndContBtn();
    }

    @When("I verify the items and the prices it should match with the earlier")
    public void iVerifyTheItemsAndThePricesItShouldMatchWithTheEarlier() throws InterruptedException {
        orderActions.validateFinalPrice();
        orderActions.validateFinalItems();
    }
}

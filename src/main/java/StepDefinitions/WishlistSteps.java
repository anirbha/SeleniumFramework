package StepDefinitions;

import Actions.LoginActions;
import Actions.WishListAction;
import Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class WishlistSteps {
    private WebDriver driver;
    private WishListAction wishlistactions;

    public WishlistSteps()
    {
        this.driver= DriverManager.getDriver();
        this.wishlistactions=new WishListAction(driver);
    }
    @When("I enter the searchitem in searchbox")
    public void iEnterTheSearchitemInSearchbox() throws IOException, ParseException {
        wishlistactions.enterSearchItem();
    }

    @Then("the searchitem should be appeared in the search results")
    public void theSearchitemShouldBeAppearedInTheSearchResults() throws IOException, ParseException {
        wishlistactions.verifyTheSearchHeader();
    }

    @And("I Apply all the filters")
    public void iApplyAllTheFilters() throws IOException, ParseException, InterruptedException {
        wishlistactions.clickOnAllFilters();
    }

    @And("I add three items in the wishlist")
    public void iAddThreeItemsInTheWishlist() throws IOException, ParseException, InterruptedException {
        wishlistactions.addWishlistItems();
        wishlistactions.retriveWishListedItems();
    }

    @When("I navigate wishlist page")
    public void iNavigateWishlistPage() throws IOException, ParseException {
        wishlistactions.navigateToWishListPage();
    }

    @Then("the wishlisted items should be present")
    public void theWishlistedItemsShouldBePresent() throws IOException, ParseException {
        wishlistactions.validationOfWishListedItems();
    }
}

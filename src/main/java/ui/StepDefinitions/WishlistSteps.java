package ui.StepDefinitions;

import ui.Actions.WishListAction;
import ui.Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class WishlistSteps {
    private WebDriver driver;
    private WishListAction wishlistactions;

    public WishlistSteps()
    {
        this.driver= DriverManager.getDriver();
        this.wishlistactions=new WishListAction(driver);
    }
    @When("I enter the searchitem in searchbox")
    public void iEnterTheSearchitemInSearchbox()  {
        wishlistactions.enterSearchItem();
    }

    @Then("the searchitem should be appeared in the search results")
    public void theSearchitemShouldBeAppearedInTheSearchResults()  {
        wishlistactions.verifyTheSearchHeader();
    }

    @And("I Apply all the filters")
    public void iApplyAllTheFilters()  {
        wishlistactions.clickOnAllFilters();
    }

    @And("I add three items in the wishlist")
    public void iAddThreeItemsInTheWishlist()  {
        wishlistactions.addWishlistItems();
        wishlistactions.retriveWishListedItems();
    }

    @When("I navigate wishlist page")
    public void iNavigateWishlistPage()  {
        wishlistactions.navigateToWishListPage();
    }

    @Then("the wishlisted items should be present")
    public void theWishlistedItemsShouldBePresent()  {
        wishlistactions.validationOfWishListedItems();
    }
}

package pages;

import org.openqa.selenium.By;

public class WishListPage {

public By SearchTextBox=By.xpath("//input[@id='search']");
public By HeaderTextSearch=By.xpath("//h2[@class='withsubtext']/span");
public By CatagoryFilter=By.xpath("//li[@class='item'][@data-group='category']");
public By FilterCheckboxes=By.xpath("//ul[@class='clearfix options']//li/input[@type='checkbox']");
public By ExcludeOutOfStockChkbox=By.id("filters_availability_In_Stock_Only");
public By Firstitem=By.xpath("//div[@class='product-title product-title-sofa-mattresses']");
public By ImageMousehover=By.xpath("//img[@class='product-img-default']");
public By WishlistsCheckbox=By.xpath("//span[contains(text(),'Add to Wishlist')]");
public By WishListedItems=By.xpath("//span[contains(text(),'Added to Wishlist')]/parent::*/parent::div//div/a/div/span[@class='name']");
public By WishListIcon=By.xpath("//span[@class='header-icon']");
public By WishListHeader=By.xpath("//h1[text()='My wishlist']");
public By WishLists=By.xpath("//span[@class='name']");

}

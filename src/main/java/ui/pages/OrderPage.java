package ui.pages;


import org.openqa.selenium.By;

public class OrderPage {

public By Dinning= By.xpath("//span[@class='topnav_itemname'][contains(text(),'Dining')]");
public By CrockeryItems=By.xpath("//span[contains(text(),'Crockery Units')]");
public By CrockeryHeader=By.xpath("//h1[contains(text(),'Crockery Units')]");
public By PriceFilter=By.xpath("//div[contains(text(),'Price')]");
public By PriceFilterRadioBtn=By.xpath("//div[contains(text(),'Price')]/following-sibling::div//ul/li[@class='row range-limits']//label");
public By SpecificPrice=By.xpath("//li[contains(text(),'₹14,938 - ₹36,545')]");
public By BrandFilter=By.xpath("//div[contains(text(),'Brand')]");
public By BrandFilterCheckboxes=By.xpath("//ul[@class='clearfix options'][@data-filter-name='brand_name']//li[not(contains(@class,'disabled'))]//input[@type='checkbox']");
public By BrandFilterNames=By.xpath("//ul[@class='clearfix options'][@data-filter-name='brand_name']//li[not(contains(@class,'disabled'))]/label");
public By StorageFilter=By.xpath("//div[contains(text(),'Storage')]");
public By StorageFilterCheckboxes=By.xpath("//ul[@class='clearfix options'][@data-filter-name='storage_type']//li[not(contains(@class,'disabled'))]//input[@type='checkbox']");
public By StorageFilterNames=By.xpath("//ul[@class='clearfix options'][@data-filter-name='storage_type']//li[not(contains(@class,'disabled'))]/label");
public By MaterialFilter=By.xpath("//div[contains(text(),'Material')]");
public By MaterialCheckbox=By.xpath("//ul[@class='clearfix options'][@data-filter-name='material']//li[not(contains(@class,'disabled'))]//input[@type='checkbox']");
public By MaterialFilterNames=By.xpath("//ul[@class='clearfix options'][@data-filter-name='material']//li[not(contains(@class,'disabled'))]/label");
public By AppliedPriceFilter=By.xpath("//div[@class='selected-options']//li[@data-filter-name='price']");
public By AppliedBrandFilter=By.xpath("//div[@class='selected-options']//li[@data-filter-name='brand_name']/span[@class='text']");
public By AppliedStorageFilter=By.xpath("//div[@class='selected-options']//li[@data-filter-name='storage_type']/span[@class='text']");
public By AppliedMaterialFilter=By.xpath("//div[@class='selected-options']//li[@data-filter-name='material']/span[@class='text']");
public By ItemsTobeClickable=By.xpath("//span[@class='name']");
public By ItemHeader=By.xpath("//h1[@class='product-title']");
public By AddToCartButton=By.xpath("//button[@id='add-to-cart-button']");
public By CartButton=By.xpath("//div[@id='cart-badge']");
public By CheckOutButton=By.xpath("//button[@name='checkout']");
public By PinCode=By.xpath("//div[@class='shipping_address']//input[@placeholder='PIN Code']");
public By Address=By.xpath("//div[@class='shipping_address']//textarea[@placeholder='Address']");
public By FirstName=By.xpath("//div[@class='shipping_address']//input[@placeholder='First Name']");
public By LastName=By.xpath("//div[@class='shipping_address']//input[@placeholder='Last Name']");
public By Mobile=By.xpath("//div[@class='shipping_address']//input[@placeholder='Enter 10 digit mobile number']");
public By ItemProductnames=By.xpath("//li//div[@class='product-name']");
public By totalpayableamount=By.xpath("//li[@class='grand-total']/span");
public By saveandcontbtn=By.xpath("//div[@class='form-buttons']/input[@id='address-form-submit']");
public By grandtotal=By.xpath("//li[@class='grand-total highlight']/span");
public By finalproductnames=By.xpath("//div[@class='product-name']");

}

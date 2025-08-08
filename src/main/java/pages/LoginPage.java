package pages;

import org.openqa.selenium.By;

public class LoginPage {


    public By UserProfileIcon=By.xpath("//span[@class='header-icon-link user-profile-icon']");
    public By LoginLink=By.xpath("//a[@class='login-link authentication_popup']");
    public By LoginButton=By.id("ul_site_login");
    public By EmailAddressTxtBoxLogin=By.xpath("//form[@name='login_form']//input[@id='spree_user_email']");
    public By PasswordTxtBoxLogin=By.xpath("//form[@name='login_form']//div/input[@id='spree_user_password']");
    public By ProfileLink=By.xpath("//ul/li/a[@id='header-icon-profile']");
    public By MyAccountHeader=By.xpath("//h1[@class='account_header']");
    public By RegisteredEmailAddress=By.xpath("//table[@id='info']/tbody/tr[2]/td[@class='profile-value']");
    public By RegisteredName=By.xpath("//table[@id='info']/tbody/tr/td[@class='profile-value']");
    public By RegisteredPhoneNumber=By.xpath("//table[@id='info']/tbody/tr[3]/td[@class='profile-value']");
    public By LogOutLink=By.id("logout");
    public By PhoneIcon=By.xpath("//a[@class='icofont-call inverted']");


}

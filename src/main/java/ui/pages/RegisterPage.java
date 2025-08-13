package ui.pages;

import org.openqa.selenium.By;

public class RegisterPage {

    public By SignUpLink = By.xpath("//a[@class='signup-link authentication_popup']");
    public By EmailAddressTxtBoxRegister =By.xpath("//form/input[@id='spree_user_email']");
    public By PasswordTxtBoxRegister= By.xpath("//input[@id='spree_user_password'][@tabindex='2']");
    public By SignUpButton= By.xpath("//input[@class='button primary signup']");
    public By EditButton=By.xpath("//a[@id='edit']");
    public By NameTextBox=By.xpath("//form/table/tbody//td/input[@id='user_name']");
    public By MobileNumberTextBox=By.xpath("//form/table/tbody//td/input[@id='user_mobile']");
    public By UpdateButton=By.xpath("//form//input[@value='Update']");

}

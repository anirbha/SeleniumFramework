package ui.pages;

import org.openqa.selenium.By;

public class ComparePage {

    public By SearchTextBox=By.xpath("//input[@id='search']");
    public By HeaderTextSearch=By.xpath("//h2[@class='withsubtext']/span");
    public By CompareButton=By.xpath("//button[@id='compare-cta']");
    public By ComparePageHeader=By.xpath("//span[@class='title content']");
    public By ComparePageItems=By.xpath("//div[@class='comparator-wrapper']//div[@class='prod-name']");
    public By PriceColumn=By.xpath("//div[@class='price comparable-prop']/div[@class='prop-label']");
    public By PriceValues=By.xpath("//div[@class='price comparable-prop']/div[@class='prop-value']");
    public By CantCompareText=By.cssSelector("div.message-content.content");

    public String addToCompareBtn="(//div[@class='otherinfo']/a[contains(text(),'Add')])";
    public String hoverElement="(//img[@class='product-img-default'])";
    public String comparableProperties="(//div[contains(@class,'comparable-properties')])";
    public String propLabels ="/descendant::div[@class='prop-label']";
    public String propValues ="/descendant::div[@class='prop-value']";
    public String productTitle="(//div[contains(@class,'product-title')])";
    public String productNames="/span[@class='name']";


}

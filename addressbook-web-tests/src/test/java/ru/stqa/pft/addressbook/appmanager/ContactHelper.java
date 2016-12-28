package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by uttabondarenko on 27.12.16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    click(By.name("theform"));
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobil());
    type(By.name("email"), contactData.getEmail());

  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    wd.switchTo().alert().accept(); // закрываем диалоговое окно
  }
}

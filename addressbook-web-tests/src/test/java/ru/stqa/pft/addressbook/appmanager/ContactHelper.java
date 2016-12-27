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
    typee(By.name("firstname"), contactData.getFirstname());
    typee(By.name("lastname"), contactData.getLastname());
    typee(By.name("address"), contactData.getAddress());
    click(By.name("theform"));
    typee(By.name("home"), contactData.getHomephone());
    typee(By.name("mobile"), contactData.getMobil());
    typee(By.name("email"), contactData.getEmail());

  }

  private void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }
}

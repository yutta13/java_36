package ru.stqa.pft.github.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.github.addressbook.model.ContactData;
import ru.stqa.pft.github.addressbook.model.Contacts;
import ru.stqa.pft.github.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by uttabondarenko on 27.12.16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitContactModificated() {
    wd.findElement(By.xpath("//div[@id='content']/form[1]/input[22]")).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobil());
    type(By.name("email"), contactData.getEmail());
  //  type(By.name("email2"), contactData.getEmail2());
  //  type(By.name("email3"), contactData.getEmail3());
 //   attach(By.name("photo"), contactData.getPhoto());

   if (creation) {
     if (contactData.getGroups().size() > 0 ) {
       Assert.assertTrue(contactData.getGroups().size() == 1);
       new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
     }
   } else {
     Assert.assertFalse(isElementPresent(By.name("new_group")));
   }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactByID(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "' ]")).click();
  }

  public void modify(ContactData contact) {
    selectContactByID(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModificated();
    contactCache =null;
    returntoHomePage();
  }

  public void deleteContact() {
    wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    wd.switchTo().alert().accept(); // закрываем диалоговое окно
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void create(ContactData contact) {
    returntoHomePage();
    initContactCreation();
    fillContactForm(contact, true);
    saveContact();
    contactCache =null;
    returntoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactByID(contact.getId());
    deleteContact();
    contactCache =null;
    returntoHomePage();
  }

  public void returntoHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int Count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td")); //разбиваем на столбцы
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();


     contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));

    }
    return new Contacts(contactCache);

  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname= wd.findElement(By.name("lastname")).getAttribute("value");
      String address= wd.findElement(By.name("address")).getAttribute("value");
      String home= wd.findElement(By.name("home")).getAttribute("value");
      String mobil= wd.findElement(By.name("mobile")).getAttribute("value");
      String email= wd.findElement(By.name("email")).getAttribute("value");
      String email2= wd.findElement(By.name("email2")).getAttribute("value");
      String email3= wd.findElement(By.name("email3")).getAttribute("value");

      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
              .withHomephone(home).withMobil(mobil).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

  }

  public String infoFromDetailForm(ContactData contact) {
    initContactDetailsById(contact.getId());
    String allDetails = wd.findElement(By.id("content")).getText();
    return allDetails;
  }

  private void initContactModificationById(int id) {

    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row =  checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();}

  private void initContactDetailsById(int id) {

    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row =  checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();

//второй вариант
  //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();

//третий вариант
  //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();

   // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }


  public void moveIntoGroup(ContactData movingContact, GroupData selectedGroup) {
    selectContactByID(movingContact.getId());
    selectWithGroup(selectedGroup);
    click(By.name("add"));
  }

  private void selectWithGroup(GroupData selectedGroup) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(selectedGroup.getName());
  }

  public void deleteContactFromGroup(ContactData movingContact, GroupData selectedGroup) {
    selectGroupWithFilter(selectedGroup);
    selectContactByID(movingContact.getId());
    click(By.name("remove"));
  }

  private void selectGroupWithFilter(GroupData selectedGroup) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(selectedGroup.getName());
  }

}





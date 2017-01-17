package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by uttabondarenko on 28.12.16.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void contactModificationTests() {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Yutta", "Bondarenko", "Moscow", null, "89992223311", "email1@mail.ru", "test1"));
    }

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Yutta", "Bondarenko", "Moscow", "495-77777", "89992223311", "email1@mail.ru", null), false);
    app.getContactHelper().submitContactModificated();
    app.getContactHelper().returntoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }


}

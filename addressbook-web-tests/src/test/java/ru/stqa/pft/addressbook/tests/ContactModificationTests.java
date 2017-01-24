package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by uttabondarenko on 28.12.16.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void contactModificationTests() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
    .withId(modifiedContact.getId()).withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1") ;
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }



}

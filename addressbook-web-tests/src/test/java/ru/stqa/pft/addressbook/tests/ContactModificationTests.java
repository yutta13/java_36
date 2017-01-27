package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
    .withId(modifiedContact.getId()).withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1") ;
    app.contact().modify(contact);
    assertThat(app.contact().Count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }



}

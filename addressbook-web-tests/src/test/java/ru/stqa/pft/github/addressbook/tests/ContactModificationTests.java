package ru.stqa.pft.github.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.github.addressbook.model.ContactData;
import ru.stqa.pft.github.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by uttabondarenko on 28.12.16.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
         app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Yutta"));
    }
  }

  @Test
  public void contactModificationTests() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Yutta").withLastname("Lastname");
    app.goTo().HomePage();
    app.contact().modify(contact);

    assertThat(app.contact().Count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUI();
  }



}

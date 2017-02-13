package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

/**
 * Created by uttabondarenko on 13.02.17.
 */
public class testContactFromGroup extends TestBase {
  @BeforeMethod
  public void ContactPreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("testName11"));
    }
  }

  @BeforeMethod
  public void GroupPreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @BeforeMethod
  public void ContactInGroupPreconditions() {
    Contacts contactsList = app.db().contacts();
    for (ContactData contact : contactsList) {
      if (contact.getGroups().size() == 0) {
        app.goTo().HomePage();
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().moveIntoGroup(contact, selectedGroup);
      }
    }
  }


  @Test
  public void testContactFromGroup() {
    app.goTo().HomePage();
    Contacts contactsList = app.db().contacts();
    ContactData movingContact = contactsList.iterator().next();
    Groups movingContactGroups = movingContact.getGroups();
    GroupData selectedGroup = movingContactGroups.iterator().next();
    app.contact().deleteContactFromGroup(movingContact, selectedGroup);
    Contacts contactsListAfter = app.db().contacts();
    ContactData movedContact = new ContactData();
    for (ContactData contact : contactsListAfter) {
      if (contact.equals(movingContact)) {
        movedContact = contact;
      }
    }
    app.goTo().HomePage();
    Groups movedContactGroups = movedContact.getGroups();
    assertThat(movedContactGroups, equalTo(movingContactGroups.without(selectedGroup)));
  }
}
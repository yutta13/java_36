package ru.stqa.pft.github.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.github.addressbook.model.ContactData;
import ru.stqa.pft.github.addressbook.model.Contacts;
import ru.stqa.pft.github.addressbook.model.GroupData;
import ru.stqa.pft.github.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by uttabondarenko on 13.02.17.
 */
public class testContactToGroup extends TestBase{

    @BeforeMethod
    public void ensureContactPreconditions() {
      if (app.db().contacts().size() == 0) {
        app.goTo().HomePage();
        app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko"));
      }
    }

    @BeforeMethod
    public void ensureGroupPreconditions() {
      if (app.db().groups().size() == 0) {
        app.goTo().GroupPage();
        app.group().create(new GroupData().withName("test1"));
      }
    }

  @Test
  public void testContactToGroup() {
    app.goTo().HomePage();
    Contacts contactsList = app.db().contacts();
    ContactData movingContact = contactsList.iterator().next();
    Groups movingContactGroups = movingContact.getGroups();
    Groups groups = app.db().groups();
    GroupData selectedGroup = groups.iterator().next();
    app.contact().moveIntoGroup(movingContact, selectedGroup);
    app.goTo().HomePage();
    Contacts contactsListAfter = app.db().contacts();
    ContactData movedContact = new ContactData();
    for (ContactData contact : contactsListAfter) {
      if (contact.equals(movingContact)) {
        movedContact = contact;
      }
    }
    Groups movedContactGroups = movedContact.getGroups();
    assertThat(movedContactGroups, equalTo(movingContactGroups.withAdded(selectedGroup)));

  }
}

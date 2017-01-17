package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;


public class ContactCreation extends TestBase {


    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData ("Yutta", "Bondarenko", "Moscow", null, "89992223311", "email1@mail.ru", "test1") ;
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().saveContact();
        app.getContactHelper().returntoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(contact);
        int max = 0;
        for (ContactData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}

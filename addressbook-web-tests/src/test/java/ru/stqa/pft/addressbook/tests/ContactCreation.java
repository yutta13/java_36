package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreation extends TestBase {


    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Yutta", "Bondarenko", "Moscow", null, "89992223311", "email1@mail.ru", "test1"), true);
        app.getContactHelper().saveContact();
        app.getContactHelper().returntoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
    }

}

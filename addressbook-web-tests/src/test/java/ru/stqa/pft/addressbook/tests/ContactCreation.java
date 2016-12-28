package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;



public class ContactCreation extends TestBase {


    @Test
    public void testContactCreation() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Yutta", "Bondarenko", "Moscow", "495-77777", "89992223311", "email1@mail.ru"));
        app.getContactHelper().saveContact();
    }

}

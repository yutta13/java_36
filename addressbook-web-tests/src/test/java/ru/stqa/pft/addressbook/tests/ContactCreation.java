package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreation extends TestBase{


    @Test
    public void testContactCreation() {

        app.initContactCreation();
        app.fillContactForm(new ContactData("Yutta", "Bondarenko", "Moscow, Zemlyanoy Val 50/8", "495-77777", "89992223311", "email1@mail.ru"));
        app.saveContact();
    }

}

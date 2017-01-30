package ru.stqa.pft.addressbook.tests;

/**
 * Created by uttabondarenko on 30.01.17.
 */

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCompareTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1"));
    }
  }


  @Test
  public void testCompareTest() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactCompareTest::cleaned)
            .collect(Collectors.joining("\n"));

  }
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobil())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactCompareTest::cleaned)
            .collect(Collectors.joining("\n"));

  }
  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactCompareTest::cleaned)
            .collect(Collectors.joining("\n"));
  }
  static String cleaned(String data) {
    return data.replaceAll("\\s"," ").replaceAll("[-()]", " ");
  }




}

package ru.stqa.pft.github.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.github.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by uttabondarenko on 30.01.17.
 */
public class ContactDetailTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru"));
    }
  }

  @Test (enabled = false)
  public void testDetailTest() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    String contactInfoFromDetailForm = app.contact().infoFromDetailForm(contact);
    app.goTo().HomePage();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(cleaned(contactInfoFromDetailForm), equalTo(mergeContact(contactInfoFromEditForm)));

  }
  private String mergeContact(ContactData contact) {

        return Arrays.asList(contact.getFirstname(),contact.getLastname(),contact.getAddress(),
        contact.getHomephone(),contact.getMobil(),contact.getEmail(),contact.getEmail2(),contact.getEmail3())
        .stream().filter((s) ->! s.equals(""))
        .map(ContactDetailTest::cleaned)
        .collect(Collectors.joining(""));


        }

  static String cleaned(String details) {
    return details.replaceAll("\\s","").replaceAll("[-()]", "").replaceAll("\\(www(.*).\\)|H:|M:|","");
  }
}
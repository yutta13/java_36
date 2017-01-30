package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by uttabondarenko on 30.01.17.
 */
  public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1"));
      }
    }

    @Test
    public void testContactAddress() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

    }


  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactAddressTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  static String cleaned(String address) {
    return address.replaceAll("\\s"," ").replaceAll("[-()]", " ");
  }

  }
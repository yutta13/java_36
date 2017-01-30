package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by uttabondarenko on 27.01.17.
 */
public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withFirstname("Yutta").withLastname("Bondarenko").withAddress("Moscow").withHomephone("89992223311").withEmail("email1@mail.ru").withGroup("test1"));
      }
    }

    @Test
    public void testContactEmails() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactEmailTests::cleaned)
              .collect(Collectors.joining("\n"));

    }

    static String cleaned(String emails) {
      return emails.replaceAll("\\s","").replaceAll("[-()]", "");
    }

  }


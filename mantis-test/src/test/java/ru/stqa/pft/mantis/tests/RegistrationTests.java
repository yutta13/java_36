package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by uttabondarenko on 16.02.17.
 */
public class RegistrationTests extends TestBase{


  @Test
  public void testRegistration() throws IOException, MessagingException {
    app.registration().start("user1", "user1@email.ru");
}
}
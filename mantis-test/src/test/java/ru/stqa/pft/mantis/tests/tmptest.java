package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by uttabondarenko on 16.02.17.
 */
public class tmptest extends TestBase{


  @Test
  public void testtmp() throws IOException, MessagingException {
    String password = "password";
    UserData user = new UserData().withUsername("Yutta").withEmail("yutta@localhost.ru");

    app.navigation().uiLogin();
    app.navigation().manageUsersPage();
    app.navigation().selectUser(user);
//    app.navigation().resetPassowrd();
//    app.registration().logout();
    System.out.println("OK");

  }

}
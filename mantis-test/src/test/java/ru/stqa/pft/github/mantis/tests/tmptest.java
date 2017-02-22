package ru.stqa.pft.github.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.github.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;

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
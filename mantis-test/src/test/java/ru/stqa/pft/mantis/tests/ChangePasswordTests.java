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
public class ChangePasswordTests extends TestBase{

 @BeforeMethod
   public void startMailServer() {
     app.mail().start();
   }


  @Test
  public void testUserPasswordChange() throws IOException, MessagingException {
    String password = "password";
    UserData user = new UserData().withUsername("Yutta").withEmail("yutta@localhost.ru");

    app.navigation().uiLogin();
    app.navigation().manageUsersPage();
    app.navigation().selectUser(user);
    app.navigation().resetPassowrd();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 33000);
    String confirmLink = findConfirmationLink(mailMessages, user.getEmail());
    app.registration().logout();
    app.registration().finish(confirmLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));
    app.mail().stop();

  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

 @AfterMethod(alwaysRun = true)
 public void stopMailServer() {
    app.mail().stop();
  }

}
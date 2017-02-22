package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import java.io.IOException;
import org.testng.Assert;

import javax.xml.rpc.ServiceException;


/**
 * Created by uttabondarenko on 15.02.17.
 */
public class LoginTests extends TestBase {

  @Test

  public void testLogin() throws IOException, ServiceException {
    try {
      skipIfBugifyIssueNotFixed(10);
    } catch (SkipException e) {
      e.printStackTrace();
    }
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login("administrator", "root1"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));

  }

}

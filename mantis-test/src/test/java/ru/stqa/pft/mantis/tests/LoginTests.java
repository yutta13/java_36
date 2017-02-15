package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import java.io.IOException;
import org.testng.Assert;


/**
 * Created by uttabondarenko on 15.02.17.
 */
public class LoginTests extends TestBase {

  @Test

  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login("administrator", "root"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));

  }

}

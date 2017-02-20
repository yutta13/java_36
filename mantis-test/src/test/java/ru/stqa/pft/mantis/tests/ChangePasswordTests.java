package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by uttabondarenko on 16.02.17.
 */
public class ChangePasswordTests extends TestBase{


  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testUserPasswordChange() {
    UserData user = new UserData().withUsername("Java Training").withEmail("javatraining2@localhost");
    app.navigation().uiLogin();
    app.navigation().openManageUserPage();
    app.navigation().selectUser(user);
    app.navigation().resetPassowrd();
    System.out.println();
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
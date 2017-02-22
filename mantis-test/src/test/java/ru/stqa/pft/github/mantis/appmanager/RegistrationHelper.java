package ru.stqa.pft.github.mantis.appmanager;

import org.openqa.selenium.By;


/**
 * Created by uttabondarenko on 16.02.17.
 */
public class RegistrationHelper  extends HelperBase {


  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
 }


  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void logout() {
    click(By.cssSelector("a[href*='logout_page.php']"));
  }
}

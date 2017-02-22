package ru.stqa.pft.github.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.pft.github.mantis.model.UserData;

/**
 * Created by uttabondarenko on 20.02.17.
 */

public class NavigationHelper extends HelperBase {


  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void uiLogin() {
    wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    type(By.xpath("//input[@id='username']"), app.getProperty("web.adminLogin"));
    type(By.xpath("//input[@type='password']"), app.getProperty("web.adminPassword"));
    wd.findElement(By.xpath("//input[@type='submit']")).click();
  }
  public void managePage() {
    wd.findElement(By.xpath("//a[@href='/mantisbt-2.1.0/manage_user_page.php']")).click();

  }

  public void manageUsersPage(){
    managePage();
    click(By.linkText("Управление пользователями"));

  }


  public void selectUser(UserData user) {
    String locator = String.format("//a[contains(@href, 'user_id=%s')]", user.getId());
    click(By.xpath(locator));
//    wait.until(ExpectedConditions.urlContains(String.format("manage_user_edit_page.php?user_id=%s", user.getId()))); }
  }


  public void resetPassowrd() {
    click(By.cssSelector("input[value='Сбросить пароль']"));
    wait.until(ExpectedConditions.urlContains("manage_user_page.php"));}

}
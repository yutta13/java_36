package ru.stqa.pft.github.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by uttabondarenko on 27.12.16.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type(By.name("pass"), password);
    type(By.name("user"), username);

    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}

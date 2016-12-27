package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by uttabondarenko on 27.12.16.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    typee(By.name("pass"), password);
    typee(By.name("user"), username);

    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}

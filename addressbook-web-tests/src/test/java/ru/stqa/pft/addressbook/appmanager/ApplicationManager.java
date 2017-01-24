package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by uttabondarenko on 27.12.16.
 */
public class ApplicationManager {
  private final String browser;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }


  public void init() {
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.SAFARI)) {
      wd = new SafariDriver();
    }


    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }



  public GroupHelper group() {
    return groupHelper;
  }
  public ContactHelper contact() {
    return contactHelper;
  }
  public NavigationHelper goTo() {
    return navigationHelper;
  }
}

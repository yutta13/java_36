package ru.stqa.pft.github.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.github.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.github.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


/**
 * Created by uttabondarenko on 27.12.16.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    Issue issue = app.soap().getIssueById(issueId);
    if ((issue.getStatus().equals("resolved")) || (issue.getStatus().equals("closed")) ||
            (issue.getResolution().equals("fixed"))) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isBugifyIssueOpen(int issueId) throws IOException, ServiceException {
    String issueStatus = app.getBugifyHelper().getIssueStatusByID(issueId);
    if ((issueStatus.equals("Resolved")) || (issueStatus.equals("Closed")) ||
            (issueStatus.equals("Fixed"))) {
      return false;
    }
    return true;
  }

  public void skipIfBugifyIssueNotFixed(int issueId) throws IOException, ServiceException {
    if (isBugifyIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
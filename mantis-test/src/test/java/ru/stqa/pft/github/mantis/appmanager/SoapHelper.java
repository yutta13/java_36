package ru.stqa.pft.github.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.github.mantis.model.Project;
import ru.stqa.pft.github.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by uttabondarenko on 22.02.17.
 */


public class SoapHelper {

  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root1");

    return Arrays.asList(projects).stream().map((p) -> new Project()
            .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());


  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL("http://localhost/mantisbt-2.1.0/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root1", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData= new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root1", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root1", issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue()))
            .withName(createdIssueData.getProject().getName());

  }

  public Issue getIssueById(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    //открываем соединение
    MantisConnectPortType mantisConnectPort = getMantisConnect();
    IssueData issue = mantisConnectPort.mc_issue_get("administrator", "root1", BigInteger.valueOf(issueId));
    ObjectRef status = issue.getStatus();
    status.getName();
    return  new Issue().withId(issue.getId().intValue()).withSummary(issue.getSummary()).
            withDescription(issue.getDescription()).withStatus(issue.getStatus().getName()).
            withResolution(issue.getResolution().getName()).withProject(new Project().
            withId(issue.getProject().getId().intValue()).
            withName(issue.getProject().getName()));
  }
}

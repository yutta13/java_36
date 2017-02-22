package ru.stqa.pft.github.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;

import java.io.IOException;

/**
 * Created by uttabondarenko on 22.02.17.
 */
public class BugifyHelper {

  private ApplicationManager app;

  public BugifyHelper(ApplicationManager app) {
    this.app = app;
  }

  public String getIssueStatusByID(int issueId) throws IOException {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonObject issue = issues.getAsJsonArray().get(0).getAsJsonObject();
    return issue.get("state_name").getAsString();
  }


}
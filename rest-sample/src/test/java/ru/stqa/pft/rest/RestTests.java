package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import org.apache.http.client.fluent.Executor;
import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;
import static org.testng.Assert.assertEquals;

/**
 * Created by uttabondarenko on 22.02.17.
 */
public class RestTests {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue()
            .withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);

  }


  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().
            execute(Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return org.apache.http.client.fluent.Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().
            execute(Post("http://demo.bugify.com/api/issues.json")
                            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject())
                            ,new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }




}

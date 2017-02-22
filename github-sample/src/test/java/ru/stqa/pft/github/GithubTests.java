package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by uttabondarenko on 22.02.17.
 */

  public class GithubTests {

    @Test
    public void testCommits() throws IOException {
     Github github = new RtGithub("95be03a792f5dace78b3e76a5743a58635353e51");
      RepoCommits commits = github.repos().get(new Coordinates.Simple("yutta13", "java_36")).commits();
      for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
        System.out.println(new RepoCommit.Smart(commit).message());
      }
    }
  }

package ru.stqa.pft.mantis.model;

/**
 * Created by uttabondarenko on 22.02.17.
 */
public class Project {

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return (this);
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return (this);
  }

  private int id;
  private String name;

}

package ru.stqa.pft.mantis.model;

/**
 * Created by uttabondarenko on 20.02.17.
 */

public class UserData {

  private int id = 2;
  private String username;
  private String password;
  private String email;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    return id == userData.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }
  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }
  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }
  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }
  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

}
package ru.stqa.pft.github.mantis.model;

/**
 * Created by uttabondarenko on 20.02.17.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}

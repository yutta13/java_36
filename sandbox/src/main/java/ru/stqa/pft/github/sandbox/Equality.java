package ru.stqa.pft.github.sandbox;

/**
 * Created by uttabondarenko on 10.01.17.
 */
public class Equality {
  public static void main(String[] args) {
    String s1 = "firefox";
    String s2 = "firefox";

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}

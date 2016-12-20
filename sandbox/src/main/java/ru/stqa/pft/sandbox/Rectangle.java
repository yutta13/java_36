package ru.stqa.pft.sandbox;

/**
 * Created by uttabondarenko on 20.12.16.
 */
public class Rectangle {
  double a;
  double b;

  public Rectangle(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double area() {
    return this.a * this.b;
  }


}

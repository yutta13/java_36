package ru.stqa.pft.github.sandbox;

/**
 * Created by uttabondarenko on 20.12.16.
 */
public class Square {
  public double l;

  public Square(double l) {
    this.l=l;
  }

  public  double area () {
    return this.l * this.l;
  }
}

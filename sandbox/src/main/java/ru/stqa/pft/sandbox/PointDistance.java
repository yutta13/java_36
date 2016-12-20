package ru.stqa.pft.sandbox;

/**
 * Created by uttabondarenko on 20.12.16.
 */
public class PointDistance {
  public static void main(String[] args) {
    Point p1 = new Point(3,2);
    Point p2 = new Point(7,8);

    System.out.println("Расстояние между точками " + "P1 (" + p1.x + "," + p1.y + ") " + "и " + "P2 (" + p2.x + "," + p2.y + ")" + " = " + p1.distance(p2));

  }


}

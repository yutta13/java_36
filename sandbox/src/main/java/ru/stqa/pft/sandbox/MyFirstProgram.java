package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("YUtta");

    Point p1 = new Point(3,2);
    Point p2 = new Point(7,8);
    System.out.println("расстояние между точками " + "P1(" + p1.x + "," + p1.y + ") " + "и " + "P2(" + p2.x + "," + p2.y + ")" + " = " + distance(p1,p2));

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,5);

    System.out.println("Площадь прямоугольник со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello (String somebody ) {

    System.out.println("Hello," + somebody + "!");
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));

  }

}
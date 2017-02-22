package ru.stqa.pft.github.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by uttabondarenko on 24.12.16.
 */
public class SquareTests {

  @Test

public void testArea() {
  Square s = new Square(5);
    Assert.assertEquals(s.area(),25.0);
}

}

package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEvaluate {

  @Before
  public void resetMode() {
    Evaluate.setDegreeMode(false);
  }

  @Test
  public void testBasicArithmetic() {
    assertEquals(5.0, Evaluate.eval("2+3"), 1e-9);
    assertEquals(2.5, Evaluate.eval("5/2"), 1e-9);
    assertEquals(16.0, Evaluate.eval("2^4"), 1e-9);
  }

  @Test
  public void testTrigInRadians() {
    Evaluate.setDegreeMode(false);
    assertEquals(1.0, Evaluate.eval("sin(pi/2)"), 1e-9);
  }

  @Test
  public void testTrigInDegrees() {
    Evaluate.setDegreeMode(true);
    assertEquals(1.0, Evaluate.eval("sin(90)"), 1e-9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyExpressionThrows() {
    Evaluate.eval("");
  }
}

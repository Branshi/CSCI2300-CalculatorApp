package controller;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public final class Evaluate {

  private static final DoubleEvaluator EVAL = new DoubleEvaluator();

  public static double eval(String expr) {
    // throws IllegalArgumentException if expr is malformed
    return EVAL.evaluate(expr);
  }
}

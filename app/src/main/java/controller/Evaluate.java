package controller;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

public final class Evaluate {
  static {
    // Ensure mXparser doesn’t prompt anything on startup
    mXparser.disableAlmostIntRounding();
  }

  /**
   * Switches all subsequent evaluations into degree or radian mode. Call this whenever your
   * HeaderController flips deg/rad.
   */
  public static void setDegreeMode(boolean deg) {
    if (deg) {
      mXparser.setDegreesMode();
    } else {
      mXparser.setRadiansMode();
    }
  }

  /**
   * Parses and evaluates the given mathematical expression.
   *
   * @param expr a String like "sin(30)" or "2^8 + log(10)"
   * @return the numeric result
   * @throws IllegalArgumentException if the expression is malformed
   */
  public static double eval(String expr) {
    Expression e = new Expression(expr);
    double result = e.calculate();
    if (Double.isNaN(result)) {
      // mXparser returns NaN on parse errors
      throw new IllegalArgumentException("Malformed expression: " + expr);
    }
    return result;
  }
}

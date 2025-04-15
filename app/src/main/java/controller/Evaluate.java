package controller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class Evaluate {

  // Throws ScriptException if the expression cannot be parsed or evaluated.
  public static double eval(String expr) throws ScriptException {
    // Create a ScriptEngineManager and get the JavaScript engine.
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    // Evaluate the expression.
    Object result = engine.eval(expr);

    // Check if the result is numeric.
    if (result instanceof Number) {
      return ((Number) result).doubleValue();
    } else {
      // If the result is not a number, throw an exception.
      throw new ScriptException("The evaluated expression did not return a numerical value.");
    }
  }
}

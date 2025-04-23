package controller;

import com.fathzer.soft.javaluator.DoubleEvaluator;

/**
 * A utility class for evaluating mathematical expressions.
 * Wraps the {@link DoubleEvaluator} to provide simple expression evaluation functionality.
 * This class cannot be instantiated and provides only static methods.
 */
public final class Evaluate {
    /** The evaluator instance used for all expression evaluations */
    private static final DoubleEvaluator EVAL = new DoubleEvaluator();

    /**
     * Evaluates a mathematical expression string and returns the result as a double.
     * 
     * @param expr the mathematical expression to evaluate (e.g., "2+3*5")
     * @return the computed result as a double value
     * @throws IllegalArgumentException if the expression is malformed or cannot be evaluated
     */
    public static double eval(String expr) {
        // throws IllegalArgumentException if expr is malformed
        return EVAL.evaluate(expr);
    }

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private Evaluate() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
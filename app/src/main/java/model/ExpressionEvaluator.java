package model;

import java.util.function.DoubleUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExpressionEvaluator {

    private final boolean degreeMode;

    public ExpressionEvaluator(boolean degreeMode) {
        this.degreeMode = degreeMode;
    }

    public double evaluate(String expr) throws Exception {
        expr = expr.replace("x", "*"); // Replace 'x' with '*'
        expr = handleTrig(expr);       // Handle sin(), cos(), tan()
        
        // Use JavaScript engine to evaluate the final expression
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Object result = engine.eval(expr);
        return Double.parseDouble(result.toString());
    }

    private String handleTrig(String expr) {
        expr = replaceTrig(expr, "sin", Math::sin);
        expr = replaceTrig(expr, "cos", Math::cos);
        expr = replaceTrig(expr, "tan", Math::tan);
        return expr;
    }

    private String replaceTrig(String expr, String func, DoubleUnaryOperator op) {
        Pattern pattern = Pattern.compile(func + "\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(expr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String innerExpr = matcher.group(1);
            double val = evalBasic(innerExpr); // Evaluate inside parentheses
            if (degreeMode) val = Math.toRadians(val); // Convert if needed
            double result = op.applyAsDouble(val);
            matcher.appendReplacement(sb, String.valueOf(result));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private double evalBasic(String expr) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Object result = engine.eval(expr);
            return Double.parseDouble(result.toString());
        } catch (Exception e) {
            return 0; // Fallback on error
        }
    }
}

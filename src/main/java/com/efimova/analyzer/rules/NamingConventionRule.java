package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import org.springframework.stereotype.Component;

/**
 * This rule ensures naming conventions to comply corresponding patterns.
 * Checks for class names, method names, method parameters, local variable names, field names.
 */
@Component
public class NamingConventionRule extends BaseRuleChecker {
    private static final String CLASS_NAME = "([A-Z][a-z0-9]+)+";
    private static final String VAR_METH_NAME = "^[a-z][a-zA-Z0-9]*$";
    private static final String CONST_NAME = "^[A-Z]+_[A-Z]*$";

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Context ctx) {
        if (!declaration.getNameAsString().matches(CLASS_NAME)) {
            ctx.addMessage(
                    "Class name \"" + declaration.getNameAsString() + "\" must match the pattern " + CLASS_NAME);
        }
        super.visit(declaration, ctx);
    }

    @Override
    public void visit(MethodDeclaration declaration, Context ctx) {
        String method = declaration.getNameAsString();

        if (!method.matches(VAR_METH_NAME)) {
            ctx.addMessage("Method " + method + ": \""
                    + method + "\" must match the pattern " + VAR_METH_NAME);
        }

        for (Parameter param : declaration.getParameters()) {
            this.visit(param, method, ctx);
        }
        super.visit(declaration, ctx);
    }

    private void visit(Parameter param, String methodName, Context ctx) {
        String name = param.getNameAsString();
        if (!name.matches(VAR_METH_NAME)) {
            ctx.addMessage(
                    "In Method :" + methodName + " parameter \""
                    + param.getNameAsString() + "\" must match the pattern " + VAR_METH_NAME);
        }
    }

    @Override
    public void visit(VariableDeclarationExpr declaration, Context ctx) {
        for (VariableDeclarator variable : declaration.getVariables()) {
            String name = variable.getNameAsString();

            if (!name.matches(VAR_METH_NAME)) {
                ctx.addMessage("In declaration " + declaration + ": "
                + "variable \"" + name + "\" must match regex " + VAR_METH_NAME);
            }
        }
        super.visit(declaration, ctx);
    }


    @Override
    public void visit(FieldDeclaration field, Context ctx) {
        for (VariableDeclarator variable : field.getVariables()) {
            String name = variable.getNameAsString();
            boolean isFinal = field.isFinal();

            if ((isFinal && !name.matches(CONST_NAME)) || (!isFinal && !name.matches(VAR_METH_NAME))) {
                ctx.addMessage("In declaration "
                        + field.toString().replace(System.lineSeparator(), " ") + ": "
                        + "field \"" + name + "\" must match regex " + (isFinal? CONST_NAME : VAR_METH_NAME));
            }
        }
        super.visit(field, ctx);
    }
}

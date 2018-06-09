package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.CheckingContext;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.springframework.stereotype.Component;

/**
 * This rule ensures naming conventions to comply corresponding patterns
 */
@Component
public class NamingConventionRule extends BaseRuleChecker {
    private static final String CLASS_NAME = "([A-Z][a-z0-9]+)+";

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, CheckingContext ctx) {
        if (!declaration.getNameAsString().matches(CLASS_NAME)) {
            ctx.addMessage(topClassName,
                    "Class name \"" + declaration.getNameAsString() + "\" must match the pattern " + CLASS_NAME);
        }
        super.visit(declaration, ctx);
    }

}

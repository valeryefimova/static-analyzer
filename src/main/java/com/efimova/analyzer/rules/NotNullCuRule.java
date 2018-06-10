package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;

/**
 * This rule checker ensures that it is given a not null Compilation Unit
 */
@Component
public class NotNullCuRule extends BaseRuleChecker {
    public static final String UNDEFIED = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, Context ctx) {
        if (compilationUnit != null) {
            return;
        }
        ctx.addMessage("Unit " + UNDEFIED + ": is NULL.");
    }
}

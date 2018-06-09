package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.CheckingContext;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;

/**
 * This rule checker ensures that it is given a not null Compilation Unit
 */
@Component
public class NotNullCuRule extends BaseRuleChecker {
    public static final String UNDEFIED = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, CheckingContext ctx) {
        if (compilationUnit != null) {
            return;
        }
        ctx.addMessage(UNDEFIED, "Unit " + UNDEFIED + ": is NULL.");
    }
}

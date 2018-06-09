package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;

/**
 * This stub checker does nothing
 */
@Component
public class AlwaysTrueRule extends BaseRuleChecker {
    @Override
    public void visit(CompilationUnit compilationUnit, Context ctx) {
    }
}

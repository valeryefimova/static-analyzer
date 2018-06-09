package com.efimova.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This is the base class for all rule checkers. needed for chain of responsibility pattern.
 */
public class BaseRuleChecker extends VoidVisitorAdapter<CheckingContext> {
    @Override
    public void visit(CompilationUnit compilationUnit, CheckingContext ctx) {
        super.visit(compilationUnit, ctx);
    }
}

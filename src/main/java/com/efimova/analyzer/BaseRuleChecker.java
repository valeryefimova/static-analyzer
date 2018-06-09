package com.efimova.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This is the base class for all rule checkers. needed for chain of responsibility pattern.
 */
public class BaseRuleChecker extends VoidVisitorAdapter<CheckingContext> {
    protected String topClassName;
    @Override
    public void visit(CompilationUnit compilationUnit, CheckingContext ctx) {
        topClassName = compilationUnit.getTypes().stream()
                .filter(t-> t.isPublic() && t.isTopLevelType()).findAny()
                .map(NodeWithSimpleName::getNameAsString).get();
        super.visit(compilationUnit, ctx);
    }
}

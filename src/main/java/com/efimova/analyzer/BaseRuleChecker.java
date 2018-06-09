package com.efimova.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This is the base class for all rule checkers. needed for chain of responsibility pattern.
 */
public class BaseRuleChecker extends VoidVisitorAdapter<Context> {
    protected String topClassName;

    @Override
    public void visit(CompilationUnit compilationUnit, Context ctx) {
        topClassName = compilationUnit.getTypes().stream()
                .filter(t -> t.isPublic() && t.isTopLevelType()).findAny()
                .map(NodeWithSimpleName::getNameAsString).orElse("Untitled");
        super.visit(compilationUnit, ctx);
    }
}

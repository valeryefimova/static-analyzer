package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This stub checker does nothing
 */
@Component
public class TopLevelPublicClassRule extends BaseRuleChecker {
    @Override
    public void visit(CompilationUnit compilationUnit, Context ctx) {
        List<String> topClasses = compilationUnit.getTypes().stream()
                .filter(t -> t.isPublic() && t.isTopLevelType())
                .map(NodeWithSimpleName::getNameAsString)
                .collect(Collectors.toList());
        if (topClasses.size() == 0) {
            ctx.addMessage("No top-level public class detected.");
        } else if (topClasses.size() > 1) {
            ctx.addMessage("Found " + topClasses.size()
                    + " top-level public classes while only one is allowed: "
                    + String.join(",", topClasses));
        }
        super.visit(compilationUnit, ctx);
    }
}

package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * There is no need to import a type that lives in the same package.
 * https://pmd.github.io/pmd-6.3.0/pmd_rules_java_errorprone.html#importfromsamepackage
 */
@Component
public class ImportFromSamePackageRule extends BaseRuleChecker {
    @Override
    public void visit(CompilationUnit compilationUnit, Context ctx) {
        topClassName = compilationUnit.getTypes().stream()
                .filter(t -> t.isPublic() && t.isTopLevelType()).findAny()
                .map(NodeWithSimpleName::getNameAsString).orElse("Untitled");

        Optional<PackageDeclaration> packg = compilationUnit.getPackageDeclaration();
        if (packg.isPresent()) {
            compilationUnit.getImports().forEach(imp -> {
                if (imp.getName().toString().startsWith(packg.get().getName().toString())) {
                    ctx.addMessage(topClassName, "Import Statement \""
                            + imp.toString().replace(System.lineSeparator(), "")
                            + "\" in not necessary. Import of the same package.");
                }
            });

        }
        super.visit(compilationUnit, ctx);
    }
}

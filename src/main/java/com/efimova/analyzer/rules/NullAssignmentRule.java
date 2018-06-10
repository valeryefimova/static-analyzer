package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Assigning a “null” to a variable (outside of its declaration) is usually bad form. Sometimes, this type of assignment is an indication that the programmer doesn’t completely understand what is going on in the code.
 * NOTE: This sort of assignment may used in some cases to dereference objects and encourage garbage collection.
 * https://pmd.github.io/pmd-6.3.0/pmd_rules_java_errorprone.html#nullassignment
 *
 * public void bar() {
 *   Object x = null; // this is OK
 *   x = new Object();
 *      // big, complex piece of code here
 *   x = null; // this is not required
 *      // big, complex piece of code here
 * }
 *
 */
@Component
public class NullAssignmentRule extends BaseRuleChecker {
    @Override
    public void visit(AssignExpr assignExpr, Context ctx) {
        Optional<Node> parent = assignExpr.getParentNode();
        if (!parent.isPresent()) {
            return;
        }

        if (assignExpr.getValue().isNullLiteralExpr()) {
            if (!(parent.get() instanceof Expression && ((Expression) parent.get()).isVariableDeclarationExpr())) {
                ctx.addMessage("Null assignment found in: \""
                        + parent.get().toString().replace(System.lineSeparator(), " ")
                        + "\". Make sure you really need to assign NULL here: " + assignExpr.toString());
            }
        }
        super.visit(assignExpr, ctx);
    }
}

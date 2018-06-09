package com.efimova.analyzer.rules;

import com.efimova.analyzer.BaseRuleChecker;
import com.efimova.analyzer.Context;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Avoid assignments in operands; this can make code more complicated and harder to read.
 * <link>https://pmd.github.io/pmd-6.3.0/pmd_rules_java_errorprone.html#assignmentinoperand<link/>
 */
@Component
public class AssignmentInOperandsRule extends BaseRuleChecker {

    @Override
    public void visit(IfStmt ifStmt, Context ctx) {
        AssignmentLookup al = new AssignmentLookup(topClassName, ifStmt);
        al.visit(ifStmt.getCondition(), ctx);
        super.visit(ifStmt, ctx);
    }

    @AllArgsConstructor
    private static class AssignmentLookup extends VoidVisitorAdapter<Context> {
        String top;
        IfStmt ifStmt;

        @Override
        public void visit(AssignExpr expr, Context ctx) {
            ctx.addMessage(top,
                    "If statements \"if(" + ifStmt.getCondition()
                            + ")\" contains assignment: \"" + expr.toString() + "\"");
            super.visit(expr, ctx);
        }


        private void visit(Expression expr, Context ctx) {
            if(expr.isBinaryExpr()){
                super.visit(ifStmt.getCondition().asBinaryExpr(), ctx);
            } else if (expr.isAssignExpr()){
                super.visit(ifStmt.getCondition().asAssignExpr(), ctx);
            }
        }


    }


}

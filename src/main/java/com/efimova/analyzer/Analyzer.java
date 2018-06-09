package com.efimova.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class holds all checkers and applies them one by one to given file (CU).
 */
@Service
public class Analyzer {
    private List<BaseRuleChecker> ruleCheckers;
    @Getter
    private CheckingContext ctx;

    @Autowired
    public void setRuleCheckers(List<BaseRuleChecker> ruleCheckers){
        this.ruleCheckers = ruleCheckers;
    }
    @Autowired
    public void setCtx(CheckingContext ctx) {
        this.ctx = ctx;
    }

    @SneakyThrows
    public void processUnit(CompilationUnit unit) {
        for (BaseRuleChecker checker: ruleCheckers) {
            checker.visit(unit, ctx);
        }
    }
}

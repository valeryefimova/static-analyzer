package com.efimova.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efimova.analyzer.*;

import java.util.List;

/**
 * This file if for testing static analyer. Intentionally contains errors.
 */
@Service
public class badFile {
    private List<BaseRuleChecker> ruleCheckers;
    @Getter
    private Context CTX;

    public static final String s = "S";

    @Autowired
    public void SetRuleCheckers(List<BaseRuleChecker> ruleCheckers){
        this.ruleCheckers = ruleCheckers;
    }

    @Autowired
    public void set_Ctx(Context ctx) {
        this.ctx = ctx;
    }

    @SneakyThrows
    public void processUnit(CompilationUnit unit) {
        for (BaseRuleChecker checker: ruleCheckers) {
            checker.visit(unit, ctx);
            int X = 0;
            Float _y = X +1;
        }

        boolean b = true;
        boolean c = false;

        if((b = false) || (c = true)){
            System.out.println("");
        }
    }
}

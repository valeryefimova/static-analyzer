package com.efimova.analyzer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].endsWith("java")) {
            usage();
        } else {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            Analyzer a = (Analyzer) ctx.getBean("analyzer");
            Path p = Paths.get(args[0]);
            analyze(p, a);
        }
    }

    /**
     * The main logic entry point. Performs static checks and prints report.
     * @param filePath path to file for analyzing.
     * @param a analyzer
     */

    @SneakyThrows
    private static void analyze(Path filePath, Analyzer a) {
        CompilationUnit unit = JavaParser.parse(filePath.toFile());
        a.processUnit(unit);
        a.getCtx().printContext();
    }

    private static void usage() {
        System.out.println("Usage: java -jar static-analyzer.jar <file.java>");
    }
}

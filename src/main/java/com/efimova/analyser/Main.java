package com.efimova.analyser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].endsWith("java")) {
            usage();
        } else {
            Path p = Paths.get(args[0]);
            analyze(p);
        }
    }

    /**
     * The main logic entry point. Performs static checks and prints report.
     * @param filePath path to file for analyzing.
     */

    @SneakyThrows
    private static void analyze(Path filePath) {
        CompilationUnit unit = JavaParser.parse(filePath.toFile());
        System.out.println(unit.toString());
    }

    private static void usage() {
        System.out.println("Usage: java -jar static-analyzer.jar <file.java>");
    }
}

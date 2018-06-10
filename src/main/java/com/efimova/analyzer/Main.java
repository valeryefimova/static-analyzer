package com.efimova.analyzer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            usage();
        } else {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            Analyzer a = (Analyzer) ctx.getBean("analyzer");
            Path p = Paths.get(args[0]);
            if (!Files.isDirectory(p)) {
                if (!p.getFileName().toString().endsWith("java")) {
                    usage();
                    return;
                } else {
                    a.getCtx().setCurrentFileName(p.getFileName().toString());
                    analyze(p, a);
                }
            } else {
                Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.getFileName().toString().endsWith(".java")) {
                            a.getCtx().setCurrentFileName(file.getFileName().toString());
                            analyze(file, a);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
            a.getCtx().printContext();
        }
    }

    /**
     * The main logic entry point. Performs static checks and prints report.
     *
     * @param filePath path to file for analyzing.
     * @param a        analyzer
     */

    @SneakyThrows
    private static void analyze(Path filePath, Analyzer a) {
        CompilationUnit unit = JavaParser.parse(filePath.toFile());
        a.processUnit(unit);
    }

    private static void usage() {
        System.out.println("Usage: java -jar static-analyzer.jar <project.dir or file>");
    }
}

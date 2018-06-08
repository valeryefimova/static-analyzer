package main.java;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
        } else {
            Path p = Paths.get(args[0]);
            analyze(p);
        }
    }

    /**
     * The main logic entry point. performs static checks and prints report.
     * @param path path to file for analyzing.
     */
    private static void analyze(Path path) {

    }

    private static void usage() {
        System.out.println("Usage: java -jar static-analyzer.jar <file>");
    }
}

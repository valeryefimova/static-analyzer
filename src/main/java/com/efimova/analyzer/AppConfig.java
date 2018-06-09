package com.efimova.analyzer;

import com.efimova.analyzer.rules.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Annotation configuration class for spring
 */
@Configuration
//@ComponentScan(basePackages = {"com.efimova.analyzer", "com.efimova.analyzer.rules"})
public class AppConfig {
    @Bean
    public Analyzer analyzer() {
        return new Analyzer();
    }

    @Bean
    public Context checkingContext() {
        return new Context();
    }
    @Bean
    public AlwaysTrueRule alwaysTrueRule() {
        return new AlwaysTrueRule();
    }
    @Bean
    public NotNullCuRule notNullCuRule () {
        return new NotNullCuRule();
    }
    @Bean
    public NamingConventionRule namingConventionRule () {
        return new NamingConventionRule();
    }
    @Bean
    AssignmentInOperandsRule assignmentInOperandsRule() {
        return new AssignmentInOperandsRule();
    }
    @Bean
    ImportFromSamePackageRule importFromSamePackageRule(){return new ImportFromSamePackageRule();}

}

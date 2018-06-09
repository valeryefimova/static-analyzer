package com.efimova.analyzer;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class keeps all info about static analysis
 */
@Component
public class CheckingContext {

    @Getter
    private HashMap<String, List<String>> messages = new HashMap<>();

    public void addMessage(String className, String message) {
        messages.putIfAbsent(className, new ArrayList<>());
        messages.get(className).add(message);
    }

    public void printContext(){

        if (messages.isEmpty()){
            System.out.println("All Right!");
            return;
        }
        for(String c: messages.keySet()){
            messages.get(c).forEach(m->
                    System.out.println(c + ":\t " + m));
            System.out.println();
        }
    }
}

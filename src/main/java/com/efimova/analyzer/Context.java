package com.efimova.analyzer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class keeps all info about static analysis
 */
@Component
public class Context {

    @Getter
    private HashMap<String, List<String>> messages = new HashMap<>();
    @Getter @Setter
    String currentFileName = "Undefined";

    public void addMessage(String message) {
        messages.putIfAbsent(currentFileName, new ArrayList<>());
        messages.get(currentFileName).add(message);
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

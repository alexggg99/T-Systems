/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsystems.javaschool.tasks;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author alexey
 */
public class testMain {
    public static void main(String[] args) {
        String equetion = "(1/2*1)/(3+2)";
        InToPost generator = new InToPost(equetion);
        String output = generator.doTrans();
        System.out.println("Postfix expresion: "+output);
        Calculator calc = new CalculatorImpl();
        System.out.println("Calculation: " + calc.evaluate(equetion));
        
        System.out.println("----------------------------------------");
        DuplicateFinder d = new DuplicateFinderImpl();
        boolean res = d.process(new File("movies.txt"), new File("moviescopy.txt"));
        System.out.println("DuplicateFinder: "+res);
    }
}

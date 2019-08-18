/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author acer
 */
public class parallelTest {
    public static void call(int i){
        i=i*2;
    }
    public static void main(String[] args) {
        List<Integer> il = new ArrayList<Integer>();
        
        il.add(1);
        il.add(2);
        il.add(3);
        
        List<Integer> result = il.parallelStream().map(i->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(parallelTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return i*2;
        }).collect((Collectors.toList()));
        
        for(int i:result){
            System.out.println(i);
        }
        }
        
    
    }


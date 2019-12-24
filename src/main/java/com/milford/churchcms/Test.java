/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import com.milford.churchcms.util.PasswordUtil;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author clarke
 */
public class Test {    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        SfFuture sf = new SfFuture();
        Future<Integer> calculate = sf.calculate(15);
        String pass = "t";

        System.out.println("Hashed pass "+ calculate.get());
        sf.shutdown();
    }  
}

class SfFuture{
    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    public Future<Integer> calculate(Integer input) {        
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
    
    public void shutdown(){
        executor.shutdown();
    }
}

//class Lambda{
//     List<Integer> numbers = Arrays.asList(1,2,3);
//    public void doStuff(){
//       // numbers.stream().filter( number -> (number % 2 != 0)).forEach(System.out::println);
//       numbers.stream().filter(Test123::isOdd).forEach(System.out::println);
//    }
//    
//}
//
//class Test123{
//    
//    public boolean isOdd(int number){
//        return (number % 2 != 0);
//    }
//    
//    public boolean hasOverHundredPoints() {
//        return true;
//    }
//}
//
//class Color implements Comparable<Color>{
//    private int R;
//    private int G;
//    private int B;
//
//    public Color(int R, int G, int B) {
//        this.R = R;
//        this.G = G;
//        this.B = B;
//    }
//
//    public int getR() {
//        return R;
//    }
//
//    public void setR(int R) {
//        this.R = R;
//    }
//
//    public int getG() {
//        return G;
//    }
//
//    public void setG(int G) {
//        this.G = G;
//    }
//
//    public int getB() {
//        return B;
//    }
//
//    public void setB(int B) {
//        this.B = B;
//    }
//
//    @Override
//    public int compareTo(Color t) {
//        if(this.R == t.R){
//            return 0;
//        }
//        return 1;
//    }
//}
//
//// "2018-11-26T10:00:00"
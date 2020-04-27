/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface Processor {
    String process(Callable<Integer> c) throws Exception;
    String process(Supplier<String> s);
}
 
class ProcessorImpl implements Processor {
   @Override
   public String process(Callable<Integer> c) throws Exception {
      return "Callable " + c.call();
   }

   @Override
   public String process(Supplier<String> s) {
       return "Supplier " + s.get();
   }
}
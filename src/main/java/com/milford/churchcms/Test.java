/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author clarke
 */
public class Test {
//    public static void main(String[] args){
//      List<String> stringList = new ArrayList<>();
//      stringList.add("Me");
//      stringList.add("You");
//      stringList.add("Them");
//      
//      for(String myS : stringList){
//          if("Them".equals(myS)){
//              System.out.println("  " + myS);
//          }
//      }
//      
//    }
//    
    
    public static void swap(Map<String, Color> colorMap){
        colorMap.replace("R", new Color("green"));
    }
}

class Color{
    private String color;

    public Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Color{" + "color=" + color + '}';
    }    
}

// "2018-11-26T10:00:00"
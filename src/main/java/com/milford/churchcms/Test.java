package com.milford.churchcms;

import java.util.Arrays;
import java.util.List;


public class Test{

//     public static void main(String []args) throws Exception{
//       //  printList(Arrays.asList(1,2,3,4,5,6,7,8,9));
//       Car myCar = new Mazda();
//       myCar.honk(new UgHorn());
//    }
     
//    public static void printList(List<Integer> nums){
//        nums.stream()
//                .filter(Test::isEven)
//                .forEach(System.out::println);
//    }
//             
//    private static boolean isEven(int num){
//        return num % 2 == 0;
//    }
}

class Car{
    public void honk(Horn horn){
        System.out.println(horn.toString());
    }
}

class Mazda extends Car{
    public void honk(UgHorn horn){
        System.out.println(horn.toString());
    }
}

class Horn{
    public String type = "Reg Horn";
    
    @Override
    public String toString(){
        return type;
    }
}

class UgHorn extends Horn{
    public String type = "Ug Horn";
    
    @Override
    public String toString(){
        return type;
    }
}
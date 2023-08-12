package com.milford.churchcms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

// Write a generic method to count the number of elements in a collection that 
//     have a specific property  (for example, odd integers, prime numbers, palindromes).
public class Test{
    public static void main(String []args) throws Exception{
        Integer[] tNumbers = {11,12,22,15,25,86,55,33,64};
        List<Integer> numberList = Arrays.asList(tNumbers);
        
        String[] tStringss = {"pop","cake","madam","stuff"};
        List<String> stringList = Arrays.asList(tStringss);
        
        

    }
    
    public static <K> int count(List<K> myList) {
        int count = 0;
        return count;
    }
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

class Employee{
    private String name;
    private String dept;

    public Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", dept=" + dept + '}';
    }
    
}

   /*     
            Integer[] tihNumbers = {11,12,22,15,25,86,55,33,64};
	List<Integer> numberList = Arrays.asList(tihNumbers);
        List<Integer> bref;
        
	bref = numberList.stream()
                .filter(n -> n%2!=0)
                .filter(n -> n < 30)
                .filter(n -> n > 20)
                .collect(Collectors.toList());    
        
        System.out.println(bref);
    ------------------------------------------------
    List<Employee> employees = new ArrayList(); 
        employees.add(new Employee("Clarke","IT"));
        employees.add(new Employee("Sangetta","IT"));
        employees.add(new Employee("Bill","BUS"));
        

        Map<String, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept));
  
        System.out.println(collect.values());
//        List<Employee> list = new ArrayList<Employee>(collect.values());
//        Collection<List<Employee>> values = collect.values();
//        
//        ArrayList<Employee> empList = new ArrayList<Employee>(values);
//        List<Employee> list = values.toArray();
//        for(Employee value: values){
//            
//        }
    } */

//    public static void main(String []args){
//                String me1 = "me";
//        String me2 = "me";
//
//        me1.hashCode();
////        me2.hashCode();
//    }

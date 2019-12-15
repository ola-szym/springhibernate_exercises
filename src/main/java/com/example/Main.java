package com.example;


import java.util.ArrayList;
import java.util.List;

public class Main {

//
//    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<>();
//        numbers.add(5);
//        numbers.add(6);
//        numbers.add(10);
//        numbers.add(15);
//
//        reversedList.
//
//        System.out.println(reversedList);
//
//    }


    public List<Integer> reverseList(List<Integer> numbers) {
        List<Integer> reversedList = new ArrayList<>();

        for (int i=numbers.size()-1; i>=0; i--){
            reversedList.add(numbers.get(i));
        } return reversedList;
    }
}

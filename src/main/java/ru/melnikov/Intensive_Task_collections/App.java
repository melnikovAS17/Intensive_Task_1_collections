package ru.melnikov.Intensive_Task_collections;


import ru.melnikov.Intensive_Task_collections.implementaion.MyArrayList;
import ru.melnikov.Intensive_Task_collections.implementaion.MyHashMap;

public class App
{
    public static void main( String[] args ){
        MyHashMap<String, Integer> v = new MyHashMap<>();
        v.putElement("first", 1);
        v.putElement("second", 1);
        v.putElement("third", 1);
        v.putElement("first", 2);
        System.out.println(v);
    }
}

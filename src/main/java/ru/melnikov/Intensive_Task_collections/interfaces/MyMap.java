package ru.melnikov.Intensive_Task_collections.interfaces;


public interface MyMap<K, V> {

    int getSize();

    V getElement (K key);

    void putElement(K key, V value);

    boolean removeElement(K key);

    void clear();
}

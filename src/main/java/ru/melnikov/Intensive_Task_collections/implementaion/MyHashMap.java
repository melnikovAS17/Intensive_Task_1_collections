package ru.melnikov.Intensive_Task_collections.implementaion;

import ru.melnikov.Intensive_Task_collections.interfaces.MyMap;

import java.util.Arrays;

public class MyHashMap <K, V> implements MyMap<K, V> { //TODO добавить тесты и коментарии (поправить пунктуацию в коментариях)

    /** Константа - целочисленное значение начального размера коллекции.  */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Константа - вещественное число загруженности коллекции.
     * Данная константа необходима для проверки и дальнейшего
     * увеличения размера коллекции при её загрузке в 75%.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /** В переменной size хранится количество существующих элементов в коллекции. */
    private int size = 0;

    /**
     * Так как HashMap, как и HashSet в основе своей реализации содержит массив,
     * то здесь создаётся массив типа Entry для возможности в дальнейшем хранить
     * в коллекции элементы в формате: <Ключ, Значение>.
     */

    private Entry[] objectsArray = new Entry[INITIAL_CAPACITY];

    @Override
    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V getElement(K key) {
        /* int position создаётся для простоты чтения */
        int position = getHashPosition(key);
            while (objectsArray[position] != null){
                if(objectsArray[position].key.equals(key)) {
                    return (V) objectsArray[position];
                }
                objectsArray[position]= objectsArray[position].next;
            }
        return null;
    }

    @Override
    public void putElement(K key, V value) {
        checkOnLoad();
        int position = getHashPosition(key);
        if (objectsArray[position] == null){
            Entry entry = new Entry(key,value,null);
            objectsArray[position] = entry;
            size++;
        }else {
            while (true){
                if(objectsArray[position].key.equals(key)){
                    objectsArray[position].value = value;
                    break;
                }
                if(objectsArray[position].next == null){
                    objectsArray[position].next = new Entry(key,value,null);
                    size++;
                    break;
                }
                objectsArray[position] = objectsArray[position].next;
            }
        }
    }


    @Override
    public boolean removeElement(K key) {
        int position = getHashPosition(key);
        if(objectsArray[position] != null && objectsArray[position].key.equals(key)) {
            objectsArray[position] = objectsArray[position].next;
            size--;
            return true;
        } else {
            while (objectsArray[position] != null) {
                if (objectsArray[position].next == null) {
                    return false;
                }
                if (objectsArray[position].next.key.equals(key)) {
                    objectsArray[position].next = objectsArray[position].next.next;
                    size--;
                    return true;
                }
                objectsArray[position] = objectsArray[position].next;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        objectsArray = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Метод увеличвает размер коллекции в два раза при загрузке в 75%.
     * При увеличении копирует все элементы в новую увеличенную коллекцию
     * и присваивает ссылке прошлой (неувеличенной) коллекции значения новой (увеличенной).
     */
    private void increasingArray(){
        Entry[] newArray = new Entry[INITIAL_CAPACITY*2];
        for(Entry entry: objectsArray){
           while (entry != null){
               putElementsInNewArray(entry.key, entry.value, newArray);
               entry = entry.next;
           }
        }
        objectsArray = newArray;
    }

    private void putElementsInNewArray(Object key, Object value, Entry[] dst){
        int position = getHashPosition(key);
        if (dst[position] == null) {
            dst[position] = new Entry(key, value, null);
        } else {
            while (true) {
                if (dst[position].key.equals(key)) {
                    dst[position].value = value;
                    break;
                }
                if (dst[position].next == null) {
                    dst[position].next = new Entry(key, value, null);
                    break;
                }
                dst[position] = dst[position].next;
            }
        }
    }

    /**
     * Метод проверяет загрузку коллекции.
     * Необходим для метода increasingArray().
     */
    private void checkOnLoad(){
        if(size == Math.round(LOAD_FACTOR * INITIAL_CAPACITY)) increasingArray();
    }


    private int getHashPosition(Object key){
        return Math.abs(key.hashCode()) % objectsArray.length;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "objectsArray=" + Arrays.toString(objectsArray) +
                '}';
    }

    /**
     * Данный класс создан из-за особенности формата хранения данных в HashMap.
     * Реализация HashMap основана на Hash - таблицах  т.е. внутри HashMap обычный массив
     * индекс элемента в котором вычисляется по хэш-коду объекта 'ключа'.
     * И для того чтобы хранить элементы в массиве в формате: <Ключ, Значение>
     * был создан класс Entry.
     */
    private static final class Entry{
        /*
        Во избежание ошибки: generic array creation, заменил универсальный
        тип K и V на Object
         */
        Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

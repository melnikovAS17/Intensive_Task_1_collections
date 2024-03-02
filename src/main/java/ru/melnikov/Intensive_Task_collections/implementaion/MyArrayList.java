package ru.melnikov.Intensive_Task_collections.implementaion;

import ru.melnikov.Intensive_Task_collections.interfaces.MyList;

import java.util.Arrays;

/**
 * Моя реализация ArrayList.
 * Реализовал только основные методы.
 * Для наглядности импользовал циклы, а не какие-либо готовые методы уже существующих
 * интерфейсов и классов.
 */
public class MyArrayList<T> implements MyList<T>{
    /** Константа - целочисленное значение начального размера коллекции.  */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Константа - вещественное число загруженности коллекции.
     * Данная константа необходима для проверки и дальнейшего
     * увеличения размера коллекции при её загрузке в 75%.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /** В переменной size хранистя количество существующих элементов в коллекции. */
    private int size = 0;
    /**
     * Так как ArrayList в основе своей реализации содержит массив.
     * Здесь создаётся массив типа Object для возможности в дальнейшем хранить
     * в коллекции различиные типы - наследников Object.
     */
    private Object[] objectsArray = new Object[INITIAL_CAPACITY];

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void addElementByIndex(Object o, int index) {
        checkOnIndex(index);
        if(checkForEmptiness(index)) {
            checkOnLoad();
            objectsArray[index] = o;
            size++;
        }
        else {
            /* В этом методе есть нюанс: при добавлении элемента сдвигаются все сотальные,
               но так как в масиве могут быть пустоты (пустые ячейки), элемент который стоит
               в конце ,при незвполненном масиве, должен будет перезписаться в следущий
               индекс, а так как его не существует, элемент потеряется , для этого необходимо
               сделать проверку на заполненность последней ячейки и если там что-то есть,
               увелитчь массив */
            if(objectsArray[objectsArray.length -1 ] != null) increasingArray();
            checkOnLoad();
            for(int i = objectsArray.length - 1; i > index; i--){
                objectsArray[i] = objectsArray[i -1];
            }
            objectsArray[index] = o;
            size++;
        }
    }

    @Override
    public void addElement(Object o) {
        checkOnLoad();
        objectsArray[size] = o;
        size++;
    }
    @SuppressWarnings("unchecked")
    @Override
    public T getElement(int index) {
        checkOnIndex(index);
        return (T) objectsArray[index];
    }

    @Override
    public boolean removeElement(Object o) {
        for(int i = 0; i < objectsArray.length; i++){
            if(objectsArray[i].equals(o)) {
                return removeElementByIndex(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeElementByIndex(int index) {
        checkOnIndex(index);
        if(checkForEmptiness(index)) return false;
        for(int i = index; i < size - 1; i++){
            objectsArray[i] = objectsArray[i+1];
        }
        size--;
        return true;
    }

    @Override
    public void clear() {
        objectsArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Метод проверяет, есть ли элемент по заданному индексу, или же он равен null.
     * Так так ArrayList имеет метод вставки по индексу, в нём могут образовываться
     * пустые ячейки.
     */
    private boolean checkForEmptiness(int index){
        return getElement(index) == null;
    }

    /**
     * Метод увеличвает размер коллекции в два раза при загрузке в 75%.
     * При увеличении копирует все элементы в новую увеличенную коллекцию
     * и присваивает ссылке прошлой (неувеличенной) коллекции значения новой (увеличенной).
     */
    private void increasingArray(){
        Object[] newArray = new Object[objectsArray.length*2];
        //Можно использовать Arrays.copyOf() - но для наглядности решил реализовать увеличение размера массива
        //через цикл
        for (int i = 0; i < size; i++){
             newArray[i] = objectsArray[i];
        }
        objectsArray = newArray;
    }

    /**
     * Метод проверяет загрузку коллекции.
     * Необходим для метода increasingArray().
     */
    private void checkOnLoad(){
        if(size >= Math.round(LOAD_FACTOR * objectsArray.length)) increasingArray();
    }

    /**
     * Метод проверяет правильность введённого индекса.
     * Если величина индекса не входит в пределы существующей коллекции или меньше 0 -
     * выбрасывает исключение.
     */
    private void checkOnIndex(int index){
        if(index >= objectsArray.length || index < 0) throw new IndexOutOfBoundsException();
    }

    /**
     * !Метод для теста! возвращает длину массива
     */
    private int getArrayLength(){
        return objectsArray.length;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "objectsArray=" + Arrays.toString(objectsArray) +
                '}';
    }
}

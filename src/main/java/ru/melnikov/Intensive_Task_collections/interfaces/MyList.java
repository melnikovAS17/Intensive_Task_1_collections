package ru.melnikov.Intensive_Task_collections.interfaces;

/**
 * Данный интерфейс - моя реализация основных методов List.
 * Методы относятся исключительно к коллекции вида ArrayList.
 */

public interface MyList<T> {
    /**
     * Метод возвращает целое число равное размеру колллекции
     * (т е количество элементов в ней).
     */
    int getSize();

    /** Метод добавления элемента в колллекцию на указанную позицию (index). */
    void addElementByIndex(T t, int index);

    /** Метод добавления элемента в конец коллекции. */
    void addElement(T t);

    /** Метод возвращает элемент коллекции, index - адресс элемента/ячейки в коллекции. */
    T getElement(int index);

    /** Метод удаляет элемент из коллекции (элемент удаления передаётся в аргумент). */
    boolean removeElement(T t);

    /** Метод удаляет элемент по index - адресу элемента в коллекции. */
    boolean removeElementByIndex(int index);

    /** Метод очищает всю коллекцию(удаляет каждый элемент). */
    void clear();

}
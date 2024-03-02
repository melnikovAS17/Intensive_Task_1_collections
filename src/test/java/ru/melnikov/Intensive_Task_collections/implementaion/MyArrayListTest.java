package ru.melnikov.Intensive_Task_collections.implementaion;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MyArrayListTest {
    private MyArrayList<Integer> myList;
    @Before
    public void setUp(){
        myList = new MyArrayList<>();
    }

    @Test // getSize() & addElement()
    public void whenAdded100ElementsSizeMustBe100(){
        for(int i = 0; i < 100; i++){
            myList.addElement(i);
        }
        assertEquals(100,myList.getSize());
    }

    @Test // getElement() & addElement()
    public void whenWantToGetSpecificElementMustGetExactlyThat(){
        for(int i = 0; i < 50; i++){
            myList.addElement(i);
        }
        int existElement = myList.getElement(35);
        assertEquals(35,existElement);
    }


    @Test //addElementByIndex
    public void whenAddElementByIndexMustGetThisElementByThisIndex(){
        for(int i = 0; i < 50; i++){
            myList.addElement(i);
        }
        myList.addElementByIndex(999, 76);
        int existElement = myList.getElement(76);
        assertEquals(999, existElement);
    }


    @Test // removeElement()
    public void whenRemoveElementGetNextValueAtSameIndexAndSizeMustBeReduced(){
        for(int i = 0; i < 20; i++){
            myList.addElement(i);
        }
        myList.removeElement(5);
        int nextElementValue = myList.getElement(5);
        assertEquals(6,nextElementValue);
        assertEquals(19,myList.getSize());
    }

    @Test // removeElementByIndex()
    public void whenRemoveElementSizeMustBeReduced(){
        for(int i = 0; i < 20; i++){
            myList.addElement(i);
        }
        myList.removeElementByIndex(2);
        assertEquals(19,myList.getSize());
    }


    @Test // removeElementByIndex()
    public void whenRemoveNonExistElementMustReturnFalse(){
        for(int i = 0; i < 20; i++){
            myList.addElement(i);
        }
        myList.addElementByIndex(25, 25);
        assertFalse(myList.removeElementByIndex(22));
    }

    @Test // removeElementByIndex()
    public void whenRemoveElementByIndexOutOfBoundThisExceptionShouldBeThrown(){
        for(int i = 0; i < 20; i++){
            myList.addElement(i);
        }
        assertThrows(IndexOutOfBoundsException.class,() -> myList.removeElementByIndex(100));
    }

    @Test //clear()
    public void whenCalledMethodClearSizeMustBe0(){
        for(int i = 0; i < 20; i++){
            myList.addElement(i);
        }
        myList.clear();
        assertEquals(0,myList.getSize());
    }

    @Test // increasingArray() - изначально размер массива 10. 75% от 10 = 8 элементов.
    public void testIncreasingArrayWhenLoadArrayWillBe75PercentLengthShouldBeDoubleValue() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        for(int i = 0; i < 9; i++){
            myList.addElement(i);
        }
        assertEquals(20, getArrayLength(myList));
    }



    // Метод для выдачи прав на использование приватного метода из класса MyArrayList с помощью reflection
    public static int getArrayLength(MyArrayList<Integer> myList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = myList.getClass().getDeclaredMethod("getArrayLength");
        method.setAccessible(true);
        return (int) method.invoke(myList);
    }
}

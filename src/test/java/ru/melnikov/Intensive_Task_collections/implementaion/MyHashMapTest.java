package ru.melnikov.Intensive_Task_collections.implementaion;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyHashMapTest {
    private MyHashMap<Integer, String> myMap;
    @Before
    public void setUp()  {
        myMap = new MyHashMap<>();
    }

    @Test // getSize() & putElement()
    public void whenAdded100ElementsSizeShouldReturn100(){
        for(int i = 0; i < 100; i++){
            myMap.putElement(i, "Element " + i);
        }
        assertEquals(100, myMap.getSize());
    }

    @Test // getElement & putElement()
    public void whenWantToGetSpecificElementMustGetExactlyThat(){
        for (int i = 0; i < 10; i++){
            myMap.putElement(i, "Element " + i);
        }
        String value = (String) myMap.getElement(5);
        assertEquals("Element 5", value);
    }

    @Test // putElement() - проверка на отсутствие дубликатов
    public void whenTryingAdd10DuplicatesKeyElementsSizeMustBe1(){
        for (int i = 0; i < 10; i++){
            myMap.putElement(1, "Element " + i);
        }
        assertEquals(1,myMap.getSize());
    }

    @Test // removeElement()
    public void whenRemove1ElementSizeMustBeReducedBy1(){
        for (int i = 0; i < 10; i++){
            myMap.putElement(i, "Element " + i);
        }
        myMap.removeElement(1);
        assertEquals(9,myMap.getSize());
    }

    @Test // removeElement()
    public void whenRemoveNonExistElementMustReturnFalse(){
        for (int i = 0; i < 10; i++){
            myMap.putElement(i, "Element " + i);
        }
        assertFalse(myMap.removeElement(100));
    }

    @Test // clear()
    public void whenAdded100ElementsAndThenCalledMethodClearSizeMustBe0(){
        for (int i = 0; i < 100; i++){
            myMap.putElement(i, "Element " + i);
        }
        assertEquals(100,myMap.getSize());
        myMap.clear();
        assertEquals(0,myMap.getSize());
    }



    @Test // increasingArray() - изначально размер массива 16. 75% от 16 = 12 элементов.
    public void testIncreasingArrayWhenLoadArrayWillBe75PercentLengthShouldBeDoubleValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < 13; i++){
            myMap.putElement(i, "Element " + i);
        }
        assertEquals(32 ,getArrayLength(myMap));

    }


    // Метод для выдачи прав на использование приватного метода из класса MyHashMap с помощью reflection
    public static int getArrayLength(MyHashMap<Integer, String> myMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = myMap.getClass().getDeclaredMethod("getArrayLength");
        method.setAccessible(true);
        return (int) method.invoke(myMap);
    }
}
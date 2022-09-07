package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Test;

public class CustomLinkedListTest {

    @Test
    public void test_CreateConstructor_ShouldCreate(){

    }

    @Test
    public void test_Add_ShouldCreateNewHeadAndTail(){
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();

        customLinkedList.add(1);
        customLinkedList.add(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Get_ShouldThrow(){
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
        customLinkedList.get(0);
    }

    @Test
    public void test_Get_ShouldGet(){
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(1);
        customLinkedList.add(2);
        customLinkedList.add(3);

        int elementToCheck = customLinkedList.get(0);

        Assert.assertEquals(1,elementToCheck);
    }
}
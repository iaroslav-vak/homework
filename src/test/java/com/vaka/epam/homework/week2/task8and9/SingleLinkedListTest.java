package com.vaka.epam.homework.week2.task8and9;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Iaroslav on 11/10/2016.
 */
public class SingleLinkedListTest {
    @Test
    public void testAdd() throws Exception {
        List<String> list = new SingleLinkedList<>();
        String helloWorld = "HelloWorld";
        list.add(helloWorld);
        list.add(null);
        Assert.assertTrue(list.contains(null));

        Assert.assertSame(helloWorld, list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddOnIndex() throws Exception {
        List<String> list = new SingleLinkedList<>();
        list.add("first element");
        list.add("second element");
        list.add("third element");
        String elem = "new element";
        String last = "last element";
        String middle = "middle element";
        list.addOnIndex(0, elem);
        list.addOnIndex(4, last);
        list.addOnIndex(2, middle);

        try {
            list.addOnIndex(-4, "some elem");
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            list.addOnIndex(14, "some elem");
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }

        Assert.assertFalse(list.contains("some elem"));
        Assert.assertSame(elem, list.get(0));
        Assert.assertSame(middle, list.get(2));
        Assert.assertSame(last, list.get(5));
    }

    @Test
    public void testGet() throws Exception {
        List<Integer> list = createList();
        Integer i = 5000;
        list.addOnIndex(50, i);
        Assert.assertSame(i, list.get(50));
        try {
            list.get(-10);
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            list.get(10000);
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        list.addOnIndex(900, i);
        Assert.assertSame(i, list.get(900));
    }

    @Test
    public void testRemove() throws Exception {
        List<Integer> list = createList();

        Assert.assertTrue(list.remove(100));
        Assert.assertFalse(list.remove(100));
        Assert.assertFalse(list.contains(100));

        Assert.assertTrue(list.remove(0));
        Assert.assertFalse(list.remove(0));
        Assert.assertFalse(list.contains(0));

        Integer last = list.get(list.size() - 1);
        Assert.assertTrue(list.remove(last));
        Assert.assertFalse(list.remove(last));
        Assert.assertFalse(list.contains(last));


        Assert.assertFalse(list.remove(null));
        list.add(null);
        Assert.assertTrue(list.remove(null));
        Assert.assertFalse(list.contains(null));
    }

    @Test()
    public void testRemoveOnIndex() throws Exception {
        List<Integer> list = createList();
        list.add(5000);


        list.removeOnIndex(list.size() - 1);
        Assert.assertFalse(list.contains(5000));

        list.removeOnIndex(500);
        Assert.assertFalse(list.contains(500));

        list.removeOnIndex(0);
        Assert.assertFalse(list.contains(0));

        try {
            list.removeOnIndex(-10);
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            list.removeOnIndex(10000);
            Assert.fail();
        } catch (IndexOutOfBoundsException expected) {
        }
    }

    @Test
    public void testIndexOf() throws Exception {
        List<Integer> list = createList();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertTrue(i == list.indexOf(i));
        }
        list.add(null);
        Assert.assertTrue(list.indexOf(null) == list.size() - 1);
        list.remove(null);
        Assert.assertEquals(-1, list.indexOf(null));
        Assert.assertEquals(-1, list.indexOf(5000));
    }

    @Test
    public void testContains() throws Exception {
        List<String> list = new SingleLinkedList<>();
        list.add("first element");
        list.add("second element");
        list.add("third element");
        list.add(null);

        Assert.assertTrue(list.contains("first element"));
        Assert.assertTrue(list.contains("second element"));
        Assert.assertTrue(list.contains("third element"));
        Assert.assertTrue(list.contains(null));
        Assert.assertFalse(list.contains("someElement"));
        list.remove(null);
        Assert.assertFalse(list.contains(null));
    }

    @Test
    public void testIterator() throws Exception {
        List<Integer> list = createList();
        Assert.assertNotNull(list.iterator());
        Assert.assertEquals(SingleLinkedList.SingleLinkLIter.class, list.iterator().getClass());
    }

    @Test
    public void testClear() throws Exception {
        List<Integer> list = createList();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertTrue(i == list.get(i));
        }
        list.removeAll();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertFalse(list.contains(i));
        }
        Assert.assertTrue(0 == list.size());
    }

    @Test
    public void testSize() throws Exception {
        List<Integer> list = createList();
        int size = list.size();
        list.add(45);
        size++;
        Assert.assertTrue(size == list.size());
        list.remove(45);
        size--;
        Assert.assertTrue(size == list.size());
        list.removeAll();
        Assert.assertTrue(0 == list.size());
    }

    public List<Integer> createList() {
        List<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        return list;
    }
}

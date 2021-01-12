/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.bst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class BSTTest {
    
    Integer[] arr = {20, 15, 10, 13, 8, 17, 40, 50, 30, 7};
    int[] height = {0, 1, 2, 3, 3, 3, 3, 3, 3, 4};
    Integer[] inorderT = {7, 8, 10, 13, 15, 17, 20, 30, 40, 50};
    Integer[] preorderT = {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};

    BST<Integer> instance;
    
    BST<Integer> ins; 

    public BSTTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new BST<>();
        for (int i : arr) {
            instance.insert(i);
        }
        
        ins = new BST<>();
        ins.insert(1);
    }

    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testRoot() {
        assertEquals(instance.root, instance.root());

        BST<String> sInstance = new BST<>();
        assertNull(sInstance.root());
    }
    
    /**
     * Tests of size method, of class BST.
     */
    @Test
    public void testToString(){
        assertEquals("1", ins.toString().trim());
        
        assertEquals(122, instance.toString().length());
    }

    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        assertEquals(instance.size(), arr.length);

        BST<String> sInstance = new BST<>();
        assertEquals(sInstance.size(), 0);
        sInstance.insert("A");
        assertEquals(sInstance.size(), 1);
        sInstance.insert("B");
        assertEquals(sInstance.size(), 2);
        sInstance.insert("A");
        assertEquals(sInstance.size(), 2);
    }

    /**
     * Test of insert method, of class BST.
     */
    @Test
    public void testInsert() {
        int array[] = {20, 15, 10, 13, 8, 17, 40, 50, 30, 20, 15, 10};
        BST<Integer> newInstance = new BST<>();
        for (int i = 0; i < 9; i++) {            //new elements
            newInstance.insert(array[i]);
            assertEquals(newInstance.size(), i + 1);
        }
        for (int i = 9; i < array.length; i++) {    //duplicated elements => same size
            newInstance.insert(array[i]);
            assertEquals(newInstance.size(), 9);
        }
    }

    /**
     * Test of remove method, of class BST.
     */
    @Test
    public void testRemove() {
        int qtd = arr.length;
        instance.remove(999);

        
        assertEquals(instance.size(), qtd);
        for (int i = 0; i < arr.length; i++) {
            instance.remove(arr[i]);
            qtd--;
            assertEquals(qtd, instance.size());
        }

        instance.remove(999);
        assertEquals(0, instance.size());
    }

    /**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {
        assertFalse(instance.isEmpty());
        instance = new BST<>();
        assertTrue(instance.isEmpty());

        instance.insert(11);
        assertFalse(instance.isEmpty());

        instance.remove(11);
        assertTrue(instance.isEmpty());
    }

    /**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {
        instance = new BST<>();
        assertEquals(instance.height(), -1);
        for (int idx = 0; idx < arr.length; idx++) {
            instance.insert(arr[idx]);
            assertEquals(instance.height(), height[idx]);
        }
        instance = new BST<>();
        assertEquals(instance.height(), -1);
    }

    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testSmallestElement() {
        assertEquals(7, instance.smallestElement());
        instance.remove(7);
        assertEquals(8, instance.smallestElement());
        instance.remove(8);
        assertEquals(10, instance.smallestElement());
    }

    /**
     * Test of processBstByLevel method, of class TREE.
     */
    @Test
    public void testProcessBstByLevel() {
        Map<Integer, List<Integer>> expResult = new HashMap<>();
        expResult.put(0, Arrays.asList(new Integer[]{20}));
        expResult.put(1, Arrays.asList(new Integer[]{15, 40}));
        expResult.put(2, Arrays.asList(new Integer[]{10, 17, 30, 50}));
        expResult.put(3, Arrays.asList(new Integer[]{8, 13}));
        expResult.put(4, Arrays.asList(new Integer[]{7}));

        Map<Integer, List<Integer>> result = instance.nodesByLevel();

        for (Map.Entry<Integer, List<Integer>> e : result.entrySet()) {
            assertEquals(expResult.get(e.getKey()), e.getValue());
        }

        BST<Integer> eInstance = new BST<>();
        expResult = new HashMap<>();
        result = eInstance.nodesByLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of inOrder method, of class BST.
     */
    @Test
    public void testInOrder() {
        List<Integer> lExpected = Arrays.asList(inorderT);
        assertEquals(lExpected, instance.inOrder());
    }

    /**
     * Test of preOrder method, of class BST.
     */
    @Test
    public void testpreOrder() {
        List<Integer> lExpected = Arrays.asList(preorderT);
        assertEquals(lExpected, instance.preOrder());
    }

    /**
     * Test of posOrder method, of class BST.
     */
    @Test
    public void testposOrder() {
        List<Integer> lExpected = Arrays.asList(posorderT);
        assertEquals(lExpected, instance.posOrder());
    }
    


}

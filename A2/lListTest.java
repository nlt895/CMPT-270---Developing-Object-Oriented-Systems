/*
 * Kristine Trinh
 * nlt895   
 * 11190412
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author krist
 */
public class lListTest {
    
    public lListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        lList instance = new lList();
        boolean result = instance.isEmpty();
        assertEquals(true, result);
        // non empty info field
        instance.info = "A";
        result = instance.isEmpty();
        assertEquals(false, result);
        // non empty next field
        instance.info = null;
        instance.nextList = new lList();
        result = instance.isEmpty();
        assertEquals(false, result);
    }

    /**
     * Test of insert method, of class LinkedList.
     */
    @Test
    public void testInsert() { 
        System.out.println("insert");
        String inInfo = "A";
        lList instance = new lList();
        instance.insertAtHead(inInfo);
        // test that info was stored in llist
        assertEquals("A",instance.info);
        // test that empty list exists off this one
        assertEquals(true,instance.nextList.isEmpty());
        // insert "B"
        instance.insertAtHead("B");
        // test that info was stored in llist
        assertEquals("B",instance.info);
        // test that previous info moved down one
        assertEquals("A",instance.nextList.info);
        // test that empty list exists off this one
    }

    /**
     * Test of nextList method, of class LinkedList.
     */
    @Test
    public void testNextList() {
        System.out.println("nextList");
        lList instance = new lList();
        String expResult = null;
        lList result = instance.nextList();
        assertEquals(expResult, result);
        
        lList instance2 = new lList();
        instance2.insertAtHead("A");
        instance2.insertAtHead("B");
        String expResult2 = "A" ;
        lList result2 = instance2.nextList();
        assertEquals (expResult2,result2.info);
        
    }

    /**
     * Test of lSize method, of class LinkedList.
     */
    @Test
    public void testLSize() { 
        System.out.println("lSize");
        lList instance = new lList();
        int expResult = 0;
        int result = instance.lSize();
        assertEquals(expResult, result);
        // insert one value
        instance.insertAtHead("A");
        result = instance.lSize();
        assertEquals(1, result);
        // insert three values
        instance.insertAtHead("B");
        instance.insertAtHead("C");
        instance.insertAtHead("D");
        result = instance.lSize();
        assertEquals(4, result);

    }

    /**
     * Test of deleteInfo method, of class LinkedList.
     */
    @Test
    public void testDeleteInfo() {
        System.out.println("deleteInfo");
        String inInfo = "A";
        lList instance = new lList();
        assertEquals(0, instance.lSize());
        instance.deleteInfo(inInfo);
        assertEquals(0, instance.lSize());
        // if we get here  then no exceptions
        // 1. Test delete when only one element
        instance.insertAtHead("A");
        assertEquals(1, instance.lSize());
        instance.deleteInfo("A");
        assertEquals(0, instance.lSize());
        // 2. test delete from head when size > 1
        instance = new lList();
        instance.insertAtHead("B");
        instance.insertAtHead("C");
        instance.deleteInfo("C");
        assertEquals(1, instance.lSize());
        assertEquals("B", instance.info);
        // 3. test delete from middle of a list
        instance = new lList();
        instance.insertAtHead("B");
        instance.insertAtHead("C");
        instance.insertAtHead("D");
        instance.deleteInfo("C");
        assertEquals(2, instance.lSize());
        assertEquals("D", instance.info);
        assertEquals("B", instance.nextList.info);

    }

    /**
     * Test of tranverse method, of class LinkedList.
     */
    @Test
    public void testTraverse() {
        System.out.println("tranverse");
        lList instance = new lList();
        String expResult = "";
        String result = instance.traverse();
        assertEquals(expResult, result);
        
        lList instance2 = new lList();
        instance2.insertAtHead("A");
        instance2.insertAtHead("F");
        instance2.insertAtHead("G");
        String expResult2 = "G,F,A,";
        assertEquals(expResult2,instance2.traverse());

    }  
    
    @Test
    public void testFindInfo() {
        System.out.println("findInfo");
        String inInfo = "";
        lList instance = new lList();
        lList result = instance.findInfo("A");
        assertEquals(null, result);
        instance.insertAtHead("A");
        result = instance.findInfo("A");
        assertEquals(instance, result);
        instance = new lList();
        instance.insertAtHead("B");
        instance.insertAtHead("C");
        instance.insertAtHead("D");
        result = instance.findInfo("D");
        assertEquals(instance, result);
        result = instance.findInfo("B");
        assertEquals(instance.nextList.nextList, result);
        result = instance.findInfo("C");
        assertEquals(instance.nextList, result);
        result = instance.findInfo("A");
        assertEquals(null, result);
    }

}

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
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of insert method, of class LinkedList.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        lList instance = new lList();
        instance.insert("A");
        instance.insert("B");
        instance.insert("C");
        assertFalse(instance.isEmpty());
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
        instance2.insert("A");
        instance2.insert("B");
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
        
        lList instance2 = new lList();
        instance2.insert("K");
        instance2.insert("Z");
        instance2.insert("P");
        int expResult2 = 3;
        int result2 = instance2.lSize();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of deleteInfo method, of class LinkedList.
     */
    @Test
    public void testDeleteInfo() {
        System.out.println("deleteInfo");
        lList instance = new lList();
        instance.insert("V");
        instance.deleteInfo("V");
        assertEquals(null,instance.info);  
        
        instance.insert("H");
        instance.insert("I");
        instance.insert("S");
        instance.insert("U");
        instance.insert("N");
        instance.deleteInfo("I");
        instance.deleteInfo("U");
        int expResult2 = 3;
        int result2 = instance.lSize();
        assertEquals(expResult2,result2);
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
        instance2.insert("A");
        instance2.insert("F");
        instance2.insert("G");
        String expResult2 = "G,F,A";
        assertEquals(expResult2,instance2.traverse());
    }  
    @Test
    public void testAll() {
        lList list = new lList();
        list.insert("A");
        list.insert("V");
        list.insert("A");
        list.insert("J");
        System.out.println("The content is " + list.traverse());
        System.out.println("The size is " + list.nextList().lSize());
        
        list.deleteInfo("V");
        list.deleteInfo("J");
        System.out.println("The content is " + list.traverse());
        System.out.println("The size is " + list.nextList().lSize());
        
    }
}

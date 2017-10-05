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

public class HashTableTest {
    
    public HashTableTest() {
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

    /**
     * Test of hashtoKey method, of class HashTable.
     */
    @Test
    public void testHashtoKey() {
        System.out.println("hashtoKey");
    
        HashTable instance = new HashTable();
        int expResult = 11;
        int result = instance.hashToKey("Vietnam");
        assertEquals(expResult,result);
        
        int expResult2 = 14;
        int result2 = instance.hashToKey("Canada");
        assertEquals(expResult2,result2);
        
        //Check if Candy whose have the same index as Canada can store 
        //at the same place
        int expResult3 = 14;
        int result3 = instance.hashToKey("Candy");
        assertEquals(expResult3,result3);
        
        int expResult4 = 2;
        int result4 = instance.hashToKey("Science");
        assertEquals(expResult4,result4);
        
    }

    /**
     * Test of insetInfo method, of class HashTable.
     */
    @Test
    public void testInsertInfo() {
        System.out.println("insertInfo");
     
        HashTable instance = new HashTable();
        instance.insertInfo("Vietnam");
        instance.insertInfo("Canada");
        instance.insertInfo("Science");
        assertNotNull(instance);
        
        assertEquals("Vietnam",instance.table[11].getInfo());
        assertEquals("Canada",instance.table[14].getInfo());
        assertEquals("Science",instance.table[2].getInfo());
    }

    /**
     * Test of findInfo method, of class HashTable.
     */
    @Test
    public void testFindInfo() {
        System.out.println("findInfo");
        HashTable table = new HashTable();
        table.insertInfo("Vietnam");
        table.insertInfo("Canada");
        table.insertInfo("Candy");
        assertNotNull(table);
        
        //Check if a valid string is in a table
        lList find = table.findInfo("Vietnam");
        assertEquals("Vietnam",find.info);
        
        /*
        Check if the function can search for 2 different strings at the same index
        */
        find = table.findInfo("Candy");
        assertEquals("Candy",find.info);     
        find = table.findInfo("Canada");
        assertEquals("Canada",find.info);
        
        //Check for an invalid string
        find = table.findInfo("Amazing");
        assertNull(find);

    }

    /**
     * Test of deleteInfo method, of class HashTable.
     */
    @Test
    public void testDeleteInfo() {
        System.out.println("deleteInfo");
        HashTable table = new HashTable();
        table.insertInfo("Vietnam");
        table.insertInfo("Computer");
        table.insertInfo("Science");
        table.insertInfo("Candy");
        
        table.deleteInfo("Candy");
        table.deleteInfo("Science");
        
        //Check if we can find the info after performing deletion
        lList find = table.findInfo("Candy");
        assertNull(find);
        find = table.findInfo("Science");
        assertNull(find);
    }
    
}

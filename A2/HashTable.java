/*
 * Kristine Trinh
 * nlt895   
 * 11190412
 */

public class HashTable {
    // The size of the hash table
    private static int size = 15;
    // A reference to the array of the linked list
    lList [] table;
    
    /**
     * The constructor of an empty hash table
     */
    public HashTable() {
        table = new lList [size];
        for(int i = 0; i < table.length; i++) {
            table[i] = new lList();
        }
    }
    
    /**
     * Compute the index in hash table for info
     * Pre : info is not null and be text for string
     * Post: the info and hash table remain unchanged
     * @param info
     * @return an integer value as the index of the hash table 
     */
    public int hashToKey(String info) {
        String s2 = new String(info);
        //String object creates an array of char with the 1st char in pos 0
        int first_char =(int)s2.toCharArray()[0];
        //String object create an array of char with 2nd char in pos 1
        int second_char =(int)s2.toCharArray()[1];
        //the int cast of the 1st and 2nd char is divided by modulo 15
        int sum = (first_char + second_char)%15;
        return sum;
    }
    
    /**
     * Insert infoToStore into the hash table 
     * Pre : infoToStore is not null
     * Post : the input string is inserted into the hash table at the specific 
     * index which is derived from the hash function
     * @param infoToStore 
     * @retun nothing
     */
    public void insertInfo(String infoToStore) {
        int index = hashToKey(infoToStore);
        table[index].insertAtHead(infoToStore);
    }
    
    /**
     * Search for the infoToFind in the hash table
     * Pre : infoToFind is not null
     * Post : the hash table remain unchanged 
     * @Param infoToFind
     * @Return a reference to the lList object containing the infoToFind
     *         null if the InfoToFind is not in the hash table
     */
    public lList findInfo(String infoToFind) {
        int index = hashToKey(infoToFind);
        return table[index].findInfo(infoToFind);
    }
    
    /**
     * Delete infoToRemove from hash table 
     * Pre : infoTpStore is not null
     * Post : if the infoToRemove is not in the hash table then the hash table 
     * remains unchanged. Otherwise, the first occurrence of the infoToRemove is 
     * removed from the corresponding list
     * @Param infoToRemove 
     * @Return nothing
     */
    public void deleteInfo(String infoToRemove) {
        int index = hashToKey(infoToRemove);
        table[index].deleteInfo(infoToRemove);
    }
}


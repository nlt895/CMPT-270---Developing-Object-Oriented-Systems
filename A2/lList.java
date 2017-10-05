/*
 * Kristine Trinh
 * nlt895   
 * 11190412
 */

/*
 * The model of a linked list which contains a sequence of links. 
 * Each link stores an element and a reference to the next link in the sequence.
 */
public class lList {
    /*a string element that contains data*/
    public String info;
    /*a reference to the next link*/
    public lList nextList;
    
    /**
     * The constructor of an empty list 
     */
    public lList() {
        this.info = null;
        this.nextList = null;
    }
    
    /**
     * A method checks if the list is empty
     * Pre : none
     * Post: the list is unchanged
     * @Return true if the list is empty
               false otherwise
     */
    public boolean isEmpty() {
	return (info == null && nextList == null);
    }
    
    /**
     * Place inInfo into the linked list at the beginning of the current list
     * @Param inInfo the data that will be placed in the list
     * Post: inInfo is inserted as the first node of the linked list
     * @Return nothing
     */
    public void insertAtHead(String inInfo) {
        if (this.isEmpty()) {
           // insert into empty list?
            this.info = inInfo;
            this.nextList = new lList();
            return;
        }
        // insert into me
        this.nextList.insertAtHead(this.info);
        this.info = inInfo;
    }
    
    
    /**
     * Returns the list
     * Pre: none
     * Post: the list remains unchanged
     * @Return a reference to the beginning of the remaining list
               null if the list is empty
     */
    public lList nextList() {
	if (!isEmpty())
            return nextList;
        return null;
    }
    
    /**
     * Count the number of none-empty sublists in the current list
     * Pre: none
     * Post: number of nodes in the list
     * @Return an integer of the counted number
     */
    public int lSize() {        
        if (this.isEmpty()) {
            return(0);
        }
        return (this.nextList.lSize() + 1); // add one for me

    }
    
    /**
     * Delete target out of the list
     * @Param outInfo is to be deleted
     * Post: if the target is match, delete the matching node
             otherwise, the list remains unchanged
     * @Return nothing
     */
    public void deleteInfo(String outInfo) {
        if (this.isEmpty()) {
            return;
        }
        if (this.info != outInfo) {
            this.nextList.deleteInfo(outInfo);
        } else   { // I have the info 
            this.info = this.nextList.info;
            this.nextList.deleteInfo(this.info); // may be null
            if (this.info == null) {
                this.nextList = null; // I am empty now too.
            }
        }
        return;

    }
    
    /**
     * Returns the current list
     * Pre : none
     * Post: the list remains unchanged
     * @Return null if the list is empty
               otherwise, a string which is the current natural
               order of the linked list as a comma separated set of string
     */
    public String traverse() {     
        if (this.isEmpty()) {
            return("");
        }
        return (this.info + "," + this.nextList.traverse()); // don't care about the comma
    }
    
    /**
     * Get the info of the current object
     * @return the info of the object
     */
    public String getInfo() {
        return info;
    }
    
    /**
     * Find the head of a linked list whose info matches the input parameter
     * Precondition none
     * Post condition: reference to a head with InInfo as its contents if inInfo exists in the LList
     * Post Condition: null if inIno does not exist within the current LList
     * @param inInfo
     * @return LLIst a LList reference to a linked list whose head contains the info; null otherwise.
     */
    public lList findInfo(String inInfo) {
        // this will be solved recursively
        // Base 1. Empty
        if (this.isEmpty()) {return (null);}
        // Base 2. I have the info
        if (this.info == inInfo) { return (this); }
        // recursive case
        return (this.nextList.findInfo(inInfo));
    }
    
}

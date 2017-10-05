/*
 * Kristine Trinh
 * nlt895   
 * 11190412
 */
import java.util.Stack;

/*
 * The model of a linked list which contains a sequence of links. 
 * Each link stores an element and a reference to the next link in the sequence.
 */
public class lList {
    /*a string element that contains data*/
    public String info;
    /*a reference to the next link*/
    public lList nextList;
    
     /*
    a method checks if the list is empty
    pre : none
    post: the list is unchanged
    @return true if the list is empty
            false otherwise
    */
    public boolean isEmpty() {
	return (info == null && nextList == null);
    }
    
     /*
    place inInfo into the linked list at the beginning of the current list
    @param inInfo the data that will be placed in the list
    post: inInfo is inserted as the first node of the linked list
    @return nothingS
    */
    public void insert(String info) {
	lList oldList = new lList();
	oldList.info = this.info;
	oldList.nextList = this.nextList;
	this.info = info;
	this.nextList = oldList;
    }
    
    
    /*
    returns the list
    pre: none
    post: the list remains unchanged
    @return a reference to the beginning of the remaining list
            null if the list is empty
    */
    public lList nextList() {
	if (!isEmpty())
            return nextList;
        return null;
    }
    
    /*
    Count the number of none-empty sublists in the current list
    pre: none
    post: number of nodes in the list
    @return an integer of the counted number
    */
    public int lSize() {
	int result = 0;
	lList temp = new lList();
	temp.nextList = nextList;
	while (temp.nextList != null) {
		result++;
		temp = temp.nextList;
	}
	return result;
    }
    
    /*
    delete target out of the list
    @param outInfo is to be deleted
    poat: if the target is match, delete the matching node
          otherwise, the list remains unchanged
    @return nothing
    */
    public void deleteInfo(String outInfo) {
	Stack<String> stack = new Stack<String>();
	if (!isEmpty()) {
            lList newFirst = new lList();
            if (outInfo.equals(info)) {
                newFirst.info = nextList.info;
                newFirst.nextList = nextList.nextList;
                nextList = newFirst.nextList;
                info = newFirst.info;
                return;
            } else {
                stack.add(info);
                while (nextList.nextList != null) {
                    if (nextList.info.equals(outInfo) && nextList != null) {
                        nextList = nextList.nextList;
                    } else if (nextList.nextList == null && info.equals(outInfo)) {
                        while (!stack.isEmpty()) {
                            nextList.info = stack.pop();
                        }
                        return;
                    } else {
                        stack.add(nextList.info);
                        nextList = nextList.nextList;
                    }
                }
                lList temp = new lList();
                temp.nextList = null;
                lList oldList = new lList();
                while (!stack.isEmpty()) {
                    temp = new lList();
                    temp.info = stack.pop();
                    temp.nextList = oldList;
                    oldList = new lList();
                    oldList.nextList = temp.nextList;
                    oldList.info = temp.info;
                }
                nextList = temp.nextList;
                info = temp.info;
            }
	}
    }
    
    /*
    returns the current list
    pre : none
    post: the list remains unchanged
    @return null if the list is empty
            otherwise, a string which is the current natural
            order of the linked list as a comma separated set of string
    */
    public String traverse() {
        String result = "";
	if (!isEmpty()) {
            lList temp = new lList();
            temp.nextList = nextList;
            temp.info = info;
            while (temp.nextList != null) {
		result += temp.info + ",";
		temp = temp.nextList;
            }
            result = result.substring(0, (result.length() - 1));
	}
	return result;
    }

}

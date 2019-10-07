import java.util.Iterator;
import java.util.SortedSet;

/**
 * SortedSet used to store NFA states.
 */
public class SortedStateSet implements SortedSet {
    
    public SortedStateSet() {
        super();
    }

    /** 
     * Retrieves the unique state in this set identified by its name
     * @return the specified NFAState, or null if not found
    */
    public NFAState getByName(String name) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            NFAState state = it.next();
            if (state.name.equals(name)) return state;
        }
        return null;
    }
}
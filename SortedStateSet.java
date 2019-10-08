import java.util.Iterator;
import java.util.SortedSet;
import java.lang.ClassCastException;

/**
 * SortedSet used to store NFA states.
 */
public class SortedStateSet<NFAState> implements SortedSet {
    
    public SortedStateSet() {
        super();
    }

    /** 
     * Retrieves the unique state in this set identified by its name
     * @return the specified NFAState, or null if not found
    */
    public NFAState getByName(String name) throws ClassCastException {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            NFAState state;
            Object o = it.next();
            if (!o.getClass().getName().equals(NFAState.class.getName())) {
                throw new ClassCastException("Requested object was not of compatible type.");
            } else {
                state = (NFAState)o;
            }
            
            if (state.name.equals(name)) return state;
        }
        return null;
    }

    @Override
    public NFAState last() {
        return null;
    }
    @Override
    public NFAState first() {
        return null;
    }
    
    
}
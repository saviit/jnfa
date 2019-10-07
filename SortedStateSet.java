import java.util.Iterator;
import java.util.SortedSet;

public class SortedStateSet implements SortedSet {
    
    public SortedStateSet() {
        super();
    }

    // Retrieves the unique state in this set identified by its name
    public NFAState get(String name) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            NFAState state = it.next();
            if (state.name.equals(name)) return state;
        }
        return null;
    }
}
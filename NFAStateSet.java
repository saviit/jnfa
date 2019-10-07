import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Simple set class to store the states for an NFA
 */
public class NFAStateSet implements Iterable<NFAState> {
    private ArrayList<NFAState> states;

    public NFAStateSet() { states = new ArrayList<NFAState>(0); }
    public NFAStateSet(NFAState[] s) {
        states = new ArrayList<NFAState>(s.length);
        addAll(s);
    }
    public NFAStateSet(Collection<NFAState> s) {
        states = new ArrayList<NFAState>(s.size());
        addAll(s);
    }

    public void add(NFAState s) {
        if (!contains(s)) {
            states.add(s);
        }
    }

    public void addAll(NFAState[] s) {
        for (int i = 0; i < s.length; i++) {
            add(s[i]);
        }
    }
    public void addAll(Collection<NFAState> s) {
        Iterator<NFAState> it = s.iterator();
        while (it.hasNext()) {
            NFAState ns = it.next();
            this.add(ns);
        }
    }

    public boolean contains(NFAState s) {
        return states.contains(s);
    }

    public boolean contains(String name) {
        Iterator<NFAState> it = this.iterator();
        while (it.hasNext()) {
            NFAState s = it.next();
            if (s.name.equals(name)) return true; 
        }
        return false;
    }

    public Iterator<NFAState> iterator() {
        return states.iterator();
    }

    public NFAState get(int index) {
        if (index < states.size()) {
            return states.get(index);
        } else throw new IndexOutOfBoundsException("Requested index was out of bounds: " + index);
    }

    public NFAState get(String name) {
        Iterator<NFAState> it = this.iterator();
        while (it.hasNext()) {
            NFAState s = it.next();
            if (s.name.equals(name)) return s; 
        }
        return null;
    }

    public int indexOf(NFAState s) {
        if (contains(s)) return states.indexOf(s);
        else throw new NoSuchElementException("Could not find element: " + s.name);
    }

    public boolean isEmpty() {
        return states.size() == 0 ? true : false;
    }

    public void remove(NFAState s) {
        if (contains(s)) {
            states.remove(s);
        }
    }

    public int size() {
        return states.size();
    }
}
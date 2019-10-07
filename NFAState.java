import java.lang.Comparable;
import java.lang.NullPointerException;
import java.lang.ClassCastException;

public class NFAState implements Comparable {
    public String name;
    public boolean isEndState;
    public NFAState(String name, boolean b) {
        this.name = name;
        this.isEndState = b;
    }

    public int compareTo(Object other) throws NullPointerException, ClassCastException {
        if (other == null) throw new NullPointerException("Parameter must not be null.");
        if (!other.getClass().getName().equals(NFAState.class.getName())) {
            throw new ClassCastException("Cannot convert an object of type " + other.getClass().getName() 
                                       + " to an instance of type " + NFAState.class.getName() + ".");
        }
        NFAState s = (NFAState)other;
        if (s.equals(this)) return 0;
        else return s.name.compareToIgnoreCase(this.name);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (!o.getClass().getName().equals(NFAState.class.getName())) { return false; }
        NFAState s = (NFAState)o;
        if (s.name.equals(this.name) && s.isEndState == this.isEndState) return true;
        else return false;
        
    }
}
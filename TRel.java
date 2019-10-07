import java.util.SortedSet;
import java.util.Iterator;

public class TRel {
    public SortedSet<TFunc> tfuncs;
    
    public TRel() {
        tfuncs = new SortedSet<TFunc>() {};
    }

    public void add(TFunc tf) {
        tfuncs.add(tf);
    }

    public NFAState yield(NFAState current, Character input) {
        Iterator it = tfuncs.iterator();
        while (it.hasNext()) {
            TFunc tf = it.next();
            if (tf.startState.equals(current) && tf.ch == input ) {
                return tf.endState;
            } else return null;
        }
    }
}
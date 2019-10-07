import java.util.Iterator;
import java.util.ArrayList;

public class TRel {
    public ArrayList<TFunc> tfuncs;
    
    public TRel() {
        tfuncs = new ArrayList<TFunc>();
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
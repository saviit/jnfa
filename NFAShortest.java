import java.util.SortedSet;
import java.util.ArrayList;

public class NFAShortest {

    public static void main(String[] args) {
        // create a set of states
        SortedSet<NFAState> states = new SortedSet<NFAState>() { };
        for (int i = 1; i < 16; i++) {
            if (i == 10 || i == 15) {
                states.add(new NFAState("" + i, true));
            } else {
                states.add(new NFAState("" + i, false));
            }
        }
        // create alphabet
        SortedSet<Character> alphabet = new SortedSet<Character>() { };
        ArrayList<Character> c = {'a', 'b', 'c', 'd', 'o', 'p', 's', 't', 'u'};
        alphabet.addAll(c);

        
        // create arcs/paths
        TRel tr = new TRel();
        // tr.add(new TFunc(, eState, ch))
        
    }
}
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Minimal presentation of a (non-)deterministic finite automaton
 */
public class NFA {

    public NFAStateSet states;
    public Alphabet alphabet;
    public TRel tr;
    public NFAState istate;
    public NFAStateSet F;

    public NFA(NFAStateSet states, Alphabet alphabet, TRel tr, NFAState istate) {
        this.states = states;
        this.alphabet = alphabet;
        this.tr = tr;
        this.istate = istate;
        this.F = new NFAStateSet();
        Iterator<NFAState> it = this.states.iterator();
        while (it.hasNext()) {
            NFAState s = it.next();
            if (s.isEndState) this.F.add(s);
        }
    }

    // traverses the NFA according to the input string (ie. the language)
    // and returns true or false depending on whether the traversal was succesful
    // (ie. whether or not the NFA accepts the input language).
    //
    // A successful traversal depends on several conditions:
    // 1) any character c of an input string s must belong to the alphabet of the NFA 
    // 2) every transition t with a character c must result in non-null state,
    //    equal to or connected to the initial state
    // 3) all states traversable according to the above conditions must belong to the
    //    set of states defined for this NFA
    public boolean traverse(String s) {
        if (s == null) return false;
        else if (s.length() == 0) {
            return this.istate.isEndState;
        }
        else {
            char cs[] = s.toCharArray();
            int i = 0;
            NFAState curr = this.istate;
            while (i < cs.length) {

                NFAState ns = this.tr.yield(curr, cs[i]);
                if (ns == null) return false;
                else { 
                    i++;
                    curr = ns; 
                }
            }
            if (curr.isEndState) return true;
            return false;
        }
    }

    /**
     * Finds the shortest string that the NFA accepts. 
     * Currently does not account for one-letter-multiple-arcs situations,
     * ie. the class behaves more like a DFA where this method is concerned.
     * @return length of the shortest accepted string
     */
    public int shortest() {
        if (this.F.isEmpty()) {
            // System.out.println("Final state store is empty");
            return -1; // there are no final states in the NFA
        }
        if (istate.isEndState) return 0;

        ArrayList<String> paths = new ArrayList<String>();
        for (Character c : alphabet) {
            NFAState ns = tr.yield(istate, c);
            if (ns != null) {
                if (ns.isEndState) return 1;
                else paths.add(c.toString());
            }
        }
        // System.out.println("Initial paths: " + paths);
        int k = 2;
        
        while (k < states.size()) {
            for (int j = 0; j < paths.size(); j++) {
                String path = paths.get(j);
                NFAState curr = istate;
                NFAState nxt;
                for (int i = 0; i < path.length(); i++) {
                    curr = tr.yield(curr, path.charAt(i));
                }
                for (Character c : alphabet) {
                    nxt = tr.yield(curr, c);
                    if (nxt != null) {
                        if (nxt.isEndState) {
                            // System.out.println("Shortest accepted language was: " + path + c.toString());
                            return path.length() + 1;
                        } else {
                            String s = path + c.toString();
                            paths.set(j, s);
                        }
                    }
                }
            }
            k++;
        }
        // System.out.println("No valid paths");
        return -2; // if we get here, there was no valid path
    }
}
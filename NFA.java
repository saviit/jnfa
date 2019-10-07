import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * Minimal presentation of a non-deterministic finite automaton
 */
public class NFA {

    public SortedSet<NFAState> states;
    public Alphabet alphabet;
    public TRel tr;
    public NFAState istate;
    public SortedSet<NFAState> F;

    public NFA(SortedSet<NFAState> states, Alphabet alphabet, TRel tr, NFAState istate) {
        this.states = states;
        this.alphabet = alphabet;
        this.tr = tr;
        this.istate = istate;
        Iterator<NFAState> it = this.states.iterator;
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

    // // returns the length of the shortest language accepted by this NFA
    // public int shortest() {
    //     // if initial state is also a final state NFA accepts an empty language
    //     if (traverse("")) return 0;

    //     Iterator it = alphabet.iterator();
    //     AbstractSet<Character> csSet = new AbstractSet<Character>(); 
    //     // gather valid arcs (from the initial state) into a temporary set
    //     while (it.hasNext()) {
    //         Character c = it.next();
    //         NFAState ns = this.tr.yield(this.istate, c);
    //         if (ns != null) {
    //             if (ns.isEndState) return 1; // found a final state on the path
    //             else csSet.add(c);
    //         }
    //     }

    //     int k;
    //     if (csSet.isEmpty()) return -1; // the NFA contains no valid paths
    //     else k = 1;
    //     ArrayList<String> paths = new ArrayList<String>();
    //     for (Character initial : csSet) { 
    //         paths.add(initial.toString()); 
    //     }

    //     // valid paths from initial state to final state are at most as long as the number of states in the NFA,
    //     // excluding the initial state. for example, the longest valid path from initial state to a final in an 
    //     // NFA with 5 states is  4.
    //     while (k < states.size()) {
    //         for (Character c : csSet) {
    //             NFAState curr = tr.yield(istate, c);
    //             for (Character a : alphabet) {
    //                 NFAState nxt = tr.yield(curr, a);
    //                 if (nxt != null) {
    //                     if (nxt.isEndState) return k + 1; // found a final state on the path
    //                     else {
    //                         String path = c.toString(); // + a.toString();
    //                         if (!paths.contains(path)) paths.add(path);
    //                         else { paths.set(paths.indexOf(path), (path + a.toString())); }
    //                     }
    //                 }
                    
    //             }
    //         }
    //         k++;
    //     }

    // }


    public int shortest2() {
        if (this.F.isEmpty()) return -1; // there are no final states in the NFA
        if (istate.isEndState) return 0;

        ArrayList<String> paths = new ArrayList<String>();
        for (Character c : alphabet) {
            NFAState ns = tr.yield(istate, c);
            if (ns != null) {
                if (ns.isEndState) return 1;
                else paths.add(c.toString());
            }
        }
        int k = 2;
        ArrayList<String> tmp_paths = new ArrayList<String>();
        
        while (k < states.size()) {
            for (String path : paths) {
                NFAState curr = istate;
                NFAState nxt;
                for (int i = 0; i < path.length(); i++) {
                    curr = tr.yield(curr, path.charAt(i));
                }
                for (Character c : alphabet) {
                    nxt = tr.yield(curr, c);
                    if (nxt != null) {
                        if (nxt.isEndState) return path.length() + 1;
                        else tmp_paths.add(path + c.toString());
                    }
                }
            }
            paths = tmp_paths;
            tmp_paths.clear();
            k++;
        }
        return -1; // if we get here, there was no valid path
    }
}
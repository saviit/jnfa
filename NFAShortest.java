import java.util.SortedSet;
import java.util.ArrayList;

public class NFAShortest {

    public static void main(String[] args) {
        // create a set of states
        SortedStateSet<NFAState> states = new SortedStateSet<NFAState>() { };
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
        tr.add(new TFunc(states.getByName("1"), states.getByName("2"), 'a'));
        tr.add(new TFunc(states.getByName("1"), states.getByName("3"), 'b'));
        tr.add(new TFunc(states.getByName("1"), states.getByName("4"), 'c'));
        tr.add(new TFunc(states.getByName("2"), states.getByName("5"), 'u'));
        tr.add(new TFunc(states.getByName("2"), states.getByName("6"), 'd'));
        tr.add(new TFunc(states.getByName("3"), states.getByName("6"), 'c'));
        tr.add(new TFunc(states.getByName("5"), states.getByName("7"), 't'));
        tr.add(new TFunc(states.getByName("6"), states.getByName("8"), 'p'));
        tr.add(new TFunc(states.getByName("6"), states.getByName("9"), 's'));
        tr.add(new TFunc(states.getByName("7"), states.getByName("10"), 'o'));
        tr.add(new TFunc(states.getByName("8"), states.getByName("11"), 't'));
        tr.add(new TFunc(states.getByName("9"), states.getByName("12"), 'a'));
        tr.add(new TFunc(states.getByName("9"), states.getByName("13"), 'p'));
        tr.add(new TFunc(states.getByName("12"), states.getByName("14"), 't'));
        tr.add(new TFunc(states.getByName("13"), states.getByName("15"), 'o'));

        // initialize NFA
        NFA nfa = new NFA(states, alphabet, tr, states.getByName("1"));
        
        int shortest = nfa.shortest2();
        System.out.println("Shortest ");
    }
}
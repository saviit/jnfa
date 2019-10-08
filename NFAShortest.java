import java.util.ArrayList;

public class NFAShortest {

    public static void main(String[] args) {
        // create a set of states
        NFAStateSet states = new NFAStateSet();
        for (int i = 1; i < 16; i++) {
            if (i == 10 || i == 15) {
                states.add(new NFAState("" + i, true));
            } else {
                states.add(new NFAState("" + i, false));
            }
        }
        // create alphabet
        Character carr[] = {'a', 'b', 'c', 'd', 'o', 'p', 's', 't', 'u'};
        Alphabet alphabet = new Alphabet(carr);
                
        // create arcs/paths
        TRel tr = new TRel();
        tr.add(new TFunc(states.get("1"), states.get("2"), 'a'));
        tr.add(new TFunc(states.get("1"), states.get("3"), 'b'));
        tr.add(new TFunc(states.get("1"), states.get("4"), 'c'));
        tr.add(new TFunc(states.get("2"), states.get("5"), 'u'));
        tr.add(new TFunc(states.get("2"), states.get("6"), 'd'));
        tr.add(new TFunc(states.get("3"), states.get("6"), 'c'));
        tr.add(new TFunc(states.get("5"), states.get("7"), 't'));
        tr.add(new TFunc(states.get("6"), states.get("8"), 'p'));
        tr.add(new TFunc(states.get("6"), states.get("9"), 's'));
        tr.add(new TFunc(states.get("7"), states.get("10"), 'o'));
        tr.add(new TFunc(states.get("8"), states.get("11"), 't'));
        tr.add(new TFunc(states.get("9"), states.get("12"), 'a'));
        tr.add(new TFunc(states.get("9"), states.get("13"), 'p'));
        tr.add(new TFunc(states.get("12"), states.get("14"), 't'));
        tr.add(new TFunc(states.get("13"), states.get("15"), 'o'));

        // initialize NFA
        NFA nfa = new NFA(states, alphabet, tr, states.get("1"));
        
        int shortest = nfa.shortest();
        System.out.println(String.format("Shortest accepted language was %d characters long.", shortest));

        String s1 = "auto";
        String s2 = "rekka";
        String s3 = "bcsat";
        String s4 = "bcspo";
        String s5 = "adspo";

        System.out.println(String.format("%6s      %10s", "String", "Acceptable"));
        System.out.println("----------------------");
        System.out.println(String.format("%6s      %10s", s1, nfa.traverse(s1)));
        System.out.println(String.format("%6s      %10s", s2, nfa.traverse(s2)));
        System.out.println(String.format("%6s      %10s", s3, nfa.traverse(s3)));
        System.out.println(String.format("%6s      %10s", s4, nfa.traverse(s4)));
        System.out.println(String.format("%6s      %10s", s5, nfa.traverse(s5)));
        
    }
}
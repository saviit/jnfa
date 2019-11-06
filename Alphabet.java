import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Simple collection class to store the alphabet for an NFA
 */
public class Alphabet implements Iterable<Character> {
    private ArrayList<Character> c;

    public Alphabet() { c = new ArrayList<Character>(0); }
    public Alphabet(char[] chars) {
        c = new ArrayList<Character>(chars.length);
        addAll(chars);
    }
    public Alphabet(Character[] chars) {
        c = new ArrayList<Character>(chars.length);
        addAll(chars);
    }
    public Alphabet(Collection<Character> chars) {
        c = new ArrayList<Character>(chars.size());
        addAll(chars);
    }

    public void add(char c) {
        Character cc = c;
        if (!contains(cc)) {
            this.c.add(cc);
        }
    }

    public void add(Character c) {
        if (!contains(c)) {
            this.c.add(c);
        }
    }

    public void addAll(char[] c) {
        Character cc;
        for (int i = 0; i < c.length; i++) {
            cc = c[i];
            add(cc);
        }
    }
    public void addAll(Character[] c) {
        for (int i = 0; i < c.length; i++) {
            add(c[i]);
        }
    }
    public void addAll(Collection<Character> c) {
        Iterator<Character> it = c.iterator();
        while (it.hasNext()) {
            Character ch = it.next();
            add(ch);
        }
    }

    public boolean contains(char c) {
        return contains(Character.valueOf(c));
    }
    public boolean contains(Character c) {
        return this.c.contains(c);
    }

    public Iterator<Character> iterator() {
        return c.iterator();
    }

    public Character get(int index) {
        if (index < c.size()) {
            return c.get(index);
        } else throw new IndexOutOfBoundsException("Requested index was out of bounds: " + index);
    }

    public int indexOf(Character c) {
        if (contains(c)) return this.c.indexOf(c);
        else throw new NoSuchElementException("Could not find element: " + c);
    }

    public void remove(char c) {
        Character cc = c;
        if (contains(cc)) {
            remove(cc);
        }
    }

    public void remove(Character c) {
        if (contains(c)) {
            this.c.remove(c);
        }
    }


}
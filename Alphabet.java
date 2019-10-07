import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Simple collection class to store the alphabet for an NFA
 */
public class Alphabet implements Iterable<Char> {
    private ArrayList<Char> c;

    public Alphabet() { c = new ArrayList<Char>(0); }
    public Alphabet(char[] chars) {
        c = new ArrayList<Char>(chars.length);
        addAll(chars);
    }
    public Alphabet(Character[] chars) {
        c = new ArrayList<Char>(chars.length);
        addAll(chars);
    }
    public Alphabet(Collection<Char> chars) {
        c = new ArrayList<Char>(chars.size());
        addAll(chars);
    }

    public void add(char c) {
        if (!contains(c)) {
            this.c.add(c);
        }
    }

    public void add(Character c) {
        if (!contains(c)) {
            this.c.add(c.charValue());
        }
    }

    public void addAll(char[] c) {
        for (int i = 0; i < c.length; i++) {
            this.add(c[i]);
        }
    }
    public void addAll(Character[] c) {
        for (int i = 0; i < c.length; i++) {
            this.add(c[i]);
        }
    }
    public void addAll(Collection<Char> c) {
        Iterator<Char> it = c.iterator();
        while (it.hasNext()) {
            char ch = it.next();
            this.add(ch);
        }
    }

    public boolean contains(char c) {
        return this.c.contains(c);
    }
    public boolean contains(Character c) {
        return this.c.contains(c.charValue());
    }

    public Iterator<Char> iterator() {
        return c.iterator();
    }

    public char get(int index) {
        if (index < c.size()) {
            return c.get(index);
        } else throw new IndexOutOfBoundsException("Requested index was out of bounds: " + index);
    }

    public int indexOf(char c) {
        if (contains(c)) return this.c.indexOf(c);
        else throw new NoSuchElementException("Could not find element: " + c);
    }

    public void remove(char c) {
        if (contains(c)) {
            this.c.remove(c);
        }
    }

    public void remove(Character c) {
        if (contains(c)) {
            this.c.remove(c.charValue());
        }
    }


}
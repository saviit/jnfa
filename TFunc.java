
public class TFunc {
    public NFAState startState;
    public NFAState endState;
    public Character ch;

    public TFunc(NFAState sState, NFAState eState, Character ch) {
        this.startState = sState;
        this.endState = eState;
        this.ch = ch;
    }
}
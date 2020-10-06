package BracketPair;

public class BracketPair {
    private char opener;
    private char closer;

    public BracketPair(char opener, char closer) {
        this.opener = opener;
        this.closer = closer;
    }

    public void setOpener(char opener) {
        this.opener = opener;
    }

    public void setCloser(char closer) {
        this.closer = closer;
    }

    public char getOpener() {
        return opener;
    }

    public char getCloser() {
        return closer;
    }

    @Override
    public String toString() {
        return "{" +
                "opener=" + opener +
                ", closer=" + closer +
                '}';
    }
}

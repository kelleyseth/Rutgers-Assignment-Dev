package braille;
/**
 * Stores a Braille symbol: character and encoding.
 * If this symbol doesn't correspond to a character, character and encoding are null.
 * @author Kal Pandit
 */
public class Symbol {
    private Character character;
    private String encoding;

    public Symbol() {
        character = null;
        encoding = null;
    }

    public Symbol(char character, String encoding) {
        this.character = character;
        this.encoding = encoding;
    }

    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String toString() {
        return "'" + character + "' " + encoding;
    }

}

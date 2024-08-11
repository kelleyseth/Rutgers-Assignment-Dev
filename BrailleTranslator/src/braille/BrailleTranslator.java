package braille;

import java.util.LinkedList;

/**
 * Contains methods to translate Braille to English and English to Braille using
 * a BST.
 * Reads encodings, adds characters, and traverses tree to find encodings.
 * 
 * @author Seth Kelley
 * @author Kal Pandit
 */
public class BrailleTranslator {

    private TreeNode treeRoot;

    /**
     * Default constructor, sets symbols to an empty ArrayList
     */
    public BrailleTranslator() {
        treeRoot = null;
    }

    /**
     * Reads encodings from an input file as follows:
     * - One line has the number of characters
     * - n lines with character (as char) and encoding (as string) space-separated
     * USE StdIn.readChar() to read character and StdIn.readLine() after reading
     * encoding
     * 
     * @param inputFile the input file name
     */
    public void createSymbolTree(String inputFile) {

        /* PROVIDED, DO NOT EDIT */

        StdIn.setFile(inputFile);
        int numberOfChars = Integer.parseInt(StdIn.readLine());
        for (int i = 0; i < numberOfChars; i++) {
            Symbol s = readSingleEncoding();
            addCharacter(s);
        }
    }

    /**
     * Reads one encoding from an input file and returns its corresponding
     * Symbol object
     * @return the symbol object
     */
    public Symbol readSingleEncoding() {
        char c = StdIn.readChar();
        String encoding = StdIn.readString();
        // readChar leaves \n unread, so put this here to skip over that
        StdIn.readLine();
        return new Symbol(c, encoding);
    }

    /**
     * Adds a character into the BST rooted at treeRoot.
     * Traces encoding path (0 = left, 1 = right), starting with an empty root.
     * Last digit of encoding indicates position (left or right) of character within
     * parent.
     * 
     * @param newSymbol the new symbol object to add
     */
    public void addCharacter(Symbol newSymbol) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Given a sequence of characters, traverse the tree based on the characters
     * to find the TreeNode it leads to
     * 
     * @param characters Sequence of braille (0s and 1s)
     * @return Returns the TreeNode of where the characters lead ot null if there is
     *         no path
     */
    public TreeNode getCharacterNode(String characters) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Given a character to look for in the tree will return the encoding of the
     * character
     * 
     * @param character The character that is to be looked for in the tree
     * @return Returns the String encoding of the character
     */
    public String findBrailleEncoding(char character) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Fixes the encoding by:
     * - Searching for the encoding to see if it is in the tree.
     * - If not in the tree, chase the left subtree to see if the remaining
     * substring leads to a character.
     * - If still not found, chase the right subtree.
     * Return the encoding or null if it does not exist (no 0s or 1s in encoding)
     * 
     * @param oldEncoding the old encoding
     * @return the encoding or null if not found
     */
    public String fixIncorrectEncoding(String oldEncoding) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Given a character, delete it from the BST and delete any encodings not
     * attached to a character (ie. no children).
     * 
     * @param symbol the symbol to delete
     */
    public void deleteSymbol(char symbol) {

        /* WRITE CODE HERE */

    }

    /**
     * Given an English message (input), right shift each character by the amount to
     * decypher what the message is
     * 
     * @param input The String of the shifted message
     * @return Returns the input shifted by amount
     */
    public String shiftMessage(String input) {

        StdIn.setFile(input);
        int amount = StdIn.readInt();
        String text = StdIn.readAll();

        /* WRITE YOUR CODE HERE */

    }

    /* DO NOT EDIT BELOW METHODS */

    /**
     * Given a sequence of Braille encodings, will decypher the encodings to form a
     * sentence in English
     * 
     * @param input The sequence of Braille inputs
     * @return Returns a String of the translated Braille input
     */
    public String translateBraille(String input) {

        StdIn.setFile(input);
        String inputMessage = StdIn.readAll();

        String output = ""; // Translated output to be returned
        TreeNode data; // Holds the tree node after the method call "getCharacterNode"

        // Loops through the number of characters to determine the nodes character value
        for (int i = 0; i < inputMessage.length(); i += 6) {
            // Node to hold tree node of found CharacterNode
            data = getCharacterNode(inputMessage.substring(i, i + 6));
            // Appends the TreeNodes data to the output message
            output += data.getData().getCharacter();
        }
        return output; // Translated output of the Braille input
    }


    public TreeNode getTreeRoot() {
        return this.treeRoot;
    }

    public void setTreeRoot(TreeNode treeRoot) {
        this.treeRoot = treeRoot;
    }

    private void printTree() {
        printTree(treeRoot, "", false, true);
    }

    private void printTree(TreeNode n, String indent, boolean isRight, boolean isRoot) {
        StdOut.print(indent);

        // Print out either a right connection or a left connection
        if (!isRoot)
            StdOut.print(isRight ? "|+1- " : "--0- ");

        // If we're at the root, we don't want a 1 or 0
        else
            StdOut.print("+--- ");

        if (n == null) {
            StdOut.println("null");
            return;
        }
        // If we have an associated character print it too
        if (n.getData() != null && (Character) n.getData().getCharacter() != null) {
            StdOut.print(n.getData().getCharacter() + " ->");
            StdOut.print(n.getData().getEncoding());
        }
        StdOut.println();

        // If no more children we're done
        if (n.getData() != null && n.getLeft() == null && n.getRight() == null)
            return;

        // Add to the indent based on whether we're branching left or right
        indent += isRight ? "|    " : "     ";

        printTree(n.getRight(), indent, true, false);
        printTree(n.getLeft(), indent, false, false);
    }

}

package braille;

/**
 * This class tests each method in the Hunger Games class to interactively
 * display outputs.
 *
 * @author Kal Pandit
 * @author Seth Kelley
 */
public class Driver {
    public static void main(String[] args) {
        String[] methods = { "createSymbolTree", "getCharacterNode", "findBrailleEncoding",
                "fixIncorrectEncoding", "translateBraille", "deleteSymbol", "shiftMessage" };
        String[] options = { "Test new file", "Test new method on the same file", "Quit" };
        int repeatChoice = 0;
        do {
            System.err.print("Enter an input text file name => ");
            String input = StdIn.readLine();
            System.err.println();
            BrailleTranslator studentBrailleTranslator = new BrailleTranslator();

            do {
                System.err.println("What method would you like to test? Later methods rely on previous methods.");

                for (int i = 0; i < methods.length; i++) {
                    System.err.printf("%d. %s\n", i + 1, methods[i]);
                }

                System.err.print("Enter a number => ");
                int choice = StdIn.readInt();
                StdIn.readLine();
                System.err.println();
                switch (choice) {
                    case 1:
                        testReadEncodings(studentBrailleTranslator, input);
                        break;
                    case 2:
                        testGetCharacterNode(studentBrailleTranslator);
                        break;
                    case 3:
                        testFindBrailleEncoding(studentBrailleTranslator);
                        break;
                    case 4:
                        System.err.print("Enter an encoding => ");
                        String enc = StdIn.readString();
                        StdIn.readLine();
                        testFixIncorrectEncoding(studentBrailleTranslator, enc);
                        break; 
                    case 5:
                        testTranslateBraille(studentBrailleTranslator);
                        break;
                    case 6:
                        System.err.print("Enter a character to delete => ");
                        char ch = StdIn.readChar();
                        StdIn.readLine();
                        testDeleteSymbol(studentBrailleTranslator, ch);
                        break;
                    case 7:
                        testShiftMessage(studentBrailleTranslator);
                        break;
                    default:
                        System.err.println("Not a valid method to test!");
                }

                StdIn.resync();

                System.err.println("\nWhat would you like to do now?");
                for (int i = 0; i < 3; i++) {
                    System.err.printf("%d. %s\n", i + 1, options[i]);
                }

                System.err.print("Enter a number => ");
                repeatChoice = StdIn.readInt();
                StdIn.readLine();
                System.err.println();

            } while (repeatChoice == 2);
        } while (repeatChoice == 1);
    }

    private static void testReadEncodings(BrailleTranslator studentBrailleTranslator, String input) {
        studentBrailleTranslator.createSymbolTree(input);
        printTree(studentBrailleTranslator.getTreeRoot());
    }

    private static void testFixIncorrectEncoding(BrailleTranslator studentBrailleTranslator, String input) {
        String res = studentBrailleTranslator.fixIncorrectEncoding(input);
        StdOut.println("New encoding:");
        if (res == null) {
            StdOut.println("Encoding not found!");
        } else {
            StdOut.println(res);
        }
    }

    private static void testDeleteSymbol(BrailleTranslator studentBrailleTranslator, char input) {
        studentBrailleTranslator.deleteSymbol(input);
        StdOut.println("Symbol Tree: ");
        printTree(studentBrailleTranslator.getTreeRoot());
    }

    private static void testGetCharacterNode(BrailleTranslator studentBrailleTranslator) {
        System.err.print("Enter an encoding => ");
        String enc = StdIn.readString();
        StdIn.readLine();

        TreeNode character = studentBrailleTranslator.getCharacterNode(enc);

        if (character == null)
            StdOut.println("Node does not exist!");
        else if (character.getData() == null)
            StdOut.println("This node has no data!");
        else
            StdOut.println("This encoding represents: " + character.getData().getCharacter());

    }

    private static void testTranslateBraille(BrailleTranslator studentBrailleTranslator) {
        System.err.print("Enter an file to read from => ");
        String file = StdIn.readString();
        StdIn.readLine();

        String outputMessage = studentBrailleTranslator.translateBraille(file);

        StdOut.println("The Braille encoding translates to:\n" + outputMessage);
    }

    private static void testFindBrailleEncoding(BrailleTranslator studentBrailleTranslator) {
        System.err.print("Enter a character to find => ");
        char c = StdIn.readChar();
        StdIn.readLine();

        String s = studentBrailleTranslator.findBrailleEncoding(c);

        if (s == null) {
            StdOut.println("Character is not in the tree!");
            return;
        }
        StdOut.println(s);
    }

    private static void testShiftMessage(BrailleTranslator studentBrailleTranslator) {
        System.err.print("Enter file for shifted message => ");
        String file = StdIn.readString();
        StdIn.readLine();

        String unshiftedMessage = studentBrailleTranslator.shiftMessage(file);

        StdOut.println("\nThe message has been translated to:\n" + unshiftedMessage);
    }

    private static void printTree(TreeNode root) {
        printTree(root, "", false, true);
    }

    private static void printTree(TreeNode n, String indent, boolean isRight, boolean isRoot) {
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

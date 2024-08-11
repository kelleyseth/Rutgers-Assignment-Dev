package seam;

public class Driver {

    public static void main(String[] args) {

        SeamCarver seamCarver = null;
        Picture seamPicture = null;
        String[] methods = { "Calculate Energy", "Populate Energy Array",
                "Find Horizontal Seam", "Remove Horizontal Seam", "Remove N Horizontal Seams",
                "Find Vertical Seam", "Remove Vertical Seam", "Remove N Vertical Seams",
                "Magic Eraser Horizontal", "Magic Eraser Vertical",
                // "Remove Horizontal Energy Seam", "Remove Vertical Energy Seam",
                "Print Energy Array", "Show Seam Picture", "Show Energy Picture",
                "Show Seam" };
        String[] options = { "Test new image file", "Test new method on the same image file", "Quit" };
        int repeatChoice = 0;

        do {
            System.out.print("Enter an image file => ");
            String imageFilename = StdIn.readString();
            seamPicture = new Picture(imageFilename);
            seamCarver = new SeamCarver(seamPicture);

            do {
                System.out.println("\n\nWhich method would you like to test?\n\"First setup the Seam Carver Object\"");

                for (int i = 0; i < methods.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, methods[i]);
                }

                System.out.print("\n\nEnter a number => ");
                int choice = StdIn.readInt();

                switch (choice) {
                    case 1:
                        executeCalcEnergy(seamCarver);
                        break;
                    case 2:
                        executePopulateEnergyArray(seamCarver);
                        break;
                    case 3:
                        executeFindHorizontalSeam(seamCarver);
                        break;
                    case 4:
                        executeRemoveHorizontalSeam(seamCarver);
                        break;
                    case 5:
                        executeRemoveNHorizontalSeams(seamCarver);
                        break;
                    case 6:
                        executeFindVerticalSeam(seamCarver);
                        break;
                    case 7:
                        executeRemoveVerticalSeam(seamCarver);
                        break;
                    case 8:
                        executeRemoveNVerticalSeams(seamCarver);
                        break;
                    case 9:
                        executeMagicEraserHorizontal(seamCarver);
                        break;
                    case 10:
                        executeMagicEraserVertical(seamCarver);
                        break;
                    case 11:
                        seamCarver.printEnergyArray();
                        break;
                    case 12:
                        seamCarver.showSeamPicture();
                        break;
                    case 13:
                        seamCarver.showEnergyPicture();
                        break;
                    case 14:
                        seamCarver.showSeam();
                        break;
                    default:
                        System.err.println("Not a valid method to test!");
                }

                System.err.println("\nWhat would you like to do now?");
                for (int i = 0; i < 3; i++) {
                    System.err.printf("%d. %s\n", i + 1, options[i]);
                }

                System.err.print("Enter a number => ");
                repeatChoice = StdIn.readInt();

            } while (repeatChoice == 2);
        } while (repeatChoice == 1);
    }

    private static void executeCalcEnergy(SeamCarver sc) {

        System.out.print("\n\nIn which column is the pixel would you like to find the energy? Enter a number => ");
        int col = StdIn.readInt();
        if (col >= sc.getWidth() || col < 0) {
            System.out.println("Aborting, the column entered is out of bounds!");
            return;
        }

        System.out.print("\nIn which row is the pixel would you like to find the energy? Enter a number => ");
        int row = StdIn.readInt();
        if (row >= sc.getHeight() || row < 0) {
            System.out.println("\n\nAborting, the row entered is out of bounds!");
            return;
        }

        System.out.println("The energy of the pixel at: " + row + ", " + col + " is " + sc.calcEnergy(row, col));
        System.out.println("Now that you've calculated one pixels energy lets calculate the entire energy array!");
    }

    private static void executePopulateEnergyArray(SeamCarver sc) {

        sc.populateEnergyArray();
        System.out.println("Energy Array has been populated.\nSelect \"Print Energy Array\" if you'd like to see it.");
        System.out.println("Warning, should only be used on smaller arrays to properly view in grid format!");

    }

    private static void executeFindHorizontalSeam(SeamCarver sc) {
        int[] seam = sc.findHorizontalSeam();

        System.out.println(
                "Horizontal Seam found!\n Would you like to see the indicies of the seam?\n\"This may be a vary long list\"");
        System.out.print("\n\nEnter 1 for YES or 2 for NO => ");
        int choice = StdIn.readInt();

        if (choice == 1) {
            for (int i = 0; i < seam.length; i++) {
                System.out.println(i + ": [" + seam[i] + "]");
            }
        }
    }

    private static void executeRemoveHorizontalSeam(SeamCarver sc) {

        sc.removeHorizontalSeam(sc.findHorizontalSeam());

        System.out.println("Horizontal Seam has been removed\nSelect \"Show Seam Picture\" to see the image.");
        System.out.println("You many not notice a change with only one seam removed, try to remove N seams later!");
    }

    private static void executeRemoveNHorizontalSeams(SeamCarver sc) {

        System.out.print("How many seams would you like to remove => ");
        int n = StdIn.readInt();

        sc.removeNHorizontalSeams(n);
        sc.showSeamPicture();
    }

    private static void executeFindVerticalSeam(SeamCarver sc) {

        int[] seam = sc.findVerticalSeam();

        System.out.println(
                "Vertical Seam found!\n Would you like to see the indicies of the seam?\n\"This may be a vary long list\"");
        System.out.print("\n\nEnter 1 for YES or 2 for NO => ");
        int choice = StdIn.readInt();

        if (choice == 1) {
            for (int i = 0; i < seam.length; i++) {
                System.out.println(i + ": [" + seam[i] + "]");
            }
        }
    }

    private static void executeRemoveVerticalSeam(SeamCarver sc) {

        sc.removeVerticalSeam(sc.findVerticalSeam());

        System.out.println("Vertical Seam has been removed\nSelect \"Show Seam Picture\" to see the image.");
        System.out.println("You many not notice a change with only one line removed, try to remove N seams later!");
    }

    private static void executeRemoveNVerticalSeams(SeamCarver sc) {

        System.out.print("How many seams would you like to remove => ");
        int n = StdIn.readInt();

        sc.removeNVerticalSeams(n);
        sc.showSeamPicture();
    }

    private static void executeMagicEraserHorizontal(SeamCarver sc) {

        sc.magicEraserHorizontal();
        sc.showSeamPicture();
    }

    private static void executeMagicEraserVertical(SeamCarver sc) {

        sc.magicEraserVertical();
        sc.showSeamPicture();
    }
}
package pacific;

/**
 * This class tests each method in the GarbagePatch class to interactively
 * display outputs.
 *
 * @author Kal Pandit
 * @author Seth Kelley
 */
public class Driver {
    public static void main(String[] args) {
        boolean constructorRun = false;
        String[] methods = { "createCurrentArray", "createGarbageDataList", "findGarbageFromData", "createGarbageArray", "hasGarbage", "pileGarbageByDensity", "simulateCurrent", "simulateCurrent - one argument", "simulateGarbageBreakdown", "numberOfPatches", "Run my own test cases" };
        String[] options = { "Test new file", "Test new method on the same file", "Quit" };
        int repeatChoice = 0;
        GarbagePatch studentGarbagePatch = null;
        do {
            do {
                System.err.println("What method would you like to test?");

                for (int i = 0; i < methods.length; i++) {
                    if (constructorRun) {
                        methods[0] = "createCurrentArray [START OVER]";
                    }
                    System.err.printf("%d. %s\n", i + 1, methods[i]);
                }

                System.err.print("Enter a number => ");
                int choice = StdIn.readInt();
                StdIn.readLine();
                System.err.println();
                switch (choice) {
                    case 1:
                        studentGarbagePatch = testCurrentArray();
                        constructorRun = true;
                        break;
                    case 2:
                        testData(studentGarbagePatch);
                        break;
                    case 3:
                        System.err.print("Enter a garbage type name => ");
                        String data = StdIn.readLine();
                        Garbage found = studentGarbagePatch.findGarbageFromData(data);
                        if (found != null) {
                            StdOut.println("Garbage piece found: " + found.toString());
                        }
                        else {
                            StdOut.println("Garbage piece not found.");
                        }
                        break;
                    case 4:
                        testGarbagePositions(studentGarbagePatch);
                        break;
                    case 5:
                        testHasGarbage(studentGarbagePatch);
                        break;
                    case 6:
                        testPileGarbage(studentGarbagePatch);
                        break;
                    case 7:
                        testCurrent(studentGarbagePatch);
                        break;
                    case 8:
                        testCurrentSteps(studentGarbagePatch);
                        break;
                    case 9:
                        testBreakdown(studentGarbagePatch);
                        break;
                    case 10:
                        testNumberOfPatches(studentGarbagePatch);
                        break;
                    case 11:
                        testCustomCases();
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

    private static GarbagePatch testCurrentArray() {
        System.err.print("Enter a current file name => ");
        String current = StdIn.readLine();
        GarbagePatch newPatch = new GarbagePatch();
        newPatch.createCurrentArray(current);
        newPatch.printCurrent();
        newPatch.printGarbageData();
        newPatch.printGarbagePositions();
        return newPatch;
    }

    private static void testData(GarbagePatch g) {
        System.err.print("Enter a data file name => ");
        String data = StdIn.readLine();
        g.createGarbageDataList(data);

        g.printCurrent();
        g.printGarbageData();
        g.printGarbagePositions();
    }

    private static void testGarbagePositions(GarbagePatch g) {
        System.err.print("Enter a garbage file name => ");
        String data = StdIn.readLine();
        g.createGarbageArray(data);

        g.printCurrent();
        g.printGarbageData();
        g.printGarbagePositions();
    }

    private static void testPileGarbage(GarbagePatch g) {
        if (constructorNullCheck(g))
         return;

        System.err.print("Enter a garbage density (double) => ");
        String densityRead = StdIn.readLine();
        double density = Double.parseDouble(densityRead);

        Garbage toAdd = new Garbage("PiledPiece", 100, density);
        StdOut.println("New Garbage piece called PiledPiece added.");

        System.err.print("Enter a row (i in [i][j]) => ");
        String xread = StdIn.readLine();
        int x = Integer.parseInt(xread);

        System.err.print("Enter a column (j in [i][j]) => ");
        String yread = StdIn.readLine();
        int y = Integer.parseInt(yread);

        g.pileGarbageByDensity(g.getGarbagePositions(), toAdd, x, y);

        g.printCurrent();
        g.printGarbagePositions();
        StdOut.println("\nNOTE: An arbitrary piece was piled. Run tasks 1-3 again to start over.\n");
    }

    private static void testCurrent(GarbagePatch g) {
        if (constructorNullCheck(g))
         return;

        g.simulateCurrent();

        g.printCurrent();
        g.printGarbagePositions();
    }

    private static void testCurrentSteps(GarbagePatch g) {
        if (constructorNullCheck(g))
         return;

        System.err.print("Enter a number of steps => ");
        String xread = StdIn.readLine();
        int x = Integer.parseInt(xread);
        g.simulateCurrent(x);

        g.printCurrent();
        g.printGarbagePositions();
    }

    private static void testBreakdown(GarbagePatch g) {
        System.err.print("Enter a time (double) => ");
        String timeRead = StdIn.readLine();
        double time = Double.parseDouble(timeRead);
        if (constructorNullCheck(g))
         return;

        g.simulateGarbageBreakdown(time);

        g.printCurrent();
        g.printGarbagePositions();
    }

    private static void testHasGarbage(GarbagePatch g) {
        if (constructorNullCheck(g))
         return;

        System.err.print("Enter a row (i in [i][j]) => ");
        String xread = StdIn.readLine();
        int x = Integer.parseInt(xread);

        System.err.print("Enter a column (j in [i][j]) => ");
        String yread = StdIn.readLine();
        int y = Integer.parseInt(yread);

        StdOut.println("Result of hasGarbage: " + g.hasGarbage(x, y));

    }

    private static void testNumberOfPatches(GarbagePatch studentGarbagePatch){
        if(constructorNullCheck(studentGarbagePatch))
        return;

        int numOfPatches = studentGarbagePatch.numberOfPatches();

        StdOut.println("The number of patches in this area is: " + numOfPatches);
    }

    private static void testCustomCases() {
        System.err.print("Enter a test case number from MyTestCases.java => ");
        String xread = StdIn.readLine();
        int x = Integer.parseInt(xread);

        switch(x) {
            case 1:
                MyTestCases.executeTestCaseOne();
                break;
            case 2:
                MyTestCases.executeTestCaseTwo();
                break;
            case 3:
                MyTestCases.executeTestCaseThree();
                break;
            case 4:
                MyTestCases.executeTestCaseFour();
                break;
            case 5:
                MyTestCases.executeTestCaseFive();
                break;
            case 6:
                MyTestCases.executeTestCaseSix();
                break;
            case 7:
                MyTestCases.executeTestCaseSeven();
                break;
            case 8:
                MyTestCases.executeTestCaseEight();
                break;
            case 9:
                MyTestCases.executeTestCaseNine();
                break;
            case 10:
                MyTestCases.executeTestCaseTen();
                break;
        }
    }

    private static boolean constructorNullCheck(GarbagePatch studentGarbagePatch) {
        if (studentGarbagePatch == null) {
            StdOut.println("STOP: Garbage Patch is null, run tasks 1-3 first.");
            return true;
        }
        return false;
    }

}
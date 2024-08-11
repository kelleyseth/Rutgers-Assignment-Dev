package pacific;

/**
 * OPTIONAL class for you to create sample test cases.
 * To run these test cases in the Driver:
 * 
 * DO NOT CALL THIS CLASS IN GarbagePatch.java
 */
public class MyTestCases {

    // Sample test case input files.
    public static final String currentFile = "current1.txt";
    public static final String garbageFile = "garbage1.txt";
    public static final String garbageDataFile = "garbagedata.txt";

    /**
     * Sample custom test case. 
     * - Creates a Garbage Patch instance.
     * - Calls constructor on the Garbage Patch.
     * - Prints all states of the garbage patch.
     */
    public static void executeTestCaseOne() {
        // 1. Instantiate a GarbagePatch object.
        GarbagePatch gp = new GarbagePatch();

        // 2. Call relevant methods ON the gp instance.
        gp.createCurrentArray(currentFile);
        gp.createGarbageDataList(garbageDataFile);
        gp.createGarbageArray(garbageFile);

        // 3. Call print methods on the relevant structures.
        gp.printCurrent();
        gp.printGarbageData();
        gp.printGarbagePositions();
    }

    public static void executeTestCaseTwo() {

    }

    public static void executeTestCaseThree() {

    }

    public static void executeTestCaseFour() {
        
    }

    public static void executeTestCaseFive() {
        
    }

    public static void executeTestCaseSix() {
        
    }

    public static void executeTestCaseSeven() {
        
    }

    public static void executeTestCaseEight() {
        
    }

    public static void executeTestCaseNine() {
        
    }

    public static void executeTestCaseTen() {
        
    }

}

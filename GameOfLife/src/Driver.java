import java.awt.*;  // For colors
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {

    // Lots of variables here

    // TO STUDENTS: This number is delay in milliseconds between you clicking something and you being able to click
    //              something else.  This is here because Java is so fast that if you click something, it'll register
    //              you clicking it multiple times before you let go of the mouse button.  If you want to make this
    //              responsiveness faster or slower, then decrement or increment this number.  Fidle around with it.
    public static final int DELAY = 250;

    // TO STUDENTS: This is the folder in which the Driver will read and write input files from and to.  Feel free to
    //              change this to whatever path you'd like.  HOWEVER, IT MUST BE RELATIVE TO THE SRC DIRECTORY THAT
    //              THIS FILE IS IN!!!!  The Driver appends this path to the path to the Driver, so if I wanted to save
    //              input files to the bin folder, I would change DIRECTORY to be "\..\bin" for Windows, or "/../bin"
    //              for Linux/Mac.
    //              Note:  Below, File.separator is simply "\" on Windows and "/" otherwise since they use different symbols in paths.
    public static final String DIRECTORY = File.separator + ".." + File.separator + "inputs";
    
    // These are constants, tbh they aren't rlly that useful and are kinda clutter
    public static final Color BACKGROUND_COLOR = new Color(40, 40, 40);
    public static final int BTN_HALFWIDTH = 10;
    public static final int BTN_HALFHEIGHT = 3;
    public static final int BOARD_HALFWIDTH = 30;
    public static final int BOARD_HALFHEIGHT = 30;
    public static final int DEAFULT_ROWS_AND_COLS = 10;

    // Arrays for name of each button for each page, include OPTIONS which is on all pages
    public static final String[] optionNames = {"Back", "Quit"};
    public static final String[] fileOptionNames = {"Save", "Reset"};
    public static final String[] constructorNames = {"Create Default Game", "Create Game with Input File",
                                                    "Create Custom Game"};
    public static final String[] inputNames = {"Open"};
    public static final String[] createNames = {"Clear", "-", "+", "-", "+", "Save and Create"};
    public static final String[] methodNames = {"Cell State", "Is Alive", "Alive Neighbors",
                                                  "Next Generation", "Next N Generations", "Communities"};
    public static final Button[] OPTIONS = {
        new Button(6, 95, 4, 3, "Back", true),
        new Button(16, 95, 4, 3, "Quit", true)
    };
    public static final Button[] FILE_OPTIONS = {
        new Button(26, 95, 4, 3, "Save", true),
        new Button(37, 95, 5, 3, "Reset", true)
    };

    public static String PATH;
    static {
    File driver = new File(Driver.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    PATH = driver.getPath() + DIRECTORY;
    }
    // Current page user is on, I forget why but there's an important reason I had to make this
    public static Page current;
    // Used for ensuring no weirdness happens with typing filenames
    public static char[] illegalKeys = {'/', '\\', '|', '"', '*', '<', '>', ':', '?'};
    // When switching between pages, these variables keep track of board and buttons on current page
    public static Button[] activeButtons;
    public static Board activeBoard;
    // Used for Next N Generations method
    public static boolean nextNGen = false;
    // Used for managing state in Cell State and Alive Neighbor
    // Really should be enums but it's too late to save this code
    public static boolean cellState = false;
    public static boolean aliveNeighbor = false;

    public static GameOfLife game;

    // Following is declaring & initializing elements of pages
    // Note there is another initializeElements() method a bit further below
    // The dimensions of these things are rlly arbitrary btw

    // CONSTRUCTOR elements
    public static Button[] constructors = new Button[constructorNames.length];
    
    // INPUT elements
    public static Text inputLabel = new Text(14,50, "Filename:");
    public static Rectangle inputInput = new Rectangle(42, 50, 20, 3, true);
    public static Text inputFilename = new Text(24, 50, "", "LEFT");
    public static Text inputExtension = new Text(64, 50, ".txt");
    public static Text inputError = new Text(50, 35, "");
    public static Button inputSubmit = new Button(80, 50, BTN_HALFWIDTH, BTN_HALFHEIGHT, inputNames[0], true);
    public static Button[] inputs = new Button[]{inputSubmit};

    // CREATE elements
    public static Button[] createBtns = new Button[createNames.length];
    public static Board createBoard = new Board(50, 50, 20, 20, DEAFULT_ROWS_AND_COLS, DEAFULT_ROWS_AND_COLS, false, new boolean[DEAFULT_ROWS_AND_COLS][DEAFULT_ROWS_AND_COLS]);
    public static Text createLabel = new Text(12,8, "Filename:");
    public static Rectangle createInput = new Rectangle(40, 8, 20, 3, true);
    public static Text createFilename = new Text(22, 8, "", "LEFT");
    public static Text createExtension = new Text(62, 8, ".txt");
    public static Text createError = new Text(50, 16, "");

    // METHOD elements
    public static Button[] methods = new Button[methodNames.length];
    public static Board methodBoard = new Board(65, 45, 30, 30, -1, -1, false, null);
    public static Text methodText = new Text(65, 87, "Select an Option");
    public static Rectangle generationsBox = new Rectangle(60, 80, 10, 3, true);
    public static Text stepsText = new Text(52, 80, "", "LEFT");
    public static Button methodSubmit = new Button(80, 80, 8, BTN_HALFHEIGHT, "Submit", true);
    public static String methodFilename = "";

    public static void main(String[] args) {
        initializeElements();
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenColor(StdDraw.WHITE);

        displayPage(Page.CONSTRUCTOR);
        current = Page.CONSTRUCTOR;

        while (true) {
            if (StdDraw.isMousePressed()) {
                // Check if option buttons in top-left are clicked
                for (Button btn : OPTIONS) {
                    if (btn.containsMouse()) {
                        run(btn);
                    }
                }

                // Check if buttons on current page are clicked
                for (Button btn : activeButtons) {
                    if (btn.containsMouse()) {
                        run(btn);
                    }
                }

                // Check save and reset
                if (current == Page.METHOD) {
                    for (Button btn : FILE_OPTIONS) {
                        if (btn.containsMouse()) {
                            run(btn);
                        }
                    }
                }

                // Check if cell state and alive neighbor are running
                if (current == Page.METHOD && StdDraw.isMousePressed() && activeBoard.containsMouse()) {
                    if (cellState) {
                        double mX = StdDraw.mouseX();
                        double mY = StdDraw.mouseY();
                        double[] coords = activeBoard.getCellCM(mX, mY);
                        int row = (int)Math.round((activeBoard.upperY - coords[0]) / activeBoard.incY);
                        int col = (int)Math.round((coords[1] - activeBoard.lowerX) / activeBoard.incX);
                        
                        methodText.text = (game.getCellState(row, col)) ? "The cell is ALIVE." : "The cell is DEAD.";
                        displayPage(Page.METHOD);
                        activeBoard.fillCell(coords[1], coords[0], StdDraw.RED);
                        StdDraw.show();
                        cellState = false;
                    
                    } else if (aliveNeighbor) {
                        double mX = StdDraw.mouseX();
                        double mY = StdDraw.mouseY();
                        double[] coords = activeBoard.getCellCM(mX, mY);
                        int row = (int)Math.round((activeBoard.upperY - coords[0]) / activeBoard.incY);
                        int col = (int)Math.round((coords[1] - activeBoard.lowerX) / activeBoard.incX);
    
                        methodText.text = "The cell has " + game.numOfAliveNeighbors(row, col) + " alive neighbors.";
                        displayPage(Page.METHOD);
                        activeBoard.fillCell(coords[1], coords[0], StdDraw.RED);
                        StdDraw.show();
                        aliveNeighbor = false;
                    }
                }

                
                // Check if filling in grid on CREATE page
                if (current == Page.CREATE && activeBoard.containsMouse()) {
                    double mX = StdDraw.mouseX();
                    double mY = StdDraw.mouseY();
                    
                    double[] coords = activeBoard.getCellCM(mX, mY);
                    int row = (int)Math.round((activeBoard.upperY - coords[0]) / activeBoard.incY);
                    int col = (int)Math.round((coords[1] - activeBoard.lowerX) / activeBoard.incX);
                    
                    activeBoard.board[row][col] = !(activeBoard.board[row][col]);
                    displayPage(Page.CREATE);
                    StdDraw.pause(DELAY);
                }
            }
            
            // Check next N generations
            if (current == Page.METHOD && nextNGen) {
                int keystroke = -1;
                if (StdDraw.hasNextKeyTyped()) {
                    keystroke = (int)StdDraw.nextKeyTyped();
                    String steps = stepsText.text;
                    int len = steps.length();
                    if (48 <= keystroke && keystroke <= 57 && len < 9) {
                        steps = steps.concat(Character.toString(keystroke));
                    } else if (keystroke == 8 && len > 0) {
                        steps = steps.substring(0, len - 1);
                    }
                    stepsText.text = steps;
                    displayPage(Page.METHOD);
                }
                
                if ((StdDraw.isMousePressed() && methodSubmit.containsMouse()) || (keystroke == 10)) {
                    try {
                        int n = Integer.parseInt(stepsText.text);
                        if (n >= 0) {
                            game.nextGeneration(n);
                            methodText.text = "Skipped " + n + " generations.";
                            stepsText.text = "";
                            nextNGen = false;
                        } else {
                            methodText.text = "Enter a nonnegative integer.";
                        }
                    } catch (NumberFormatException e) {
                        methodText.text = "Invalid number.";
                    }
                }
                
                displayPage(Page.METHOD);
            }

            // Check if typing on CREATE or INPUT page
            if ((current == Page.CREATE || current == Page.INPUT) && StdDraw.hasNextKeyTyped()) {
                char keystroke = StdDraw.nextKeyTyped();
                String filename = (current == Page.CREATE) ? createFilename.text : inputFilename.text;
                int len = filename.length();
                if ((int)keystroke == 8) { // 8 is backspace in ASCII
                    if (len > 0) {
                        filename = filename.substring(0, len - 1);
                    }
                } else if ((int)keystroke == 10) { // 10 is enter is Java (idk why it isn't 13)
                    String name = current == Page.CREATE ? "Save and Create" : "Open";
                    Button tmp = new Button(name);
                    run(tmp);
                    continue;
                } else {
                    if ((int)keystroke >= 32 && !(inArray(keystroke, illegalKeys)) && len < 20) {
                        filename = filename.concat(Character.toString(keystroke));
                    }
                }
                if (current == Page.CREATE) {
                    createFilename.text = filename;
                    displayPage(Page.CREATE);
                } else {
                    inputFilename.text = filename;
                    displayPage(Page.INPUT);
                }
            }
        }
    }


    public static void initializeElements() {
        // CONSTRUCTOR
        for (int i = 0; i < constructors.length; i++) {
            constructors[i] = new Button(50, 60 - 2*(5 + 2*BTN_HALFHEIGHT)*i, 3*BTN_HALFWIDTH, 2*BTN_HALFHEIGHT, constructorNames[i], true);
        }
        
        // CREATE
        int pmHW = 3;
        createBtns[0] = new Button(94, 95, 4, 3, createNames[0], true);
        createBtns[1] = new Button(50, 90, pmHW, pmHW, createNames[1], true);
        createBtns[2] = new Button(60, 90, pmHW, pmHW, createNames[2], true);
        createBtns[3] = new Button(50, 80, pmHW, pmHW, createNames[3], true);
        createBtns[4] = new Button(60, 80, pmHW, pmHW, createNames[4], true);
        createBtns[5] = new Button(82, 8, 14, BTN_HALFHEIGHT, createNames[5], true);

        // METHOD
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Button(17, 72 - (5 + 2*BTN_HALFHEIGHT)*i, 15, BTN_HALFHEIGHT, methodNames[i], true);
        }
}


    public static void displayPage(Page page) {
        clearPage();
        for (Button option : OPTIONS) {
            option.changeColor(StdDraw.RED);
        }

        switch(page) {
            case CONSTRUCTOR:
                Font tmp = StdDraw.getFont();
                StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
                StdDraw.text(50, 90, "Conway's Game of Life");
                StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
                StdDraw.text(50, 80, "Click the option to run your constructor with.");
                StdDraw.text(50, 76, "The third option will allow you to create a custom input file.");
                StdDraw.setFont(tmp);
        
                for (int i = 0; i < constructors.length; i++) {
                    constructors[i].changeColor(StdDraw.RED);
                }
        
                activeButtons = constructors;
                activeBoard = null;
                break;

            case INPUT:
                Font f = StdDraw.getFont();
                StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
                StdDraw.text(50, 80, "Enter an Input File");
                StdDraw.setFont(f);

                inputLabel.draw();
                inputInput.changeColor(StdDraw.GRAY);
                inputFilename.draw();
                inputError.changeColor(StdDraw.YELLOW);
                inputExtension.draw();
                inputSubmit.changeColor(StdDraw.RED);

                activeButtons = inputs;
                activeBoard = null;
                break;

            case CREATE:
                StdDraw.text(35, 90, "Rows:  " + createBoard.rows);
                StdDraw.text(36, 80, "Cols:  " + createBoard.cols);
                
                for (Button btn : createBtns) {
                    btn.changeColor(StdDraw.RED);
                }
                createBoard.draw();

                Font t = StdDraw.getFont();
                StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
                StdDraw.text(50, 24, "Select cells to toggle them alive and dead.");
                StdDraw.setFont(t);

                createLabel.draw();
                createInput.changeColor(StdDraw.GRAY);
                createFilename.draw();
                createExtension.draw();
                createError.changeColor(StdDraw.YELLOW);

                activeButtons = createBtns;
                activeBoard = createBoard;
                break;
                
            case METHOD:
                for (Button btn : FILE_OPTIONS) {
                    btn.changeColor(StdDraw.RED);
                }
                for (int i = 0; i < methods.length; i++) {
                    methods[i].changeColor(StdDraw.RED);
                }
                methodBoard.board = game.getGrid();
                methodBoard.changeColor(StdDraw.WHITE);

                Font temp = StdDraw.getFont();
                StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
                methodText.draw();
                StdDraw.setFont(temp);

                if (nextNGen) {
                    generationsBox.changeColor(StdDraw.GRAY);
                    stepsText.draw();
                    methodSubmit.changeColor(StdDraw.RED);
                }
                
                activeButtons = methods;
                activeBoard = methodBoard;
                break;
        }
        StdDraw.show(); // Here in order for double buffering to work
    }
        

    public static void clearPage() {
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
    }


    public static boolean inArray(String str, String[] arr) {
        for (String a : arr) {
            if (a.equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean inArray(char ch, char[] arr) {
        for (char a : arr) {
            if (Character.compare(ch, a) == 0) {
                return true;
            }
        }
        return false;
    }


    public static void run(Button btn) {
        if (inArray(btn.name, optionNames)) {

            switch (btn.name) {
                case "Back":
                    displayPage(Page.CONSTRUCTOR);
                    current = Page.CONSTRUCTOR;
                    break;
                case "Quit":
                    System.exit(0);
            }
        
        } else if (inArray(btn.name, fileOptionNames)) {

            switch (btn.name) {
                case "Reset":
                    // Students better not make a file named "default.txt" or else it won't go back to it
                    game = (methodFilename.equals("default")) ? new GameOfLife() : new GameOfLife(getAbsolutePath(methodFilename));
                    initializeMethod(game);
                    displayPage(Page.METHOD);
                    StdDraw.pause(DELAY);
                    break;
                
                case "Save":
                    try {
                        int version = getNextAvailableNumber(methodFilename);
                        String savedFilename = methodFilename + "_saved" + version + ".txt";
                        createFile(savedFilename);
                        methodText.text = "Grid saved as " + savedFilename + ".";
                    } catch (IOException e) {
                        methodText.text = "Error occurred in saving state.";
                    }
                    displayPage(Page.METHOD);
                    StdDraw.pause(DELAY);
                    break;
            }

        } else if (inArray(btn.name, constructorNames)) {

            switch (btn.name) {
                case "Create Default Game":
                    methodFilename = "default";
                    game = new GameOfLife();
                    initializeMethod(game);
                    displayPage(Page.METHOD);
                    current = Page.METHOD;
                    break;
                case "Create Game with Input File":
                    displayPage(Page.INPUT);
                    current = Page.INPUT;
                    while (StdDraw.hasNextKeyTyped()) {
                        StdDraw.nextKeyTyped();
                    }
                    break;
                case "Create Custom Game":
                    displayPage(Page.CREATE);
                    current = Page.CREATE;
                    while (StdDraw.hasNextKeyTyped()) {
                        StdDraw.nextKeyTyped();
                    }
                    break;
            }

        } else if (inArray(btn.name, inputNames)) {

            switch (btn.name) {
                case "Open":
                    methodFilename = inputFilename.text;
                    File inputFile = new File(PATH, methodFilename + ".txt");
                    if (inputFile.exists()) {
                        inputError.text = "";
                        inputFilename.text = "";
                        game = new GameOfLife(getAbsolutePath(methodFilename));
                        initializeMethod(game);
                        displayPage(Page.METHOD);
                        current = Page.METHOD;
                    } else {
                        inputError.text = "The file you input does not exist.";
                        displayPage(Page.INPUT);
                    }
                    break;
            }

        } else if (inArray(btn.name, createNames)) {

            boolean [][] newCells = null;
            switch (btn.name) {
                case "Clear":
                    activeBoard.rows = 10;
                    activeBoard.cols = 10;
                    activeBoard.board = new boolean[10][10];
                    break;
                case "-":
                    if (btn.y == 90 && activeBoard.rows > 3) {
                        activeBoard.rows--;
                    } else if (btn.y == 80 && activeBoard.cols > 3) {
                        activeBoard.cols--;
                    }
                    break;
                case "+":
                    if (btn.y == 90 && activeBoard.rows < 20) {
                        activeBoard.rows++;
                    } else if (btn.y == 80 && activeBoard.cols < 20) {
                        activeBoard.cols++;
                    }
                    break;

                case "Save and Create":
                    methodFilename = createFilename.text;
                    try {
                        if (createFile(methodFilename + ".txt")) {
                            createError.text = "";
                            createFilename.text = "";
                            game = new GameOfLife(getAbsolutePath(methodFilename));
                            initializeMethod(game);
                            displayPage(Page.METHOD);
                            current = Page.METHOD;
                        } else {
                            createError.text = "Filename already taken.";
                            displayPage(Page.CREATE);
                        }
                    } catch (IOException e) {
                        createError.text = "Invalid filename."; // Assuming this actually is the issue
                        displayPage(Page.CREATE);
                    }
                    return;

            }
            newCells = new boolean[activeBoard.rows][activeBoard.cols];
            int r = activeBoard.board.length;
            int c = activeBoard.board[0].length;
            for (int i = 0; i < Math.min(r, activeBoard.rows); i++) {
                for (int j = 0; j < Math.min(c, activeBoard.cols); j++) {
                    newCells[i][j] = activeBoard.board[i][j];
                }
            }
            activeBoard.board = newCells;
            displayPage(Page.CREATE);
            StdDraw.pause(DELAY);

        } else if (inArray(btn.name, methodNames)) {
            
            switch (btn.name) {
                case "Cell State":
                    // If no if statement, this runs while mouses is held down
                    // and board flashes bc it runs too fast
                    if (!cellState) {
                        cellState = true;
                        aliveNeighbor = false;
                        nextNGen = false;
                        methodText.text = "Select a cell to get state of.";
                        displayPage(Page.METHOD);
                        activeBoard.changeColor(StdDraw.RED);
                        StdDraw.show();
                    }
                    break;

                case "Is Alive":
                    nextNGen = false;
                    methodText.text = (game.isAlive()) ? "There are still living cells." : "There are no more living cells.";
                    displayPage(Page.METHOD);
                    break;
                
                case "Alive Neighbors":
                    // See cell state for why there's an if statement
                    if (!aliveNeighbor) {
                        cellState = false;
                        aliveNeighbor = true;
                        nextNGen = false;
                        methodText.text = "Select cell to count of alive neighbors of.";
                        displayPage(Page.METHOD);
                        activeBoard.changeColor(StdDraw.RED);
                        StdDraw.show();
                    }
                    break;

                case "Next Generation":
                    nextNGen = false;
                    game.nextGeneration();
                    methodText.text = "Next generation calculated.";
                    displayPage(Page.METHOD);
                    StdDraw.pause(DELAY);
                    break;

                case "Next N Generations":
                    if (!nextNGen) {
                        cellState = false;
                        aliveNeighbor = false;
                        nextNGen = true;
                        methodText.text = "Enter number of generations to skip.";
                        displayPage(Page.METHOD);
                        // Clear stack of key presses (it actually may be a queue, idk)
                        while (StdDraw.hasNextKeyTyped()) {
                            StdDraw.nextKeyTyped();
                        }
                    }
                    break;

                case "Communities":
                    nextNGen = false;
                    methodText.text = "Number of Communities: " + game.numOfCommunities();
                    displayPage(Page.METHOD);
                    break;
            }
        }
    }


    // Method to create input file
    public static boolean createFile(String filename) throws IOException {
        
        File file = new File(PATH, filename);
        if (!file.createNewFile()) {
            return false;
        }

        FileWriter inputWriter = new FileWriter(file);
        inputWriter.write(Integer.toString(activeBoard.rows) + "\n"); // rows
        inputWriter.write(Integer.toString(activeBoard.cols) + "\n"); // then cols

        for (boolean[] row : activeBoard.board) {
            for (boolean cell : row) {
                String spacing = (cell) ? "   " : "  ";
                inputWriter.write(Boolean.toString(cell) + spacing);
            }
            inputWriter.write("\n");
        }
        inputWriter.close();
        return true;
    }


    // Method to return abolsute path for given filename in DIRECTORY
    // ASSUMING .txt EXTENSION
    public static String getAbsolutePath(String filename) {
        return PATH + File.separator + filename + ".txt";
    }


    // This method just initializes things in method w/ game object since that can't be done
    // right at the start when it doesn't exist yet, this is just to cut down on code
    public static void initializeMethod(GameOfLife game) {
        boolean[][] grid = game.getGrid();
        methodBoard.board = grid;
        methodBoard.rows = grid.length;
        methodBoard.cols = grid[0].length;
        methodText.text = "Select an Option";
        cellState = false;
        aliveNeighbor = false;
        nextNGen = false;
    }


    // Get number to put at end of file for saving current state of board
    public static int getNextAvailableNumber(String currentFilename) {
        int num = -1;  // number to return for end of filename
        File[] filesInDirectory = new File("./").listFiles();

        for (File file : filesInDirectory) {
            // Ensure file is a file, not a directory
            if (file.isFile()) {
                String filename = file.getName();
                int indexOfExtension = filename.lastIndexOf(".");
                String beginning = currentFilename + "_saved";
                
                // Following will throw error if file doesn't have extension
                try {
                    // Ensure file is txt file that contains filename_saved in it
                    if (filename.substring(indexOfExtension).equals(".txt") && filename.contains(beginning)) {
                        // Get number from file, so if current filename is "name_saved3.txt", this will get the "3"
                        String currentNumber = filename.substring(filename.indexOf(beginning) + beginning.length(), indexOfExtension);
                        
                        // God forbid student changes something and this part isn't a number
                        try {
                            int potentialNum = Integer.parseInt(currentNumber);
                            if (potentialNum > num) {
                                num = potentialNum;
                            }
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return num + 1;
    }
}
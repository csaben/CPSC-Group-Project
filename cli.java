import java.io.FileNotFoundException;
import java.util.Scanner;

public class cli {
    public static void main(String[] args) {
		/*
		    initial user interaction with app
		 */
        while (true) {
            System.out.println("Choose one of the following");
            System.out.println("1. open novel from library (into gui)");
            System.out.println("2. download a new novel (user provides url)");
            System.out.println("3. Exit");
            System.out.println("");
            Scanner scanner = new Scanner(System.in);
            boolean bool = true;
            while (bool) {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    //request more gui related info before opening gui
                    //TED METHOD CALLED HERE; ASK FOR USER TO PICK A NOVEL; OPEN PICKED NOVEL IN GUI INSTANCE
                    try {
                        gui.getNovel();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    bool = false;
                } else if (choice == 2) {
                    //ask for novel url to be added (politely)
                    Scanner userInput = new Scanner(System.in);
                    System.out.println("Please enter the url to your novel's homepage on royal road");
                    System.out.println("must include https:// prefix!!!");
                    //TODO: merge my stuff with main project
                    //TODO: make ted's open gui function work inside of cli
                    String novelUrl = userInput.nextLine();
                    novel test = new novel();
                    test.saveNovel(novelUrl);
                    bool = false;
                } else if (choice == 3) {
                    System.exit(1);
                } else {
                    System.out.println("enter a valid int option");
                }
            }
        }
    }
}


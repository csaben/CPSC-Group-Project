import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GuiTester {
    //Tests the GUI with random stuff I pulled from RoyalRoad. Change the boolean in the constructor to test the chapter mode
    public static void main(String[] args) {
        try {
            gui.getNovel();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
}

import java.util.HashMap;

public class GuiTester {
    //Tests the GUI with random stuff I pulled from RoyalRoad. Change the boolean in the constructor to test the chapter mode
    public static void main(String[] args) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("Chapter 1 Game Over You’re Dead","“Hello, this is Jeff Juggernaut with my beautiful co-host Ruby Apocalypse\n bringing you the World Championship \n finals of the card collecting game that has taken the gaming world by storm, Hero Seeker Online. Are you all ready for this--");
        hash.put("Chapter 2 Mother’s Milk", "I twitched a pudgy finger and the message vanished.\n" +
                "\n" +
                "A moment later a door burst open and a gust of frigid air blew into the small log cabin.\n" +
                "\n" +
                "I thought we got mansions in heaven, not log cabins. Then again I didn't expect to make it to heaven.\n" +
                "\n" +
                "Maybe this is what dying feels like. A trippy combination of video games and hot vulnerable women. I can get used to it.\n" +
                "\n" +
                "My fantasy ended when a man wearing a furrowed brow and a stubble beard entered the room. His face was red and he panted heavily \nlike he'd just been out for a morning jog.\n" +
                "\n");
        GUI g = new GUI(hash, "Test Novel", true);
        g.read();
    }
}

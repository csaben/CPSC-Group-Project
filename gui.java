import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gui {
    //creates the GUI, taking a hashmap of chapter names mapped to content
    //a boolean to tell you whether you're in continuous scrolling or not
    //and a String for the novel name
    private boolean continuousScrolling;
    private LinkedHashMap novel;

    private String novelName;

    private String currentChapter;

    public gui(LinkedHashMap novel, String novelName, boolean continuousScrolling) {
        this.novel=novel;
        this.continuousScrolling=continuousScrolling;
        this.novelName=novelName;
    }
    public void read(){
        //Calls the appropriate method depending on whether or not we're in continuous scrolling mode
        if(continuousScrolling){
            String entireNovel = "";
            for (Object s: novel.keySet()){
                entireNovel= entireNovel+ "\n\n" + (String)s + "\n\n" + novel.get(s);
            }
            displayWindow(novelName, entireNovel);
        }else{
            chapterSelection();
        }
    }

    public void chapterSelection(){
        //Creates a ComboBox and a button, when the button is pressed closes this frame and goes to the chapter indicated in the window
        final JFrame f = new JFrame("Select a chapter");
        f.addWindowListener(new WindowHandler(f));
        final JComboBox cb = new JComboBox<>(novel.keySet().toArray());
        JButton b = new JButton(new AbstractAction("Read on!") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenKey = (String) cb.getSelectedItem();
                currentChapter = chosenKey;
                String title = novelName + ": " + (String) chosenKey;
                displayWindow(title, (String) novel.get(chosenKey));
                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
            }
        });
        JPanel p = new JPanel();
        p.add(cb);
        p.add(b);
        f.add(p);
        f.setSize(600, 600);
        f.setVisible(true);
    }
    public void addButtons(JPanel p, final JFrame f) {
        //adds buttons to panel, only if not in continuous scrolling

        if (continuousScrolling == false) {
            if(checkIncrement()!=1) {
                JButton nButton = new JButton(new AbstractAction() {
                    //moves us to next chapter
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newChapter = incrementChapter(1);
                        displayWindow(newChapter, (String) novel.get(newChapter));
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));

                    }
                });
                nButton.setIcon(new ImageIcon("Right Arrow.jpg"));
                nButton.setBounds(0, 0, 95, 30);
                p.add(nButton);
            }
            if(checkIncrement()!=0) {
                JButton pButton = new JButton(new AbstractAction() {
                    //moves us to previous chapter
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newChapter = incrementChapter(-1);
                        displayWindow(newChapter, (String) novel.get(newChapter));
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                });
                pButton.setIcon(new ImageIcon("Left Arrow.jpg"));
                pButton.setBounds(200, 200, 95, 30);
                p.add(pButton);
            }
        }
    }

    public void displayWindow(String title, String text) {
        //Creates the reader window with the selected novel/chapter and a scroll bar
        JFrame f = new JFrame(title);
        JPanel p = new JPanel();
        p.setBounds(0, 500, 600, 100);
        p.setBackground(Color.gray);
        addButtons(p,f);
        JTextPane tp = new JTextPane();
        tp.setText(texttoHTML(text));
        tp.setEditable(false);
        JScrollPane sta = new JScrollPane(tp);
        sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sta.setPreferredSize(new Dimension(300, 300));
        p.add(sta);
        f.add(p);
        f.addWindowListener(new WindowHandler(f));
        f.setSize(600, 600);
        f.setVisible(true);

    }
    public String incrementChapter(int chapter){
        // increments the chapter
        ArrayList<String> chapterNames = new ArrayList<>();
        for (Object s: novel.keySet()){
            chapterNames.add((String)s);
        }
        int chapterIndex = chapterNames.indexOf(currentChapter) + chapter;
        String newChapter = chapterNames.get(chapterIndex);
        currentChapter = newChapter;
        return newChapter;
    }
    public int checkIncrement(){
        // checks if the chapter can be incremented or decremented
        ArrayList<String> chapterNames = new ArrayList<>();
        for (Object s: novel.keySet()){
            chapterNames.add((String)s);
        }
        int chapterIndex = chapterNames.indexOf(currentChapter);
        if(chapterIndex==0){
            return 0;
        }if(chapterIndex == chapterNames.size()-1){
            return 1;
        }else{
            return 2;
        }
    }
    public static String texttoHTML(String text){
        // turns the text into HTML
        StringBuilder builder = new StringBuilder();
        boolean previousSpace = false;
        for (char c : text.toCharArray()){
            if (c == ' ') {
                if (previousSpace) {
                    builder.append("&nbsp;");
                    previousSpace = false;
                    continue;
                }
                previousSpace = true;
            }
            else{
                previousSpace = false;
            }
            switch (c){
                case '<' :
                    builder.append("&lt;");
                    break;
                case '>' :
                    builder.append("&gt;");
                    break;
                case '&' :
                    builder.append("&amp;");
                    break;
                case '"' :
                    builder.append("&quot;");
                    break;
                case '\t' :
                    builder.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default :
                    builder.append(c);
            }
        }
        String converted = builder.toString();
        String s = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(converted);
        converted = matcher.replaceAll("<a href=\"$1\">$1</a>");
        return converted;
    }
    public static void getNovel() throws FileNotFoundException {
        System.out.println("Please select a novel");
        File[] novelNames = getGetNovelNames();
        int i = 0;
        for (File n : novelNames) {
            i++;
            System.out.println(i + ". " + noHyphen(n.getName()));
        }
        Scanner s = new Scanner(System.in);
        int selection = s.nextInt()-1;
        File selectedFile = novelNames[selection];
        LinkedHashMap novel=readFiles(selectedFile);
        gui g = new gui(novel, noHyphen(selectedFile.getName()), false);
        g.read();
    }



    public static LinkedHashMap readFiles(File file){
        LinkedHashMap novel = new LinkedHashMap<>();
        File[] chapters = file.listFiles();
        Arrays.sort(chapters);
        for (File f: chapters) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(f, StandardCharsets.UTF_8.name());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            novel.put(noHyphen(f.getName()), content);
        }
        System.out.println(novel.keySet());
        return novel;
    }
    public static File[] getGetNovelNamesNovelNames() throws FileNotFoundException {

        File novelsFile = new File("novels");
        File filesList[] = novelsFile.listFiles();

        return filesList;
    }
    public static String noHyphen(String str) {
        str= str.replaceAll("-", " ");
        str = str.replaceAll(".txt", "");
        return str;
    }
}

class WindowHandler implements WindowListener {
    //Returns to main menu and sets completion status
    private JFrame frame;

    public WindowHandler(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void windowClosing(WindowEvent e) {

    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}


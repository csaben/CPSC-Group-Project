import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class gui {
    //creates the GUI, taking a hashmap of chapter names mapped to content
    //a boolean to tell you whether you're in continuous scrolling or not
    //and a String for the novel name
    private boolean continousScrolling;
    private HashMap novel;

    private String novelName;

    public gui(HashMap novel, String novelName, boolean continousScrolling) {
        this.novel=novel;
        this.continousScrolling=continousScrolling;
        this.novelName=novelName;
    }
    public void read(){
        //Calls the appropriate method depending on whether or not we're in continuous scrolling mode
        if(continousScrolling){
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
        JFrame f = new JFrame("Select a chapter");
        f.addWindowListener(new WindowHandler(f));
        JComboBox cb = new JComboBox<>(novel.keySet().toArray());
        JButton b = new JButton(new AbstractAction("Read on!") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenKey = (String) cb.getSelectedItem();
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
    public void addButtons(JPanel p, JFrame f) {
        //adds buttons to panel, only if not in continuous scrolling

        if (continousScrolling == false) {

            JButton nButton = new JButton(new AbstractAction("Next") {
                //moves us to next chapter
                @Override
                public void actionPerformed(ActionEvent e) {
                    //needs to open the next chapter using the displaywindow method
                    f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_OPENED));
                    f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));

                }
            });

            JButton pButton = new JButton(new AbstractAction("Prev") {
                //moves us to previous chapter
                @Override
                public void actionPerformed(ActionEvent e) {
                    //needs to go to the prev. chapter using the displaywindow method
                    f.dispatchEvent(new WindowEvent(f, WinowEvent.WINDOW_OPENED));
                    f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                }
            });
            nButton.setBounds(0, 0, 95, 30);
            pButton.setBounds(200, 200, 95, 30);
            p.add(pButton);
            p.add(nButton);
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
        tp.setText(text);
        tp.setEditable(false);
        JScrollPane sta = new JScrollPane(tp);
        sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        p.add(sta);
        f.add(p);
        f.addWindowListener(new WindowHandler(f));

        f.setSize(600, 600);
        f.setVisible(true);

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
        // Whatever method(s) set the completion status
        // Whatever method gives us the main menu
        displayMessage("WindowListener method called : windowClosing.");
    }
    public void windowOpened(WindowEvent e) {
        displayMessage("WindowListener method called : windowOpened.");
    }
    public void windowClosed(WindowEvent e) {
        displayMessage("WindowListener method called : windowClosed.");
    }
    public void windowIconified(WindowEvent e) {
        displayMessage("WindowListener method called : windowIconified.");
    }
    public void windowDeiconified(WindowEvent e) {
        displayMessage("WindowListener method called : windowDeiconified.");
    }
    public void windowActivated(WindowEvent e) {
        displayMessage("WindowListener method called : windowActivated.");
    }
    public void windowDeactivated(WindowEvent e) {
        displayMessage("WindowListener method called : windowDeactivated.");
    }
}


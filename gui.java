import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GUI {
    private boolean continousScrolling;
    private HashMap<String, String> novel;

    public GUI(HashMap novel, boolean continousScrolling) {
        novel = this.novel;
        continousScrolling = this.continousScrolling;
    }


    public void addButtons(JPanel p) {
        if (continousScrolling == false) {
            JButton nButton = new JButton(new AbstractAction("Next") {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JButton pButton = new JButton(new AbstractAction("Prev") {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            nButton.setBounds(0, 0, 95, 30);
            pButton.setBounds(200, 200, 95, 30);
            p.add(pButton);
            p.add(nButton);
        }
    }

    public void displayWindow() {
        JFrame f = new JFrame("Reader");
        JPanel p = new JPanel();
        p.setBounds(0, 500, 600, 100);
        p.setBackground(Color.gray);
        addButtons(p);
        JTextPane tp = new JTextPane();
        tp.setText("");
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
    private JFrame frame;

    public WindowHandler(JFrame frame) {
        this.frame = frame;
    }

    public void windowClosing(WindowEvent e) {
        //getScrollPosition()
        // Whatever method(s) set the completion status
        // Whatever method gives us the main menu
    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}


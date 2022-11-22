import javax.swing.*;
import java.awt.event.*;

public class gui {
    private boolean continousScrolling;

    public class ButtonListener implements ActionListener(){
        public void actionPerformed (Action Event e){
            JOptionPane.showMessageDialog(null, "You pushed the button!");
        }
    }

    public void addButton(String t, JFrame f) {
        if (continousScrolling == false) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonListener(text));
            f.getContentPane().add(button);
        }
    }
    public void closeWindow(){

    }

    public void displayWindow(String novelContent){

    }

    public void navigationBar(){

    }


}

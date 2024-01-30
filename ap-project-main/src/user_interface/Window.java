package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Window Class extends JPanel
 * It's used to create menus and input fields.
 */
public class Window extends JPanel{
    JFrame frame;
    private Vector<TextField> texts = new Vector<>();
    private int rowCnt;
    Window(){
        this.frame = GUI.getFrame();
        this.setLayout(new GridLayout(20, 1, 10, 5));
    }
    public Vector<TextField> getTexts(){
        return this.texts;
    }

    /**
     * Add a pressable button to the menu.
     * @param s the text that appears on the button
     * @param t the message that is sent after clicking the button
     */
    public void addButton(String s, String t){
        this.add(GUI.newButton(s, t));
        this.rowCnt++;
        this.setLayout(rowCnt);
    }

    /**
     * Add a text field to the menu.
     * @param s the text
     */
    public void addText(String s){
        var label = new JLabel(s, SwingConstants.CENTER);
        //label.setText(s);
        label.setFont(GUI.getFont());
        this.add(label);
        this.rowCnt++;
    }
    public void setLayout(int rows){
        this.setLayout(new GridLayout(rows, 1, 10, 5));
    }
    public void setLayoutInputStyle(int rows){
        this.setLayout(new GridLayout(rows, 2, 10, 5));
    }

    /**
     * Add an input field to the menu
     */
    public void addInputField(){
        TextField text = new TextField("");
        text.setFont(GUI.getFont());
        //this.setLayout(new GridLayout(4, 2, 10, 5));
        this.add(text);
        texts.add(text);
    }

    /**
     * Add an "enter" button that sends all the values in the input fields to <Code>UI</Code>.
     * Does nothing if any of the input fields in this menu are empty.
     */
    public void addEnterButton(){
        this.setLayoutInputStyle(texts.size() + 1);

        JButton button = new JButton("enter");
        button.setFont(GUI.getFont());

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(var text : texts){
                    if(text.getText().isBlank()){
                        return;
                    }
                }
                for(var text : texts){
                    UI.newLine(text.getText());
                }
            }
        });
        this.add(button);
    }
}

package user_interface;

import javax.swing.*;
import java.awt.*;

/**
 * The Graphical User Interface of the application.
 */
public class GUI{
    private static final int width = 800, height = 500, fontSize = 18;
    private static final Font font = new Font("Monospaced", Font.PLAIN, fontSize);
    private static JFrame frame;

    /**
     * Everything is initialized and ready to be seen.
     */
    public static void init(){
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Clear the window of all its contents.
     */
    public static void clear() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    /**
     * Visualize a <Code>Window</Code> on the GUI.
     * @param window the window you want to show
     */
    public static void showWindow(Window window){
        clear();
        frame.add(BorderLayout.CENTER, new JScrollPane(window));
        frame.setVisible(true);
    }

    /**
     * Create a button.
     * @param s the text that appears on the button
     * @param t the message that is sent to <Code>UI</Code> after the button is clicked
     * @return the button object
     */
    public static JButton newButton(String s, String t) {
        JButton button = new JButton(s);
        button.setFont(getFont());
        button.addActionListener(e -> UI.newLine(t));
        return button;
    }
    public static JFrame getFrame(){
        return frame;
    }
    public static Font getFont() {
        return font;
    }

    /**
     * Close the window when the application is about to shutdown.
     */
    public static void close(){
        frame.dispose();
        frame.setVisible(false);
    }
}

package user_interface;

import javax.swing.*;
import java.awt.*;

/**
 * The Graphical User Interface of the application.
 */
public class GUI{
    private static final int width = 600, height = 400, fontSize = 20;
    private static final Font font = new Font("Calibri", Font.PLAIN, fontSize);
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
     * Visualize a Window on the GUI.
     *  window the window you want to show
     */
    public static void showWindow(Window window){
        clear();
        frame.add(BorderLayout.CENTER, new JScrollPane(window));
        frame.setVisible(true);

    }

    /**
     * Create a button.
     *  s the text that appears on the button
     *  t the message that is sent to UI after the button is clicked
     * @return the button object
     */
    public static JButton newButton(String s, String t) {
        JButton button = new JButton(s);
        button.setFont(getFont());
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setPreferredSize(new Dimension(240,40));
        button.setBackground(new Color(230,46,46));
        button.setForeground(Color.WHITE);
        ImageIcon imageIcon = new ImageIcon("src/user_interface/logo.png");
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(32,32,java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
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

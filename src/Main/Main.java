package Main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        MyPanel panel = new MyPanel();

        window.setDefaultCloseOperation(3);
        window.setResizable(false);

        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.StartGameThread();
    }
}
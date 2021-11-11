import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int w = 600;
        int h = 600;
        JFrame jFrame = new JFrame();
        jFrame.setSize(w,h);
        jFrame.setTitle("Mundo");
        jFrame.add(new World(w,h));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}

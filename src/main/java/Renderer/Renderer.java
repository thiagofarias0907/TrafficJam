package Renderer;

import instance.Instance;
import javax.swing.*;

public class Renderer implements Runnable {

    @Override
    public void run() {

        JFrame jFrame = new JFrame();
        jFrame.setSize(800,800);
        jFrame.setTitle("Mundo");
        jFrame.add(Instance.getInstance().getWorld().getDrawable());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        while (true){
            jFrame.repaint();
        }
    }
}

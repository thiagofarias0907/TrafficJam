package Renderer;

import instance.Instance;
import javax.swing.*;

public class Renderer implements Runnable {

    @Override
    public void run() {

        while (true){
            Instance.getInstance().getWorld().getDrawable().repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

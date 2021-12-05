import View.View;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int w = 600;
        int h = 600;
        World world = new World(w,h);
        View view = new View(world,(w+200),h,"Mundo");

        JFrame jFrame = view;
        jFrame.pack();
        jFrame.setVisible(true);

//        world.moveCars();
    }

}

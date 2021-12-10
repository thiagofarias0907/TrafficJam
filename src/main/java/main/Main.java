package main;

import View.View;
import instance.Instance;
import instance.world.WorldDrawable;
import instance.world.cells.cellTypes.CellTypes;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        int w = 600;
        int h = 600;

        View view = new View(new JPanel(),(w+200),h,"Mundo");


        JFrame jFrame = view;
        jFrame.pack();
        jFrame.setVisible(true);

    }

}

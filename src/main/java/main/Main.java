package main;

import View.View;
import instance.Instance;
import instance.world.cells.cellTypes.CellTypes;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        int w = 600;
        int h = 600;

        String path = "target/classes/";
        String file = "malha1-caso1.txt";
        Instance instance = new Instance(600, 600, CellTypes.SYNCHRONIZED, path+file, 5, 100, 1000);


        View view = new View(instance.getWorld().getDrawable(),(w+200),h,"Mundo");

        JFrame jFrame = view;
        jFrame.pack();
        jFrame.setVisible(true);

    }

}

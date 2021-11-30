package main;

import instance.Instance;
import instance.world.cells.State.DangerZoneHandler;
import instance.world.cells.State.MyMutexBlocker;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {




        DangerZoneHandler dangerZoneHandler = new MyMutexBlocker();

        Instance instance = new Instance(600, 600, dangerZoneHandler, "C:\\Users\\alcgo\\OneDrive\\Documentos\\GitHub\\TrafficJam\\src\\main\\resources\\malha1-caso1.txt");

        JFrame jFrame = new JFrame();
        jFrame.setSize(600,600);
        jFrame.setTitle("Mundo");
        jFrame.add(instance.getWorld().getDrawable());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }

}

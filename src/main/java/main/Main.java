package main;

import instance.Instance;
import instance.world.cells.State.DangerZoneHandler;
import instance.world.cells.State.MyMutexBlocker;

public class Main {

    public static void main(String[] args) {




        DangerZoneHandler dangerZoneHandler = new MyMutexBlocker();

        Instance instance = new Instance(600, 600, dangerZoneHandler, "C:\\Users\\alcgo\\OneDrive\\Documentos\\GitHub\\TrafficJam\\src\\main\\resources\\malha1-caso2.txt", 5, 3000);


    }

}

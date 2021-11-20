import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int w = 600;
        int h = 600;
        JFrame jFrame = new JFrame();
        jFrame.setSize(w,h);
        jFrame.setTitle("Mundo");
        World world = new World(w,h);
        jFrame.add(world);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        int count = 0;
        while (count<10){
            world.moveCars();

//            jFrame.getContentPane().validate();
//            jFrame.getContentPane().repaint();
//            count++;
        }
    }

}

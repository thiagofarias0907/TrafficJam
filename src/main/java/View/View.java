package View;
//import World;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private JRadioButton rbSemaphore;
    private JButton btnStart;
    private JButton btnStop;
    private JSpinner spinnerQtyCars;
    private JTextField tfFilePath;
    private JRadioButton rbMonitor;
    private JLabel lbFilePath;
    private JPanel jpanelOptions;
    private JPanel jpanelCanvas;
    private JPanel mainPanel;

//    private World world;

    public View(JComponent world,int w,int h, String title) {
        super(title);
        this.setSize(w,h);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jpanelCanvas.add(world);
        jpanelOptions.setVisible(true);
//        this.jpanelCanvas.repaint();

//        this.world = (World) world;

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpanelCanvas.setVisible(true);
//                this.world.moveCars();

            }
        });
    }

    public void setWorld(JComponent world){
        JPanel jPanel = new JPanel();
        jPanel.add(world);
        jpanelCanvas = jPanel;
    }

}
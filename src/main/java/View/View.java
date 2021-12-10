package View;
//import World;
import instance.Instance;
import instance.builder.InstanceBuilder;
import instance.world.cells.cellTypes.CellTypes;

import javax.swing.*;
import java.awt.*;
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
    private ButtonGroup buttonGroup;


    public View(JComponent world,int w,int h, String title) {
        super(title);
        this.setSize(w,h);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rbMonitor.setSelected(true);
        this.tfFilePath.setText("target/classes/malha1-caso1.txt");
        this.spinnerQtyCars.setValue(10);
        jpanelOptions.setVisible(true);
        jpanelCanvas.setVisible(true);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Instance.getInstance()!=null){
                    if(Instance.getInstance().isRunning()) {
                        Instance.getInstance().stopRunning(true);
                        return;
                    }

                }

                jpanelCanvas.removeAll();
                jpanelCanvas.revalidate();
                jpanelCanvas.setVisible(true);

                if(!checkSelectionsAndFields())
                    return;

                String filePath = "";
                if(!tfFilePath.getText().isBlank())
                    filePath = tfFilePath.getText();

                CellTypes cellType = CellTypes.SEMAPHORE;
                if(rbMonitor.isSelected())
                    cellType = CellTypes.SYNCHRONIZED;

                InstanceBuilder instanceBuilder = new InstanceBuilder();
                instanceBuilder.setCarsQuantity((int) spinnerQtyCars.getValue());
                instanceBuilder.setCellTypes(cellType);
                instanceBuilder.setPath(filePath);
                instanceBuilder.setMaxVehicleSpeedInMs(500);
                instanceBuilder.setMinVehicleSpeedInMs(10);
                instanceBuilder.build();

                jpanelCanvas.add(Instance.getInstance().getWorld().getDrawable());
                jpanelCanvas.revalidate();
                btnStart.setEnabled(false);
            }

        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Instance.getInstance().getWorld().stopCarInitializer();
                if(Instance.getInstance() !=null)
                    Instance.getInstance().stopRunning(true);
                btnStart.setEnabled(true);
            }
        });
    }

    private boolean checkSelectionsAndFields() {

        if (tfFilePath.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Precisa Selecionar o Arquivo contendo a malha!");
            return false;
        }

        String spinnerValue = spinnerQtyCars.getValue().toString();
        if (spinnerQtyCars.getValue().toString().isBlank()) {
            JOptionPane.showMessageDialog(this, "O número de carros precisa ser entre 1 e 99");
            return false;
        }
        try {
            if (Integer.parseInt(spinnerValue)<=0)
                throw (new Exception());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "O número de carros precisa ser entre 1 e 99");
            return false;
        }

        if(!rbMonitor.isSelected()  && !rbSemaphore.isSelected() )
            JOptionPane.showMessageDialog(this, "É necessário selecionar um modo (Semáforo/Monitor)");


        return true;
    }

//    public void setWorld(JComponent world){
//        JPanel jPanel = new JPanel();
//        jPanel.add(world);
//        jpanelCanvas = jPanel;
//    }

}
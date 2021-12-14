package View;
import instance.Instance;
import instance.builder.InstanceBuilder;
import instance.world.cells.cellTypes.CellTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private JSpinner spnMinSpeed;
    private JSpinner spnMaxSpeed;
    private ButtonGroup buttonGroup;


    public View(JComponent world,int w,int h, String title) {
        super(title);
        this.setSize(w,h);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rbMonitor.setSelected(true);
        this.tfFilePath.setText("target/classes/malha1-caso1.txt");
        this.spinnerQtyCars.setValue(10);
        this.spnMinSpeed.setValue(500);
        this.spnMaxSpeed.setValue(2000);

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
                instanceBuilder.setMaxVehicleSpeedInMs((int) spnMaxSpeed.getValue());
                instanceBuilder.setMinVehicleSpeedInMs((int) spnMinSpeed.getValue());
                instanceBuilder.build();

                jpanelCanvas.add(Instance.getInstance().getWorld().getDrawable());
                jpanelCanvas.revalidate();
                btnStart.setEnabled(false);
            }

        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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


        Path filePath = Paths.get(tfFilePath.getText());
        if (!Files.exists(filePath)) {
            JOptionPane.showMessageDialog(this, "Arquivo Não Existe!");
            return false;
        }

        String spinnerValue = spinnerQtyCars.getValue().toString();
        if (spinnerQtyCars.getValue().toString().isBlank()) {
            JOptionPane.showMessageDialog(this, "O número de carros precisa ser entre maior que zero");
            return false;
        }
        try {
            if (Integer.parseInt(spinnerValue)<=0)
                throw (new Exception());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "O número de carros precisa ser entre maior que zero");
            return false;
        }

        if(!rbMonitor.isSelected()  && !rbSemaphore.isSelected() )
            JOptionPane.showMessageDialog(this, "É necessário selecionar um modo (Semáforo/Monitor)");


        return true;
    }


}
package pl.edu.pw.eiti.isi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class NewEmptyBoardDialog extends JDialog {

    private JSpinner widthInput;
    private JSpinner heightInput;
    private JSpinner randomCells;

    public NewEmptyBoardDialog() {
        super(MainWindow.getInstance(), "Nowa gra w życie", true);

        setSize(400, 120);
        setLocationRelativeTo(null);
        addComponents();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addComponents() {
        setLayout(new GridLayout(4, 2, 10, 2));
        addWidthInput();
        addHeightInput();
        addRandomCells();
        addButtons();
    }

    private void addWidthInput() {
        widthInput = new JSpinner(new SpinnerNumberModel(30, 10, Integer.MAX_VALUE, 1));
        addFormItem("Szerokość gry (liczba komórek)", widthInput);
    }

    private void addHeightInput() {
        heightInput = new JSpinner(new SpinnerNumberModel(15, 10, Integer.MAX_VALUE, 1));
        addFormItem("Wysokość gry (liczba komórek)", heightInput);
    }

    private void addRandomCells() {
        randomCells = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        addFormItem("Wstaw losowe komórki", randomCells);
    }

    private void addFormItem(String label, JComponent component) {
        add(new JLabel(label + ":", SwingConstants.RIGHT));
        add(component);
    }

    private void addButtons() {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOkClick());
        add(okButton);
    }

    protected void onOkClick() {
        MainWindow.getInstance().newEmptyBoard((Integer) (widthInput.getValue()), (Integer) (heightInput.getValue()), (Integer) (randomCells.getValue()));
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}

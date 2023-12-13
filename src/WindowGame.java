package src;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowGame extends JFrame {
    private final int colCellsAmount, rowCellsAmount;
    private JTable table;
    private CustomTable customTable;
    private ImagePacman pacman;
    private ImageGhost ghost;
    private ArrayList<Integer> wallsColsIndexes = new ArrayList<>();
    private ArrayList<Integer> wallsRowsIndexes = new ArrayList<>();

    public static void main(String[] args) {
        new WindowGame(15, 15);
    }

    public WindowGame(int colCellsAmount, int rowCellsAmount) {
        this.colCellsAmount = colCellsAmount;
        this.rowCellsAmount = rowCellsAmount;
        createTable();
        frameConfig();
        gameplayConfig();
        pack();
        setVisible(true);
    }

    private void createTable() {
        customTable = new CustomTable();
        table = new JTable(customTable);
        add(table);
    }

    private void frameConfig() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main.centerWindowToScreen(this);
        setBorderConfig();
        setResizable(false);
    }

    private void pacmanConfig() {
        pacman = new ImagePacman(this);
        addKeyListener(pacman);
    }

    private void ghostsConfig() {
        ghost = new ImageGhost(this, "red");
        addKeyListener(pacman);
    }

    private void gameplayConfig() {
        pacmanConfig();
        tableConfig();
        editTableCells();
        displayPacman();
    }

    private void tableConfig() {
        table.setDefaultRenderer(ImageIcon.class, new DisplayComponents());
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setEnabled(false);
        table.setRowHeight(25);
    }

    private void editTableCells() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(25);
            fillColorCell(i);
        }
    }

    public class DisplayComponents extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {

            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (row == 3 && column == 4) {
                cell.setBackground(Color.BLUE);
                wallsColsIndexes.add(column);
                wallsRowsIndexes.add(row);
                ((JLabel) cell).setIcon(null);
            } else if (row == 2 && column == 6) {
                ((JLabel) cell).setIcon(new ImageIcon("images/ghosts/purple/left.png"));
                cell.setBackground(Color.BLACK);
            } else if (row == pacman.getCurrImgPosRow() && column == pacman.getCurrImgPosCol()) {
                ((JLabel) cell).setIcon(pacman.getIcon());
                cell.setBackground(Color.BLACK);
            } else {
                ((JLabel) cell).setIcon(null);
                cell.setBackground(Color.BLACK);
            }

            ((JLabel) cell).setText("");

            return cell;

        }
    }

    class CustomTable extends AbstractTableModel {

        public int getColumnCount() {
            return colCellsAmount;
        }

        public int getRowCount() {
            return rowCellsAmount;
        }

        public Object getValueAt(int row, int column) {
            return row * column;
        }

        @Override
        public Class<?> getColumnClass(int col) {
            if (col == 9)
                return ImageIcon.class;
            return super.getColumnClass(col);
        }
    }

    public void displayImgInDifferentPos() {
        table.getColumnModel().getColumn(pacman.getCurrImgPosCol()).setCellRenderer(new WindowGame.DisplayComponents());
        table.repaint();
    }

    private void fillColorCell(int i) {
        table.getColumnModel().getColumn(i).setCellRenderer(new DisplayComponents());
    }

    private void setBorderConfig() {
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
    }

    private void displayPacman() {
        table.getColumnModel().getColumn(pacman.getCurrImgPosCol()).setCellRenderer(new DisplayComponents());
    }

    public JTable getTable() {
        return table;
    }

    public ArrayList<Integer> getWallsColsIndexes() {
        return wallsColsIndexes;
    }

    public ArrayList<Integer> getWallsRowsIndexes() {
        return wallsRowsIndexes;
    }
}
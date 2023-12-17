package src;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class WindowGame extends JFrame {
    private final int colsAmount, rowsAmount;
    private JTable table;
    private ImagePacman pacman;
    private ImageGhost ghost;
    private Point point;
    TimeUpdater timeUpdater = new TimeUpdater(1000);
    private DisplayComponents displayComp;
    private ArrayList<Integer> heartsCols = new ArrayList();
    private final int[][] wallsIndexes = {
            {0, 1, 3, 4, 5, 6, 8, 9},
            {0, 9},
            {0, 2, 3, 4, 6},
            {4, 6, 7, 9},
            {1, 2, 7, 9},
            {2, 4, 5, 7, 9},
            {0, 2, 5, 7, 9},
            {0, 2, 3, 5, 9},
            {0, 3, 5, 7, 9},
            {0, 1, 5, 7}
    };

    public static void main(String[] args) {
        new WindowGame(10, 10);
    }

    public WindowGame(int colsAmount, int rowsAmount) {
        this.colsAmount = colsAmount;
        this.rowsAmount = rowsAmount;
        displayComp = new DisplayComponents();
        assignHeartsCols();
        frameConfig();
        createTable();
        gameplayConfig();
        pack();
        setVisible(true);
    }

    private void assignHeartsCols() {
        heartsCols.add(4);
        heartsCols.add(5);
        heartsCols.add(6);
    }

    private void imgsStartPos() {
        pacman.imgStartPos();
        ghost.imgStartPos();
    }

    private void createTable() {
        CustomTable customTable = new CustomTable();
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
        ghost = new ImageGhost(this, "purple", 1, 3);
    }

    private void gameplayConfig() {
        tableConfig();
        pacmanConfig();
        ghostsConfig();
        editTableCells();
        imgsStartPos();
        pointsConfig();
        timeConfig();
        closeWindowByKeysCombination();
    }

    private void pointsConfig() {
        point = new Point();
        generatePoints();
    }

    private void timeConfig() {
        timeUpdater.start();
    }

    private void tableConfig() {
        table.setDefaultRenderer(ImageIcon.class, new DisplayComponents());
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setEnabled(false);
        table.setRowHeight(25);
    }

    private void closeWindowByKeysCombination() {
        KeyStroke closeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);

        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();
        inputMap.put(closeKeyStroke, "closeWindow");
        actionMap.put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowGame.this.dispose();
                new WindowStart().display();
            }
        });
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
            checkCollisionOccurred();
            editCell( (JLabel) cell, row, column);
            return cell;
        }
    }

    private void checkCollisionOccurred() {
        if (isColumnsSame(pacman, ghost) && isRowsSame(pacman, ghost)) {
            imgsStartPos();
            if (pacman.getAvailableHearts() > 0) {
                reduceHealth();
            } else
                endGame();
        }
    }

    private void endGame() {
        timeUpdater.stopTimer();
        new SaveStats(this);
    }

    private void reduceHealth() {
        if (!heartsCols.isEmpty()) {
            pacman.reduceHealth();
            removeLastHeart();
        }
    }

    private void editCell(JLabel lbl, int tabRow, int tabCol) {
        lbl.setText("");
        displayHearts(lbl, tabRow, tabCol);

        if (isCellForPlayTime(tabRow, tabCol))
            displayPlayTime(lbl);
        else if (isCellForPointsLbl(tabRow, tabCol))
            displayPoints(lbl);
        else if (isWallExistsIn(tabRow, tabCol))
            addWall(lbl);
        else if (isCellFor(ghost, tabRow, tabCol))
            ghost.changePos(lbl);
        else if (isCellFor(point, tabRow, tabCol) && isCellFor(pacman, tabRow, tabCol))
            collectPoint(lbl, tabRow, tabCol);
        else if (isCellFor(pacman, tabRow, tabCol))
            pacman.changePos(lbl);
        else if (isCellFor(point, tabRow, tabCol))
            fillWithPoint(lbl);
        else
            fillWithBlackColor(lbl);
    }

    private void collectPoint(JLabel lbl, int row, int col) {
        point.addCollectedPoint();
        pacman.changePos(lbl);
        removeAddedPoint(lbl, row, col);
        checkExistingPoints();
    }

    private void checkExistingPoints() {
        if (point.getCollectedPoints() >= point.getAllPoints())
            endGame();
    }

    private void generatePoints() {
        for (int row = 0; row < rowsAmount; row++)
            for (int col = 0; col < colsAmount; col++)
                if (col != getWallIndex(row, col) && !isCellFor(pacman, row, col))
                    point.addPoint(row, col);
    }

    private int getWallIndex(int row, int col) {
        try {
            for (int i = 0; i < wallsIndexes[row].length; i++)
                if (wallsIndexes[row][i] == col)
                    return col;
            return -1;
        } catch (IndexOutOfBoundsException e ) {
            return -1;
        }
    }

    private void fillWithBlackColor(JLabel lbl) {
        lbl.setIcon(null);
        lbl.setBackground(Color.BLACK);
    }

    private boolean isCellFor(Image img, int row, int col) {
        return isRowsSame(row, img) && isColumnsSame(col, img);
    }

    private boolean isCellFor(Point points, int row, int col) {
        return points.isPointExistsIn(row, col);
    }

    private void fillWithPoint(JLabel lbl) {
        lbl.setIcon(point.getImgPoint());
        lbl.setBorder(null);
    }

    private void removeAddedPoint(JLabel lbl, int row, int col) {
        point.removePoint(row, col);
        lbl.setIcon(null);
        lbl.setBackground(Color.BLACK);
    }

    private void displayPlayTime(JLabel lbl) {
        lbl.setText(String.valueOf(timeUpdater.getPlayTime()));
        lbl.setForeground(Color.MAGENTA);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setBackground(Color.BLACK);
        setPlayTimeFont(lbl);
    }

    private void setPlayTimeFont(JLabel lbl) {
        if (timeUpdater.getPlayTime() > 99)
            lbl.setFont(new Font("", Font.ITALIC, 10));
        else
            lbl.setFont(new Font("", Font.BOLD, 20));
    }

    private boolean isCellForPlayTime(int row, int column) {
        return row == 0 && column == 0;
    }

    private void displayHearts(JLabel lbl, int row, int column) {
        if (isCellForHeart(row, column))
            lbl.setIcon(pacman.getImgHeart());
        else
            lbl.setIcon(null);
    }

    private void displayPoints(JLabel lbl) {
        lbl.setForeground(Color.GREEN);
        lbl.setBackground(Color.BLACK);
        lbl.setFont(new Font("", Font.BOLD, 20));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setText(String.valueOf(point.getCollectedPoints()));
    }

    private boolean isCellForHeart(int row, int column) {
        return row == 0 && heartsCols.contains(column);
    }

    private boolean isCellForPointsLbl(int row, int column) {
        return row == 0 && column == 9;
    }

    private void addWall(JLabel lbl) {
        lbl.setBackground(Color.BLUE);
        lbl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    }

    public boolean isWallExistsIn(int rowTable, int colTable) {
        for (int row = 0; row < wallsIndexes.length; row++)
            if (rowTable == row)
                for (int column = 0; column < wallsIndexes[row].length; column++)
                    if (colTable == wallsIndexes[row][column])
                        return true;
        return false;
    }

    class CustomTable extends AbstractTableModel {

        public int getColumnCount() {
            return colsAmount;
        }

        public int getRowCount() {
            return rowsAmount;
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

    public void displayImgInDifferentPos(Image img) {
        try {
            table.getColumnModel().getColumn(img.getCurrPosCol()).setCellRenderer(new WindowGame.DisplayComponents());
            table.repaint();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    private boolean isColumnsSame(Image img, Image img2) {
        return img.getCurrPosCol() == img2.getCurrPosCol();
    }

    private boolean isColumnsSame(int column, Image img) {
        return column == img.getCurrPosCol();
    }

    private boolean isRowsSame(Image img, Image img2) {
        return img.getCurrPosRow() == img2.getCurrPosRow();
    }

    private boolean isRowsSame(int row, Image img) {
        return row == img.getCurrPosRow();
    }

    private void removeLastHeart() {
        heartsCols.removeLast();
    }

    private void fillColorCell(int i) {
        table.getColumnModel().getColumn(i).setCellRenderer(displayComp);
    }

    private void setBorderConfig() {
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
    }

    public JTable getTable() {
        return table;
    }

}

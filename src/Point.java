package src;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Point {
    private int countPoints = 0;
    public int collectedPoints = 0;
    private ImageIcon imgPoint = new ImageIcon("images/others/point.png");
    private Map<Integer, ArrayList<Integer>> storedPoints = new HashMap<>();

    public void addPoint(int row, int col) {
        storedPoints.putIfAbsent(row, new ArrayList<>());
        storedPoints.get(row).add(col);
        countPoints++;
    }

    public void removePoint(int tabRow, int tabCol) {
        storedPoints.get(tabRow).remove((Integer) tabCol);
        countPoints--;
    }

    public boolean isPointExistsIn(int tabRow, int tabCol) {
        ArrayList<Integer> colsArr;
        int row;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : storedPoints.entrySet()) {
            row = entry.getKey();
            colsArr = entry.getValue();
            for (Integer col : colsArr) {
                if (row == tabRow && col == tabCol)
                    return true;
            }
        }
        return false;
    }

    public int getCollectedPoints() {
        return collectedPoints;
    }

    public ImageIcon getImgPoint() {
        return imgPoint;
    }

    public int getStoredPointsSize() {
        return storedPoints.size();
    }

    public void addCollectedPoint() {
        collectedPoints++;
    }
}

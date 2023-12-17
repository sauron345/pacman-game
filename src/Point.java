package src;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Point {
    private int collectedPoints = 0;
    private int allPoints = 0;
    private ImageIcon imgPoint = new ImageIcon("images/others/point.png");
    private Map<Integer, ArrayList<Integer>> existingPoints = new HashMap<>();

    public void addPoint(int row, int col) {
        existingPoints.putIfAbsent(row, new ArrayList<>());
        existingPoints.get(row).add(col);
        allPoints++;
    }

    public void removePoint(int tabRow, int tabCol) {
        existingPoints.get(tabRow).remove((Integer) tabCol);
    }

    public boolean isPointExistsIn(int tabRow, int tabCol) {
        ArrayList<Integer> colsArr;
        int row;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : existingPoints.entrySet()) {
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

    public int getExistingPointsSize() {
        return existingPoints.size();
    }

    public void addCollectedPoint() {
        collectedPoints++;
    }

    public int getAllPoints() {
        return allPoints;
    }
}

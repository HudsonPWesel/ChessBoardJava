import java.awt.Color;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.List;
import javax.swing.*;
import java.awt.event.MouseListener;

import java.util.Optional;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoardManager extends JFrame implements MouseListener {
    // First icon is the A8 Rook (King Side Black Rook)

    private List<IconPanel> icons = new ArrayList<IconPanel>();
    private List<Cell> cells = new ArrayList<Cell>();

    private int xCellPos = 110;
    private int yCellPos = 110;

    private int cellGap = 100;
    private int cellSize = 100;

    // activeCEll points to last clicked cell that contained a piece
    private Cell activeCell;
    private Cell destinationCell;

    public BoardManager() {

        getContentPane().setBackground(Color.WHITE);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        addMouseListener(this);

        // makeFrameFullSize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
    }

    public String getImgSrc(int row, int col) {
        // Array of paths
        switch (row) {
            case 0:
                if (col == 0)
                    return "Images/br.png";
                else if (col == 1)
                    return "Images/bn.png";
                else if (col == 2)
                    return "Images/bb.png";
                else if (col == 3)
                    return "Images/bq.png";
                else if (col == 4)
                    return "Images/bk.png";
                else if (col == 5)
                    return "Images/bb.png";
                else if (col == 6)
                    return "Images/bn.png";
                else if (col == 7)
                    return "Images/br.png";

            case 1:
                return "Images/bp.png";
            case 6:
                return "Images/wp.png";
            case 7:
                if (col == 0)
                    return "Images/wr.png";
                else if (col == 1)
                    return "Images/wn.png";
                else if (col == 2)
                    return "Images/wb.png";
                else if (col == 3)
                    return "Images/wq.png";
                else if (col == 4)
                    return "Images/wk.png";
                else if (col == 5)
                    return "Images/wb.png";
                else if (col == 6)
                    return "Images/wn.png";
                else if (col == 7)
                    return "Images/wr.png";

        }
        return "NULL";

    }

    public double[] getFrameDimensions() {
        return new double[] { getSize().getWidth(), getBounds().getHeight() };
    }

    void drawRectangles(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // LOWER THESE VALUES TO FIT SMALLER SCREEN
        if (cells.size() < 1) {
            for (int col = 0; col < 8; col++) {
                for (int row = 0; row < 8; row++) {

                    String imgSrc = getImgSrc(col, row);

                    int xCellPos = (this.xCellPos + row * cellGap);
                    int yCellPos = (this.yCellPos + col * cellGap);

                    IconPanel icon = new IconPanel(g, imgSrc);

                    Cell cell = new Cell(xCellPos, yCellPos, this.cellSize, this.cellGap,
                            this.cellSize, this.cellSize, false, icon);

                    g2d.drawRect(cell.getXCellPos(), cell.getYCellPos(), cell.getCellSize(),
                            cell.getCellSize());

                    if (row % 2 == 0) {
                        if (col % 2 != 0)
                        // Fill Black
                        {
                            cell.setIsBlack(true);
                            g2d.fillRect(cell.getXCellPos(), cell.getYCellPos(), cell.getCellSize(),
                                    cell.getCellSize());
                        }

                    } else {
                        if (col % 2 == 0)
                        // Fill Black
                        {
                            cell.setIsBlack(true);
                            g2d.fillRect(cell.getXCellPos(), cell.getYCellPos(), cell.getCellSize(),
                                    cell.getCellSize());
                        }
                    }

                    icon.setIconPos(xCellPos, yCellPos);

                    icon.printImg(g2d);

                    cells.add(cell);
                    icons.add(icon);
                }

            }
        } else {

            cells.forEach((cell) -> {
                System.out.println("CELL X: " + cell.getXCellPos() + " Y: " + cell.getYCellPos());
                g2d.drawRect(cell.getXCellPos(), cell.getYCellPos(), cell.getCellSize(),
                        cell.getCellSize());

                if (cell.getIsBlack()) {
                    g2d.fillRect(cell.getXCellPos(), cell.getYCellPos(), cell.getCellSize(),
                            cell.getCellSize());
                }
                cell.getContainedIcon().printImg(g2d);
            });

        }
    }

    public boolean calcIsActiveCell(Cell cell, int cursorXPos, int cursorYPos) {

        boolean cursorIsInCellX = cursorXPos >= cell.getXCellPos() && cursorXPos <= cell.getXCellPos() + 110;

        boolean cursorIsInCellY = cursorYPos >= cell.getYCellPos() && cursorYPos <= cell.getYCellPos() + 110;
        if (cursorIsInCellX && cursorIsInCellY)
            return true;
        return false;
    }

    public void setActiveCell(int cursorXPos, int cursorYPos) {

        Optional<Cell> filteredCell = (cells.stream().filter(cell -> (calcIsActiveCell(cell, cursorXPos, cursorYPos)))
                .findFirst());

        // DOUBLE CLICK ELIMS OF ICONS
        try {
            if (!filteredCell.isEmpty() && activeCell == null &&
                    !filteredCell.get().getContainedIcon().getPieceName().equals("NULL")) {
                this.activeCell = filteredCell.get();

            } else if (!(filteredCell.isEmpty()) && this.activeCell != null) {
                this.destinationCell = filteredCell.get();
            }
        } catch (NullPointerException ignored) {}

    }

    public void calcClickedPiece(int cursorXPos, int cursorYPos) {

        // icon has access to name to identify piece
        // icon has access to x and y coordinates

        setActiveCell(cursorXPos, cursorYPos);

        if (activeCell != null && destinationCell != null) {
            // this.activeCell.getContainedIcon().setIconPos(destinationCell.getXCellPos(),
            // destinationCell.getYCellPos());

            // Set new contained icon at selected destination cell
            // DESTINATION CELL ICON POS

            if (activeCell != destinationCell) {
                this.destinationCell.setContainedIcon(this.activeCell.getContainedIcon());
                this.destinationCell.getContainedIcon().setIconPos(destinationCell.getXCellPos(),
                        destinationCell.getYCellPos());

                this.activeCell.setContainedIcon(new IconPanel());
                repaint();
            }
            this.activeCell = null;
            this.destinationCell = null;
            return;

        }

    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        calcClickedPiece(e.getX(), e.getY());

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BoardManager().setVisible(true);

            }
        });
    }

    public void paint(Graphics g) {

        super.paint(g);
        drawRectangles(g);
    }
}

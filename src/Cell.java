
public class Cell {
    private int xCellPos;
    private int yCellPos;
    private int cellGap;
    private int cellSize;
    private int cellXDimension;
    private int cellYDimension;
    private boolean isOccupied;
    private IconPanel containedIcon;
    private boolean isBlack;

    // cell.getContainedIcon().getImgSrc()
    public Cell(int xCellpos, int yCellpos, int cellSize, int cellGap, int cellXDimension, int cellYDimension,
            boolean isOccupied, IconPanel containedIcon) {
        this.xCellPos = xCellpos;
        this.yCellPos = yCellpos;
        this.cellSize = cellSize;
        this.cellXDimension = cellXDimension;
        this.cellYDimension = cellYDimension;
        this.cellGap = cellGap;
        this.isOccupied = isOccupied;
        this.isBlack = false;

        // BOTH MUST BE EQUAL
        this.cellSize = 100;
        this.cellGap = 100;

        this.containedIcon = containedIcon;
    }

    public void setIsBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean getIsBlack() {
        return this.isBlack;
    }

    public int getXCellPos() {
        return this.xCellPos;
    }

    public void setXCellPos(int xCellPos) {
        this.xCellPos = xCellPos;
    }

    public int getYCellPos() {
        return this.yCellPos;
    }

    public void setYCellPos(int yCellPos) {
        this.yCellPos = yCellPos;
    }

    public int getCellGap() {
        return this.cellGap;
    }

    public void setCellGap(int cellGap) {
        this.cellGap = cellGap;
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getCellXDimension() {
        return this.cellXDimension;
    }

    public void setCellXDimension(int cellXDimension) {
        this.cellXDimension = cellXDimension;
    }

    public int getCellYDimension() {
        return this.cellYDimension;
    }

    public void setCellYDimension(int cellYDimension) {
        this.cellYDimension = cellYDimension;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public void setIsOccupied() {
        if (!this.containedIcon.getName().equals("NULL"))
            this.isOccupied = true;
    }

    public IconPanel getContainedIcon() {
        return this.containedIcon;
    }

    public void setContainedIcon(IconPanel containedIcon) {
        this.containedIcon = containedIcon;
    }

}

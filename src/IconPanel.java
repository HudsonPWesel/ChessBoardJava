import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IconPanel extends JPanel {

    private BufferedImage image;
    private String imgSrc;
    private int xIconPos;
    private int yIconPos;
    private int imageHeight;
    private int imageWidth;
    private String pieceName;
    private boolean isActive;

    // Mouse
    public IconPanel(Graphics g, String imgSrc) {
        this.imgSrc = imgSrc;
        pasteImage(imgSrc);
        this.imageHeight = 100;
        this.imageWidth = 100;

    }

    public IconPanel() {
    }

    // THIS IS ID FOR EACH ICON
    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setPieceName(String imgSrc) {
        switch (imgSrc) {
            case "Images/bp.png":
                this.pieceName = "Black-Pawn";
                break;
            case "Images/br.png":
                this.pieceName = "Black-Rook";
                break;
            case "Images/bn.png":
                this.pieceName = "Black-Knight";
                break;
            case "Images/bb.png":
                this.pieceName = "Black-Bishop";
                break;
            case "Images/bq.png":
                this.pieceName = "Black-Queen";
                break;
            case "Images/bk.png":
                this.pieceName = "Black-Knight";
                break;

            case "Images/wp.png":
                this.pieceName = "White-Pawn";
                break;
            case "Images/wr.png":
                this.pieceName = "White-Rook";
                break;
            case "Images/wn.png":
                this.pieceName = "White-Knight";
                break;
            case "Images/wb.png":
                this.pieceName = "White-Bishop";
                break;
            case "Images/wq.png":
                this.pieceName = "White-Queen";
                break;
            case "Images/wk.png":
                this.pieceName = "White-King";
                break;
            default:
                this.pieceName = "NULL";
                break;
        }
        // this.pieceName = name;
    }

    public String getPieceName() {
        return this.pieceName;
    }

    private void pasteImage(String imgSrc) {
        if (imgSrc.equalsIgnoreCase("NULL"))
            return;
        this.imgSrc = imgSrc;

        try {
            image = ImageIO.read(new File(imgSrc));
            setPieceName(imgSrc);

        } catch (IOException e) {

            System.out.println(imgSrc);
        }
    }

    public void toggleIsActive() {
        this.isActive = !this.isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public int getIconXPos() {
        return this.xIconPos;
    }

    public int getIconYPos() {
        // Black Pistol Fire
        return this.yIconPos;
    }

    public void setIconPos(int xPos, int yPos) {
        this.xIconPos = xPos;
        this.yIconPos = yPos;
    }

    public IconPanel printImg(Graphics g) {

        // if(getFrameDimensions == #) --> this.imageWidth = #, this.imageHeight = #

        g.drawImage(image, xIconPos, yIconPos, this.imageWidth, this.imageHeight, this);

        return this;
    }

}
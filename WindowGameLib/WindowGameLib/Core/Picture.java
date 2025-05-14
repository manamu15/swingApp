package Core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
    private int x, y;
    private BufferedImage image = null;
    private BufferedImage[][] images;

    /**
     * コンストラクタ　ファイルが開けなければ強制終了する
     * @param path 画像ファイルのパス
     */
    public Picture(String path) {
        this.x = 0;
        this.y = 0;
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("画像ファイルを開けませんでした。");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * コンストラクタ　ファイルが開けなければ強制終了する
     * @param path 画像ファイルのパス
     * @param splitX 横の分割数
     * @param splitY 縦の分割数
     */
    public Picture(String path, int splitX, int splitY) {
        this(path);
        this.images = new BufferedImage[splitY][splitX];
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int splitWidth = width / splitX;
        int splitHeight = height / splitY;
        for (int i = 0; i < splitY; i++) {
            for (int j = 0; j < splitX; j++) {
                this.images[i][j] = this.image.getSubimage(j * splitWidth , i * splitHeight, splitWidth, splitHeight);
            }
        }
    }

    /**
     * 画像の描画
     * @param g Graphicsクラスのインスタンス
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, WindowPanel.getInstance());
    }

    /**
     * 画像の描画
     * @param g Graphicsクラスのインスタンス
     * @param indexX xの添え字
     * @param indexY yの添え字
     */
    public void draw(Graphics g, int indexX, int indexY) {
        int halfWidth = this.images[indexY][indexX].getWidth() / 2;
        int halfHeight = this.images[indexY][indexX].getHeight() / 2;
        g.drawImage(this.images[indexY][indexX], x - halfWidth, y - halfHeight, WindowPanel.getInstance());
    }

    /**
     * 座標の設定
     * @param x X座標
     * @param y Y座標
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X座標の取得
     * @return X座標
     */
    public int getX() {
        return this.x;
    }

    /**
     * Y座標の取得
     * @return Y座標
     */
    public int getY() {
        return this.y;
    }
}

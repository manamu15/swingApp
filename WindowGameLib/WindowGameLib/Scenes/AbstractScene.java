package Scenes;

import java.awt.Graphics;

public abstract class AbstractScene {
    /**
     * 画面の更新処理
     */
    public abstract void update();
    
    /**
     * 画面の描画処理
     * @param g Graphicsクラスのインスタンス
     */
    public abstract void draw(Graphics g);

    /**
     * 画面遷移の処理
     * @return 次のシーンを返す
     */
    public abstract AbstractScene changeScene();
}

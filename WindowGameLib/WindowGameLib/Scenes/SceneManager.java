package Scenes;

import java.lang.Override;
import java.awt.Graphics;

public class SceneManager extends AbstractScene {
    // AbstractSceneのインスタンス
    private AbstractScene scene;

    /**
     * コンストラクタ
     * @param scene 最初の画面
     */
    public SceneManager(AbstractScene scene) {
        this.scene = scene;
    }

    @Override
    public void update() {
        this.scene.update();
    }

    @Override
    public void draw(Graphics g) {
        this.scene.draw(g);
    }

    @Override
    public AbstractScene changeScene() {
        AbstractScene nextScene;
        nextScene = this.scene.changeScene();

        if (this.scene != nextScene)
        {
            this.scene = nextScene;
        }
        return this.scene;
    }
}

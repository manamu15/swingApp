package Core;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import Scenes.SceneManager;

/**
 * クラス：WindowPanel
 * ウィンドウの描画管理クラス
 */
public class WindowPanel extends JPanel {
    // Windowクラスのシングルトン
    private static WindowPanel instance = null;

    // 描画エリアのサイズ
    private static int width = 640;
    private static int height = 480;

    // SceneManagerクラスのインスタンス
    private SceneManager sceneManager;

    /**
     * コンストラクタ
     * @param width         横幅
     * @param height        高さ
     * @param sceneManager  シーンマネージャクラスのインスタンス
     */
    public WindowPanel(int width, int height, SceneManager sceneManager) {
        // instanceの初期化
        if (WindowPanel.instance == null) {
            WindowPanel.instance = this;
        } else {
            System.out.println("WindowPanelクラスを複数インスタンス作成しようとしています");
            System.exit(1);
        }
        // 描画エリアの初期化
        WindowPanel.width = width;
        WindowPanel.height = height;
        // シーンマネージャークラスのインスタンス
        this.sceneManager = sceneManager;
    }

    /**
     * 画面に描画するメソッド
     * @param g     Graphicsのインスタンス
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.sceneManager.update();
        // 画面のクリア
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, width, height);
        g.fillRect(0, 0, width, height);
        this.sceneManager.draw(g);

        this.sceneManager.changeScene();
    }

    /**
     * 描画エリアの横幅を返す
     * @return 描画エリアの横幅
     */
    public static int getPanelWidth() {
        return WindowPanel.width;
    }

    /**
     * 描画エリアの高さを返す
     * @return 描画エリアの高さ
     */
    public static int getPanelHeight() {
        return WindowPanel.height;
    }

    /**
     * WindowPanelクラスのインスタンスを取得
     * @return WindowPanelクラスのインスタンス
     */
    public static WindowPanel getInstance() {
        return WindowPanel.instance;
    }

}

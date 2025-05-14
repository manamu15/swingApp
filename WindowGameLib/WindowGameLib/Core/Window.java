package Core;
import javax.swing.*;

import Scenes.SampleScene;
import Scenes.SceneManager;

import java.awt.Dimension;

/**
 * クラス：Window
 * Windowの制御クラス
 */
public class Window extends JFrame{
    // Windowクラスのシングルトン
    private static Window instance = null;

    // WindowPanelクラスのインスタンス
    private WindowPanel panel;

    // SceneManagerクラスのインスタンス
    private SceneManager sceneManager;

    /**
     * コンストラクタ
     * @param width     ウィンドウの横幅
     * @param height    ウィンドウの高さ
     * @param title     ウィンドウのタイトル
     */
    public Window(int width, int height, String title) {
        if (Window.instance == null) {
            Window.instance = this;
        } else {
            System.out.println("Windowクラスを複数インスタンス作成しようとしています");
            System.exit(1);
        }
        // ウィンドウタイトルの指定
        this.setTitle(title);
        // ウィンドウサイズの指定
        this.getContentPane().setPreferredSize(new Dimension(width, height));
        // ウィンドウを中央に表示
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // SceneManagerクラスのインスタンス作成
        this.sceneManager = new SceneManager(new SampleScene());
        // WindowPanelクラスのインスタンス作成
        this.panel = new WindowPanel(width, height, sceneManager);
        this.add(panel);
        this.addKeyListener(new Keyboard());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * コンストラクタ
     * ウィンドウタイトルはデフォルトの"WindowTitle"に指定
     * @param width     ウィンドウの横幅
     * @param height    ウィンドウの高さ
     */
    public Window(int width, int height) {
        this(width, height, "WindowTitle");
    }

    /**
     * コンストラクタ
     * ウィンドウタイトルはデフォルトの"WindowTitle"に指定
     * ウィンドウのサイズは640x480
     */
    public Window() {
        this(640, 480, "WindowTitle");
    }

    /**
     * Windowクラスのインスタンスを取得
    */
    public static Window getInstance() {
        return Window.instance;
    }
}
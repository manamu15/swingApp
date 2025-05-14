import java.lang.Runnable;
import java.lang.Thread;

import Core.Keyboard;
import Core.Window;
import Core.WindowPanel;

/**
 * クラス：Main
 * メインクラス
 */
public class Main implements Runnable {
    // fpsの指定
    final double FRAME_RATE = 60.0;
    // 1秒のナノ単位表記
    final long NANO_SECOND = 1_000_000_000L;
    // 1フレームの秒数
    final long DELTA_NANO_SECOND = NANO_SECOND / (long)FRAME_RATE;
    // Windowクラスのインスタンス
    Window window = null;
    // WindowPanelクラスのインスタンス
    WindowPanel panel = null;
    // 実際のfps
    double frameRate = 0.0;

    /**
     * メインメソッド
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * コンストラクタ
     */
    Main() {
        // ウィンドウの作成
        this.window = new Window(640, 480, "WindowTitle");
        // ウィンドウの描画クラスの取得
        this.panel = WindowPanel.getInstance();

        // メインループの開始
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * メインループメソッド
     */
    @Override
    public void run() {
        long startTime = System.nanoTime();
        // メインループ
        while (true) {
            long nowTime = System.nanoTime();
            // fps固定（60fps）
            if ((nowTime - startTime) >= this.DELTA_NANO_SECOND) {
                // fpsの取得
                long deltaTime = nowTime - startTime;
                this.frameRate = (double)this.NANO_SECOND / deltaTime;

                // キー入力の更新
                Keyboard.Update();
                
                // 画面の再描画
                this.panel.repaint();

                startTime = System.nanoTime();
            }
        }
    }
}

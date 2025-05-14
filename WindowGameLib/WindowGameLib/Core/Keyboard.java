package Core;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.awt.event.KeyEvent;

/**
 * クラス：Keyboard
 * キー入力の管理をするクラス
 */
public class Keyboard implements KeyListener {
    // キー入力の情報
    private static HashMap<Integer, Integer> keyState = null;

    /**
     * コンストラクタ
     */
    public Keyboard() {
        Keyboard.keyState = new HashMap<>();
    }

    /**
     * キー入力している間のカウントアップ処理
     */
    public static void Update() {
        for (int key : Keyboard.keyState.keySet()) {
            int value = Keyboard.keyState.get(key);
            if (value > 0) {
                Keyboard.keyState.replace(key, value + 1);
            }
        }
    }

    /**
     * キー入力を開始してからのフレーム数
     * @param keycode キーコード
     * @return 入力してからのフレーム数
     */
    public static int GetInputFrame(int keycode) {
        if (Keyboard.keyState.containsKey(keycode)) {
            return Keyboard.keyState.get(keycode);
        } else {
            return 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { /* nothing */ }

    // キー押した時
    @Override
    public void keyPressed(KeyEvent e) {
        if (Keyboard.keyState.containsKey(e.getKeyCode())) {
            if (Keyboard.keyState.get(e.getKeyCode()) < 1) {
                Keyboard.keyState.replace(e.getKeyCode(), 1);
            }
        } else {
            Keyboard.keyState.put(e.getKeyCode(), 1);
        }
    }

    // キーを離した時
    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.keyState.replace(e.getKeyCode(), 0);
    }
}

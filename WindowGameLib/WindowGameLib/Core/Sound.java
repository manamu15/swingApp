package Core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    Clip clip = null;
    int startFrame;

    public Sound(String path) {
        //指定されたURLのオーディオ入力ストリームを取得
		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path))) {
			
			//ファイルの形式取得
			AudioFormat af = ais.getFormat();
			
			//単一のオーディオ形式を含む指定した情報からデータラインの情報オブジェクトを構築
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);
			
			//指定された Line.Info オブジェクトの記述に一致するラインを取得
			this.clip = (Clip)AudioSystem.getLine(dataLine);
			
			//再生準備完了
			this.clip.open(ais);

            // 再生位置のリセット
            startFrame = 0;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    }

    /**
     * 音量の設定
     * @param volume 0.0～100.0の間
     */
    public void setVolume(double volume) {
        FloatControl ctrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        ctrl.setValue((float)Math.log10(volume / 100.0)*20);
    }

    /**
     * 再生
     */
    public void play() {
        this.clip.setFramePosition(startFrame);
        this.clip.start();
    }

    /**
     * 指定された位置から再生
     * @param start フレーム
     */
    public void play(int start) {
        this.clip.setFramePosition(start);
        this.clip.start();
    }

    /**
     * ループ再生
     */
    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * 範囲内をループ再生
     * @param start スタート
     * @param end 終わり
     */
    public void loop(int start, int end) {
        this.clip.setLoopPoints(start, end);
        this.loop();
    }

    /**
     * 中断
     */
    public void pause() {
        startFrame = this.clip.getFramePosition();
        this.clip.stop();
    }

    /**
     * 停止
     */
    public void stop() {
        startFrame = 0;
        this.clip.stop();
    }
}

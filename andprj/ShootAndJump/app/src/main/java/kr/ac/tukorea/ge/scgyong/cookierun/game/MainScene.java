package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.content.Context;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.Background;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.Camera;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.GameScore;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.MonsterGenerator;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.PauseButton;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.Player;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects.PlayerHP;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    public static Player player;
    public PlayerHP playerHP;
    public MonsterGenerator generator;
    public static Camera camera;
    public Background background;
    public static GameScore gameScore;

    public PauseButton pauseButton;

    public enum Layer {
        LAYER1, LAYER2, LAYER3;
        public static final int COUNT = values().length;
    }

    public MainScene() {
        initLayers(Layer.COUNT);

        camera = new Camera();
        background = new Background(R.mipmap.background);
        generator = new MonsterGenerator();
        player = new Player(R.mipmap.commando_left);
        playerHP = new PlayerHP();
        gameScore = new GameScore();
        pauseButton = new PauseButton(R.mipmap.button_pause);

        add(Layer.LAYER1, camera);
        add(Layer.LAYER1, background);
        add(Layer.LAYER1, generator);
        add(Layer.LAYER2, (IGameObject) player);
        add(Layer.LAYER3, gameScore);
        add(Layer.LAYER3, playerHP);
        add(Layer.LAYER3, pauseButton);

        Sound.playMusic(R.raw.game_bgm);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float[] in = Metrics.fromScreen(event.getX(), event.getY());
            float x = in[0];
            float y = in[1];
            float screenWidth = Metrics.width;

            // 일시정지 버튼을 누를 경우 일시정지 씬으로 전환
            if(Metrics.cvtX(Metrics.ASP(-0.9f)) - Metrics.unit * 0.1f < x &&
                   x < Metrics.cvtX(Metrics.ASP(-0.9f)) + Metrics.unit * 0.1f &&
                            Metrics.cvtY(0.8f) - Metrics.unit * 0.1f < y &&
                    y <  Metrics.cvtY(0.8f) + Metrics.unit * 0.1f){
                PauseScene pauseScene;
                pauseScene = new PauseScene();
                pauseScene.push();

                return true;
            }

            if(x > 0.0 || x < screenWidth) {
                if (x < screenWidth * 0.5f) {
                    onLeftTouch();
                } else {
                    onRightTouch();
                }
            }

            return true;
        }
        return false;
    }

    public boolean onBackPressed() {
        Sound.stopMusic();
        return false;
    }

    public void onLeftTouch() {
        player.inputEvent(0);
    }

    public void onRightTouch() {
        player.inputEvent(1);
    }
}

package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Background;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Camera;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.GameScore;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.MonsterGenerator;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Player;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.PlayerHP;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    public static Player player;
    public PlayerHP playerHP;
    public MonsterGenerator generator;
    public static Camera camera;
    public Background background;
    public static GameScore gameScore;

    public enum Layer {
        LAYER1, LAYER2, LAYER3, LAYER4, LAYER5;
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

        add(Layer.LAYER1, camera);
        add(Layer.LAYER1, background);
        add(Layer.LAYER1, generator);
        add(Layer.LAYER2, (IGameObject) player);
        add(Layer.LAYER3, gameScore);
        add(Layer.LAYER3, playerHP);

        Sound.playMusic(R.raw.game_bgm);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float screenWidth = Metrics.width;

            if (x < screenWidth * 0.5f) {
                onLeftTouch();
            } else {
                onRightTouch();
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

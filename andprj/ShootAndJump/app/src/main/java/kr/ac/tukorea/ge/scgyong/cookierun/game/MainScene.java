package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Background;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Camera;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.MonsterGenerator;
import kr.ac.tukorea.ge.scgyong.cookierun.game.objects.Player;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    public static Player player;
    public MonsterGenerator generator;
    public static Camera camera;

    public Background background;

    public enum Layer {
        LAYER1, LAYER2, LAYER3, LAYER4, LAYER5;
        public static final int COUNT = values().length;
    }

    public MainScene() {
        initLayers(Layer.COUNT);

        background = new Background(R.mipmap.background);
        player = new Player(R.mipmap.commando_left);
        generator = new MonsterGenerator();
        camera = new Camera();

        add(Layer.LAYER1, background);
        add(Layer.LAYER1, camera);
        add(Layer.LAYER1, generator);
        add(Layer.LAYER2, player);
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

    public void onLeftTouch() {
        player.inputEvent(0);
    }

    public void onRightTouch() {
        player.inputEvent(1);
    }
}

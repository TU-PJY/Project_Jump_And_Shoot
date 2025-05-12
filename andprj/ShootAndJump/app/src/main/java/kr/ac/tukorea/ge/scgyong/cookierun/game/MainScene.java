package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    private final Player player;

    public enum Layer {
        LAYER1, LAYER2, LAYER3, LAYER4, LAYER5;
        public static final int COUNT = values().length;
    }

    public MainScene() {
        initLayers(Layer.COUNT);

        // 1번 레이어에 플레이어 추가
        player = new Player(R.mipmap.commando);
        add(Layer.LAYER1, player);
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

package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.DeadPlayer;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.GameoverBackground;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.GameoverResult;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameoverScene extends Scene {
    public DeadPlayer deadPlayer;
    public GameoverBackground gameoverBackground;
    public GameoverResult gameoverResult;

    public enum Layer {
        LAYER1, LAYER2;
        public static final int COUNT = values().length;
    }

    public GameoverScene() {
        initLayers(Layer.COUNT);

        gameoverBackground = new GameoverBackground(R.mipmap.hp_indicator_back);
        gameoverResult = new GameoverResult();
        deadPlayer = new DeadPlayer(R.mipmap.commando_dead);

        add(Layer.LAYER1, gameoverBackground);
        add(Layer.LAYER1, deadPlayer);
        add(Layer.LAYER2, gameoverResult);
    }

    public boolean onBackPressed() {
        Sound.stopMusic();
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gameoverResult.touchInput();
            return true;
        }
        return false;
    }
};

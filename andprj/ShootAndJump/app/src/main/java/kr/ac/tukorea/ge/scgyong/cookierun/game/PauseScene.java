package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.DeadPlayer;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.GameoverBackground;
import kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects.GameoverResult;
import kr.ac.tukorea.ge.scgyong.cookierun.game.PauseSceneObjects.PauseBackground;
import kr.ac.tukorea.ge.scgyong.cookierun.game.PauseSceneObjects.PauseText;
import kr.ac.tukorea.ge.scgyong.cookierun.game.PauseSceneObjects.ResumeButton;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseScene extends Scene {

    public PauseBackground pauseBackground;
    public ResumeButton resumeButton;
    public PauseText pauseText;

    public enum Layer {
        LAYER1, LAYER2;
        public static final int COUNT = values().length;
    }

    public PauseScene() {
        initLayers(kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverScene.Layer.COUNT);

        pauseBackground = new PauseBackground(R.mipmap.background_pause);
        resumeButton = new ResumeButton(R.mipmap.button_play);
        pauseText = new PauseText();
        add(Layer.LAYER1, pauseBackground);
        add(Layer.LAYER2, resumeButton);
        add(Layer.LAYER2, pauseText);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float[] in = Metrics.fromScreen(event.getX(), event.getY());
            float x = in[0];
            float y = in[1];

            // 계속하기 버튼을 누를 경우 플레이 씬으로 전환
            if(Metrics.cvtX(Metrics.ASP(-0.9f)) - Metrics.unit * 0.1f < x &&
                    x < Metrics.cvtX(Metrics.ASP(-0.9f)) + Metrics.unit * 0.1f &&
                    Metrics.cvtY(0.8f) - Metrics.unit * 0.1f < y &&
                    y <  Metrics.cvtY(0.8f) + Metrics.unit * 0.1f){

                pop();

                return true;
            }

            return true;
        }
        return false;
    }
}

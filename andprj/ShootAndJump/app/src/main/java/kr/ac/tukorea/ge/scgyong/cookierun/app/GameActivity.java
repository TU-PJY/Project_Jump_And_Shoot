package kr.ac.tukorea.ge.scgyong.cookierun.app;

import android.os.Bundle;
import android.util.Log;

import kr.ac.tukorea.ge.scgyong.cookierun.BuildConfig;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameActivity extends kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        //Metrics.setGameSize(1600, 900);
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}
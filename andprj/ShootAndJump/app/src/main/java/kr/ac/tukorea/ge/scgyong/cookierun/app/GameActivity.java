package kr.ac.tukorea.ge.scgyong.cookierun.app;

import android.os.Bundle;
import android.util.Log;

import kr.ac.tukorea.ge.scgyong.cookierun.BuildConfig;
import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameActivity extends kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        Metrics.setGameSize(1600, 900);
        super.onCreate(savedInstanceState);

        // 사운드 미리 로드
        Sound.loadSound(R.raw.m1);
        Sound.loadSound(R.raw.hurt);
        Sound.loadSound(R.raw.monster_sound1);
        Sound.loadSound(R.raw.hit);
        Sound.loadSound(R.raw.next_round);
        Sound.loadSound(R.raw.player_dead);

        new MainScene().push();
    }
}
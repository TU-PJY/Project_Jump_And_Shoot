package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MonsterGenerator implements IGameObject {
    private float currentTime;
    private float spwanTime = 2.0f;
    float unit = Metrics.height * 0.5f;

    public MonsterGenerator() {

    }

    @Override
    //  currentTime이 spawnTime에 도달할 때마다 몬스터를 1마리씩 스폰한다.
    public void update() {
        currentTime += GameView.frameTime;
        if(currentTime >= spwanTime) {
            currentTime -= spwanTime;
            Monster monster;
            monster = new Monster(R.mipmap.monster_left, 1.0f);
            Scene scene = Scene.top();
            scene.add(MainScene.Layer.LAYER1, monster);
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}

package kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MonsterGenerator implements IGameObject {
    private float currentTime;
    public float spawnTime = 2.0f;
    public float bigProbability = 0.2f;
    float unit = Metrics.height * 0.5f;

    Random rand = new Random();

    public MonsterGenerator() {

    }

    @Override
    //  currentTime이 spawnTime에 도달할 때마다 몬스터를 1마리씩 스폰한다.
    public void update() {
        currentTime += GameView.frameTime;
        if(currentTime >= spawnTime) {
            currentTime -= spawnTime;
            Monster monster;
            BigMonster bigMonster;

            int randomSpawnDirection = rand.nextBoolean() ? 1 : -1;
            // 20퍼센트 확률로 큰 몬스터 스폰
            boolean spawnBig = Math.random() < bigProbability;

            Scene scene = Scene.top();

            // 좌우 스폰 위치, 종류 랜덤
            if(randomSpawnDirection == 1) {
                if(!spawnBig) {
                    monster = new Monster(R.mipmap.monster_left, Metrics.ASP(1.5f));
                    scene.add(MainScene.Layer.LAYER1, monster);
                }
                else {
                    bigMonster = new BigMonster(R.mipmap.monster_big_left, Metrics.ASP(1.5f));
                    scene.add(MainScene.Layer.LAYER1, bigMonster);
                }
            }
            else {
                if(!spawnBig) {
                    monster = new Monster(R.mipmap.monster_left, Metrics.ASP(-1.5f));
                    scene.add(MainScene.Layer.LAYER1, monster);
                }
                else {
                    bigMonster = new BigMonster(R.mipmap.monster_big_left, Metrics.ASP(-1.5f));
                    scene.add(MainScene.Layer.LAYER1, bigMonster);
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}

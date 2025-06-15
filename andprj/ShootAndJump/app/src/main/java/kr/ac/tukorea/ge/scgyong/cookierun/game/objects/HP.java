package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class HP implements IGameObject {
    Monster targetMonster;
    BigMonster targetBigMonster;

    private float positionX;
    private float positionY;
    Sprite front;
    Sprite back;

    private boolean targetBig;


    boolean drawEnabled;

    // 특정 몬스터만 추적한다.
    public HP(int mipmapId, Monster monsterInstance) {
        front = new Sprite(mipmapId);
        back = new Sprite(R.mipmap.hp_indicator_back);
        targetMonster = monsterInstance;
    }

    public HP(int mipmapId, BigMonster monsterInstance) {
        front = new Sprite(mipmapId);
        back = new Sprite(R.mipmap.hp_indicator_back);
        targetBigMonster = monsterInstance;
        targetBig = true;
    }

    @Override
    public void update() {
        // 추적 대상 몬스터가 더 이상 존재하지 않으면 스스로 삭제한다.
        if(!targetBig) {
            if (!targetMonster.dead) {
                positionX = targetMonster.positionX;
                positionY = targetMonster.positionY - Metrics.unit * 0.25f;
            }
            else {
                Scene scene = Scene.top();
                scene.remove(MainScene.Layer.LAYER1, this);
            }
        }

        else {
            if (!targetBigMonster.dead) {
                positionX = targetBigMonster.positionX;
                positionY = targetBigMonster.positionY - Metrics.unit * 0.25f;
            }
            else {
                Scene scene = Scene.top();
                scene.remove(MainScene.Layer.LAYER1, this);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(drawEnabled) {
            if(!targetBig) {
                if (!targetMonster.dead) {
                    for (int i = 0; i < targetMonster.HP; i++) {
                        float renderPositionX = positionX - Metrics.unit * 0.04f * (float) (targetMonster.HP - 1);
                        renderPositionX += i * Metrics.unit * 0.08f;

                        back.setPosition(
                                renderPositionX + MainScene.camera.shakeResultX,
                                positionY + MainScene.camera.shakeResultY,
                                Metrics.unit * 0.07f, Metrics.unit * 0.07f
                        );

                        front.setPosition(
                                renderPositionX + MainScene.camera.shakeResultX,
                                positionY + MainScene.camera.shakeResultY,
                                Metrics.unit * 0.05f, Metrics.unit * 0.05f
                        );

                        back.draw(canvas);
                        front.draw(canvas);
                    }
                }
            }

            else {
                if (!targetBigMonster.dead) {
                    for (int i = 0; i < targetBigMonster.HP; i++) {
                        float renderPositionX = positionX - Metrics.unit * 0.04f * (float) (targetBigMonster.HP - 1);
                        renderPositionX += i * Metrics.unit * 0.08f;

                        back.setPosition(
                                renderPositionX + MainScene.camera.shakeResultX,
                                positionY + MainScene.camera.shakeResultY,
                                Metrics.unit * 0.07f, Metrics.unit * 0.07f
                        );

                        front.setPosition(
                                renderPositionX + MainScene.camera.shakeResultX,
                                positionY + MainScene.camera.shakeResultY,
                                Metrics.unit * 0.05f, Metrics.unit * 0.05f
                        );

                        back.draw(canvas);
                        front.draw(canvas);
                    }
                }
            }
        }
        drawEnabled = true;
    }
}

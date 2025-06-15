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

    private float positionX;
    private float positionY;
    Sprite sprite;

    boolean drawEnabled;

    // 특정 몬스터만 추적한다.
    public HP(int mipmapId, Monster monsterInstance) {
        sprite = new Sprite(mipmapId);
        targetMonster = monsterInstance;
    }

    @Override
    public void update() {
        // 추적 대상 몬스터가 더 이상 존재하지 않으면 스스로 삭제한다.
        if(!targetMonster.dead) {
            positionX = targetMonster.positionX;
            positionY = targetMonster.positionY - Metrics.unit * 0.25f;
        }

        else {
            Scene scene = Scene.top();
            scene.remove(MainScene.Layer.LAYER1, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(drawEnabled) {
            if (!targetMonster.dead) {
                for (int i = 0; i < targetMonster.HP; i++) {
                    float renderPositionX = positionX - Metrics.unit * 0.025f + Metrics.unit * 0.07f * i;
                    renderPositionX -= Metrics.unit * 0.025f * (float) targetMonster.HP / 2.0f;

                    sprite.setPosition(
                            renderPositionX + MainScene.camera.shakeResultX,
                            positionY + MainScene.camera.shakeResultY,
                            Metrics.unit * 0.05f, Metrics.unit * 0.05f
                    );
                    sprite.draw(canvas);
                }
            }
        }
        drawEnabled = true;
    }
}

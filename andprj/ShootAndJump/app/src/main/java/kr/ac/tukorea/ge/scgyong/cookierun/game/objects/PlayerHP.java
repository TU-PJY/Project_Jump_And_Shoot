package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PlayerHP implements IGameObject {
    private Sprite front;
    private Sprite back;
    private int HP;
    private boolean drawEnable;
    public PlayerHP() {
        back = new Sprite(R.mipmap.hp_indicator_back);
        front = new Sprite(R.mipmap.hp_indicator);
    }

    @Override
    public void update() {
        HP = MainScene.player.HP;
    }

    @Override
    public void draw(Canvas canvas) {
        if(drawEnable) {
            for (int i = 0; i < HP; i++) {
                back.setPosition(
                        Metrics.cvtX(Metrics.ASP(-0.9f + i * 0.07f)) + MainScene.camera.shakeResultX,
                        Metrics.cvtY(-0.9f) + MainScene.camera.shakeResultY,
                        Metrics.unit * 0.1f,
                        Metrics.unit * 0.1f
                        );

                front.setPosition(
                        Metrics.cvtX(Metrics.ASP(-0.9f + i * 0.07f)) + MainScene.camera.shakeResultX,
                        Metrics.cvtY(-0.9f) + MainScene.camera.shakeResultY,
                        Metrics.unit * 0.08f,
                        Metrics.unit * 0.08f
                        );

                back.draw(canvas);
                front.draw(canvas);
            }
        }
        drawEnable = true;
    }
}

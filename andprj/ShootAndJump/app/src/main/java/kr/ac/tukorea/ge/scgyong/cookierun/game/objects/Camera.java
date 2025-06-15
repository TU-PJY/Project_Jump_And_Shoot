package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Camera implements IGameObject {
    private float shakeValue;
    public float shakeResultX;
    public float shakeResultY;

    public float updateTime;

    float unit = Metrics.height * 0.5f;
    Random rand = new Random();

    @Override
    public void update() {
        shakeValue = shakeValue + (0.0f - shakeValue) * GameView.frameTime * 10.0f;
        if(shakeValue < 0.0f) {
            shakeValue = 0.0f;
            updateTime = 0.01f;
        }

        else {
            updateTime += GameView.frameTime;

            if(updateTime >= 0.01f) {
                int randomFactor = rand.nextBoolean() ? 1 : -1;
                shakeResultX = unit * shakeValue * (float) randomFactor;
                int randomFactor2 = rand.nextBoolean() ? 1 : -1;
                shakeResultY = unit * shakeValue * (float) randomFactor2;
                updateTime -= 0.01f;
            }
        }
    }

    public void AddShake(float Value) {
        shakeValue += Value;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}

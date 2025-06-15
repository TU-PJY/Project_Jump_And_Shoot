package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameScore implements IGameObject {

    private int score;
    private Paint paint;

    public GameScore() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(Metrics.unit * 0.3f);
        paint.setFakeBoldText(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void addScore() {
        score ++;
    }

    @Override
    public void update() {

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(String.format("%d", score),
                Metrics.cvtX(0.0f) + MainScene.camera.shakeResultX,
                Metrics.cvtY(-0.7f) + MainScene.camera.shakeResultY,
                paint);
    }
}

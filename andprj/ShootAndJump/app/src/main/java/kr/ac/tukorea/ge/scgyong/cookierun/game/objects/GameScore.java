package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameScore implements IGameObject {

    private int startScore;
    private int score;
    private int round = 1;
    private Paint paint;

    public GameScore() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void addScore() {
        score++;
    }


    @Override
    public void update() {
        // 10점마다 난이도 상승
        // 플레이어 체력 회복
        if(score - startScore >= 10) {
            Sound.playEffect(R.raw.next_round);
            round++;
            startScore = score;
            MainScene.player.HP = 5;
        }
    }

    public void drawScoreText(Canvas canvas) {
        canvas.drawText(String.format("%d", score),
                Metrics.cvtX(0.0f) + MainScene.camera.shakeResultX,
                Metrics.cvtY(-0.7f) + MainScene.camera.shakeResultY,
                paint);
    }

    public void drawRoundText(Canvas canvas) {
        canvas.drawText(String.format("Round %d", round),
                Metrics.cvtX(Metrics.ASP(0.9f)) + MainScene.camera.shakeResultX,
                Metrics.cvtY(-0.8f) + MainScene.camera.shakeResultY,
                paint);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void draw(Canvas canvas) {
        paint.setTextSize(Metrics.unit * 0.3f);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setFakeBoldText(true);
        drawScoreText(canvas);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        drawScoreText(canvas);


        paint.setTextSize(Metrics.unit * 0.2f);
        paint.setTextAlign(Paint.Align.RIGHT);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setFakeBoldText(true);
        drawRoundText(canvas);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        drawRoundText(canvas);
    }
}

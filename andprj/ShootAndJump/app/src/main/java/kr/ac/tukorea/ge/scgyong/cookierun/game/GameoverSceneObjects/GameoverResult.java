package kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class GameoverResult implements IGameObject {

    private int resultScore;
    private int prevScore;
    private boolean isHighScore;

    private Paint paint;

    // Scene에 저장해둔 점수 로드
    public GameoverResult() {
        Scene scene = Scene.top();
        resultScore = scene.globalScore;
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        Context context = GameView.view.getContext();

        // 점수 갱신 시 새로 저장
        SharedPreferences prefs = context.getSharedPreferences("HighScore", Context.MODE_PRIVATE);
        prevScore = prefs.getInt("Score", 0);

        if(prevScore < resultScore) {
            isHighScore = true;
            prefs = context.getSharedPreferences("HighScore", Context.MODE_PRIVATE);
            prefs.edit().putInt("Score", resultScore).apply();
        }
    }

    // 터치가 입력되면 다시 플레이 씬으로 돌아간다.
    public void touchInput() {
        Sound.stopMusic();
        MainScene mainScene = new MainScene();
        mainScene.change();
    }

    public void drawScoreText(Canvas canvas) {
        paint.setTextSize(Metrics.unit * 0.3f);
        canvas.drawText(String.format("GameOver", resultScore),
                Metrics.cvtX(0.0f),
                Metrics.cvtY(-0.7f),
                paint);

        paint.setTextSize(Metrics.unit * 0.4f);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(8f);
        paint.setFakeBoldText(true);
        canvas.drawText(String.format("%d", resultScore),
                Metrics.cvtX(0.0f),
                Metrics.cvtY(-0.3f),
                paint);

        if(!isHighScore) {
            paint.setTextSize(Metrics.unit * 0.1f);
            paint.setStyle(Paint.Style.FILL);
            paint.setFakeBoldText(false);
            canvas.drawText(String.format("High Score: %d", prevScore),
                    Metrics.cvtX(0.0f),
                    Metrics.cvtY(-0.1f),
                    paint);
        }

        else {
            paint.setTextSize(Metrics.unit * 0.2f);
            paint.setStyle(Paint.Style.FILL);
            paint.setFakeBoldText(false);
            canvas.drawText(String.format("High Score!"),
                    Metrics.cvtX(0.0f),
                    Metrics.cvtY(0.0f),
                    paint);
        }

        paint.setTextSize(Metrics.unit * 0.1f);
        canvas.drawText(String.format("Tap to continue", resultScore),
                Metrics.cvtX(0.0f),
                Metrics.cvtY(0.9f),
                paint);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        drawScoreText(canvas);
    }
}

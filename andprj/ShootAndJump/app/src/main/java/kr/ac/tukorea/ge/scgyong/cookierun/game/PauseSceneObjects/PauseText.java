package kr.ac.tukorea.ge.scgyong.cookierun.game.PauseSceneObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseText implements IGameObject {
    private Paint paint;

    @Override
    public void update() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Metrics.unit * 0.6f);
    }

    public void drawText(Canvas canvas) {
        canvas.drawText(String.format("Paused"),
                Metrics.cvtX(0.0f),
                Metrics.cvtY(0.0f),
                paint);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10f);
        paint.setFakeBoldText(true);
        drawText(canvas);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        drawText(canvas);
    }
}

package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Trace extends Sprite implements IBoxCollidable  {
    private float positionX;
    private float positionY;

    private float screenWidth = Metrics.width;
    private float screenHeight = Metrics.height;
    private float unit = screenHeight * 0.5f;
    private int moveDirection;


    public Trace(int mipmapId, float createPositionX, int direction) {
        super(mipmapId);
        Bitmap bitmap = BitmapPool.get(mipmapId);
        positionX = createPositionX;
        positionY = screenHeight * 0.8f;
        moveDirection = direction;

        setPosition(positionX + MainScene.camera.shakeResultX,
                positionY + MainScene.camera.shakeResultY,
                unit * 0.1f, unit * 0.1f);
    }

    @Override
    public void update() {
        if(moveDirection == 0)
            positionX -= GameView.frameTime * unit * 5.0f;
        else
            positionX += GameView.frameTime * unit * 5.0f;

        if(positionX > screenWidth + unit * 0.5 || positionX < - unit * 0.5) {
            Scene scene = Scene.top();
            scene.remove(MainScene.Layer.LAYER3, this);
        }

        setPosition(positionX + MainScene.camera.shakeResultX,
                positionY + MainScene.camera.shakeResultY,
                unit * 0.1f, unit * 0.1f);
    }

    @Override
    public RectF getCollisionRect() {
        return null;
    }
}

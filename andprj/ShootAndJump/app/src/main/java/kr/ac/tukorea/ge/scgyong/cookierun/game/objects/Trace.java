package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Bitmap;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
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

    private final RectF collisionRect = new RectF();


    public Trace(int mipmapId, float createPositionX, int direction) {
        super(mipmapId);
        Bitmap bitmap = BitmapPool.get(mipmapId);
        positionX = createPositionX;
        positionY = Metrics.cvtY(0.3f);
        moveDirection = direction;
    }

    @Override
    public void update() {
        if(moveDirection == 0)
            positionX -= GameView.frameTime * unit * 5.0f;
        else
            positionX += GameView.frameTime * unit * 5.0f;

        collisionRect.set
                (
                        positionX - unit * 0.05f,
                        positionY - unit * 0.05f,
                        positionX + unit * 0.05f,
                        positionY + unit * 0.05f
                );

        Scene scene = Scene.top();

        // 몬스터 명중 시 대미지 준 후 스스로 삭제
        ArrayList<IGameObject> objects = scene.objectsAt(MainScene.Layer.LAYER1);
        for (IGameObject obj : objects) {
            if (obj instanceof Monster) {
                Monster monster = (Monster) obj;
                if (collisionRect.intersect((monster.getCollisionRect()))) {
                    monster.giveDamage(1);
                    scene.remove(MainScene.Layer.LAYER3, this);
                    return;
                }
            }
        }

        if(positionX > screenWidth + unit * 0.05 || positionX < - unit * 0.05) {
            scene.remove(MainScene.Layer.LAYER3, this);
        }

        setPosition(positionX + MainScene.camera.shakeResultX,
                positionY + MainScene.camera.shakeResultY,
                unit * 0.1f, unit * 0.1f);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}

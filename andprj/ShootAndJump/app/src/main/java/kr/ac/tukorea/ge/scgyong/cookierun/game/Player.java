package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;


public class Player extends Sprite implements IBoxCollidable {
    private static final String TAG = Player.class.getSimpleName();

    private final RectF collisionRect = new RectF();

    private Sprite sprite;

    float screenWidth = Metrics.width;
    float screenHeight = Metrics.height;

    // DirectX, OpenGL 등에서 사용하는 1.0 크기 기준을 사용한다.
    float unit = screenHeight * 0.5f;

    // 플레이어 위치
    private float positionX = screenWidth * 0.5f;
    private float positionY = screenHeight * 0.5f;
    float spriteRatio = 0.0f;
    public Player(int mipmapId) {
        super(mipmapId);
        Bitmap bitmap = BitmapPool.get(mipmapId);

        float spriteWidth = bitmap.getWidth();
        float spriteHeight = bitmap.getHeight();
        spriteRatio = spriteHeight / spriteWidth;

        // 가로가 더 기므로 세로 길이를 줄인다.
        // 0.5, 0.5 scale로 그린다.
        setPosition(positionX, positionY, unit * 0.5f, unit * 0.5f * spriteRatio);
    }

    // 0: left touch  1: right touch
    public void inputEvent(int touchType) {
        if     (touchType == 0)  positionX -= unit * 0.2f;
        else if(touchType == 1)  positionX += unit * 0.2f;

        setPosition(positionX, positionY, unit * 0.5f, unit * 0.5f * spriteRatio);
    }

    @Override
    public void update()  {

    }

    private void handleLeftTouch() {
        Scene top = Scene.top();
        if (top instanceof MainScene) {
            ((MainScene) top).onLeftTouch();
        }
    }

    private void handleRightTouch() {
        Scene top = Scene.top();
        if (top instanceof MainScene) {
            ((MainScene) top).onRightTouch();
        }
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}

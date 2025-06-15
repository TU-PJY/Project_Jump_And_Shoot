package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Monster extends Sprite implements IBoxCollidable {
    private float positionX;
    private float positionY;

    // 0: left, 1: right
    int moveDirection = 0;
    int prevDirection = -1;

    float screenWidth = Metrics.width;
    float screenHeight = Metrics.height;
    float unit = screenHeight * 0.5f;

    // 몬스터 이미지
    Bitmap currentImage;

    private final RectF collisionRect = new RectF();

    public Monster(int mipmapId, float createPositionX) {
        super(mipmapId);
        currentImage = BitmapPool.get(mipmapId);

        // 생성자에서 입력받는 위치로 몬스터 스폰
        positionX = screenWidth * createPositionX;
        positionY = screenHeight * 0.8f;

        setPosition(positionX, positionY, unit * 0.25f, unit * 0.25f);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {
        // 플레이어 위치에 따라 몬스터 이동 방향이 달라진다.
        float destPosition = MainScene.player.getPosition();
        if(positionX < destPosition) {
            moveDirection = 1;
            if (prevDirection != moveDirection) {
                setImageResourceId(R.mipmap.monster_right);
                prevDirection = moveDirection;
            }
        }

        else if(positionX > destPosition) {
            moveDirection = 0;
            if (prevDirection != moveDirection) {
                setImageResourceId(R.mipmap.monster_left);
                prevDirection = moveDirection;
            }
        }

        if(moveDirection == 0)
            positionX -= GameView.frameTime * unit;
        else
            positionX += GameView.frameTime * unit;

        collisionRect.set
                (
                        positionX - unit * 0.125f,
                        positionY - unit * 0.125f,
                        positionX + unit * 0.125f,
                        positionY + unit * 0.125f
                );

        // 가로세로가 동일하므로 그냥 그린다.
        // 0.5, 0.5 scale로 그린다.
        setPosition(positionX + MainScene.camera.shakeResultX,
                positionY + MainScene.camera.shakeResultY,
                unit * 0.25f, unit * 0.25f);
    }
}

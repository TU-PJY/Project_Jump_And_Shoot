package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Monster extends Sprite implements IBoxCollidable {
    public float positionX;
    public float positionY;

    // 0: left, 1: right
    int moveDirection = 0;
    int prevDirection = -1;

    // 몬스터 체력
    public int HP = 1;

    public boolean dead;
    private float removeTimer;

    private final RectF collisionRect = new RectF();

    public Monster(int mipmapId, float createPositionX) {
        super(mipmapId);

        // 생성자에서 입력받는 위치로 몬스터 스폰
        positionX = Metrics.cvtX(createPositionX);
        positionY = Metrics.cvtY(0.3f);

        HP hp = new HP(R.mipmap.hp_indicator, this);
        Scene scene = Scene.top();
        scene.add(MainScene.Layer.LAYER1, hp);
    }

    public void giveDamage(int Value) {
        if(HP == 0)
            return;
        HP -= Value;
        Sound.playEffect(R.raw.hit);
        if(HP == 0) {
            Sound.playEffect((R.raw.monster_sound1));
            MainScene.gameScore.addScore();
        }

    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {
        // 체력이 0이 될 경우 스스로 삭제
        if(HP == 0) {
            dead = true;

            if(moveDirection == 1)
                setImageResourceId(R.mipmap.monster_dead_right);
            else
                setImageResourceId(R.mipmap.monster_dead_left);

            removeTimer += GameView.frameTime;
            if(removeTimer >= 1.0) {
                Scene scene = Scene.top();
                scene.remove(MainScene.Layer.LAYER1, this);
                return;
            }
        }

        else {
            // 플레이어 위치에 따라 몬스터 이동 방향이 달라진다.
            float destPosition = MainScene.player.getPosition();
            if (positionX < destPosition) {
                moveDirection = 1;
                if (prevDirection != moveDirection) {
                    setImageResourceId(R.mipmap.monster_right);
                    prevDirection = moveDirection;
                }
            } else if (positionX > destPosition) {
                moveDirection = 0;
                if (prevDirection != moveDirection) {
                    setImageResourceId(R.mipmap.monster_left);
                    prevDirection = moveDirection;
                }
            }

            if (moveDirection == 0)
                positionX -= GameView.frameTime * Metrics.unit;
            else
                positionX += GameView.frameTime * Metrics.unit;
        }

        collisionRect.set
                (
                        positionX -  Metrics.unit * 0.125f,
                        positionY -  Metrics.unit * 0.125f,
                        positionX +  Metrics.unit * 0.125f,
                        positionY +  Metrics.unit * 0.125f
                );

        setPosition(
                positionX + MainScene.camera.shakeResultX,
                positionY + MainScene.camera.shakeResultY,
                Metrics.unit * 0.25f,  Metrics.unit * 0.25f
        );
    }
}

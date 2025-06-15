package kr.ac.tukorea.ge.scgyong.cookierun.game.objects;

import android.graphics.Bitmap;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;


public class Player extends Sprite implements IBoxCollidable {
    private static final String TAG = Player.class.getSimpleName();

    private final RectF collisionRect = new RectF();

    private Sprite sprite;

    // 플레이어 위치
    private float positionX;
    private float positionY;

    // 플레이어 높이 오프셋
    private float heightOffset;
    private float sinNum;

    // 플레이어 최종 이동 위치
    private float destPositionX = positionX;

    // 플레이어 이동 입력 가능 여부
    private boolean moveEnabled = true;

    // 플레이어 이미지
    Bitmap currentImage;

    // 플레이어 이동 상태
    // 0: left, 1: right
    private int moveState = 0;

    // 중복 스프라이트 교체를 방지하기 위한 이전 이동 상태
    private int prevMoveState = -1;

    // 플레이어 이동 가능 횟수
    private int moveCount = 0;

    float spriteRatio = 0.0f;

    // 플레이어 숨쉬기 애니메이션 변수
    private float sizeOffset;
    private float sizeNum;

    // 플레이어 체력
    public int HP = 5;
    private boolean damageIgnore;
    private float damageCooltime;

    public Player(int mipmapId) {
        super(mipmapId);

        float spriteWidth = bitmap.getWidth();
        float spriteHeight = bitmap.getHeight();
        positionX = Metrics.cvtX(0.0f);
        positionY = Metrics.cvtY(0.3f);
        destPositionX = positionX;

        spriteRatio = spriteHeight / spriteWidth;
    }

    // 0: left touch  1: right touch
    public void inputEvent(int touchType) {
        // 이동 상태가 아닐 경우 입력을 무시한다.
        if(!moveEnabled)
            return;

        // 스프라이트 크기는 같으므로 방향이 달라질때마다 스프라이트를 교체한다.
        // 방향이 달라질때만 새로운 스프라이트를 요정한다.
        if (touchType == 0)  {
            if(moveCount >= 1)
                return;

            destPositionX += Metrics.unit * 1.2f;
            moveState = 1;
            moveCount ++;

            if(prevMoveState != moveState) {
                setImageResourceId(R.mipmap.commando_left);
                prevMoveState = moveState;
            }
        }

        else if(touchType == 1) {
            if(moveCount <= -1)
                return;

            destPositionX -= Metrics.unit * 1.2f;
            moveState = 0;
            moveCount --;
            if(prevMoveState != moveState) {
                setImageResourceId(R.mipmap.commando_right);
                prevMoveState = moveState;
            }
        }

        // 총 발사 사운드 재생
        Sound.playEffect(R.raw.m1);

        // 카메라 흔들림 추가
        MainScene.camera.AddShake(0.05f);

        // 총알 오브젝트 추가
        Trace trace;
        if(moveState == 0)
            trace = new Trace(R.mipmap.trace, positionX, 1);
        else
            trace = new Trace(R.mipmap.trace, positionX, 0);
        Scene scene = Scene.top();
        scene.add(MainScene.Layer.LAYER3, trace);

        // 이동 입력 시 잠시 입력을 무시한다.
        moveEnabled = false;
    }

    @Override
    public void update()  {
        // 목표 지점에 도달하면 입력을 다시 받는다.
        // 왼쪽 이동
        if(moveState == 0) {
            positionX -= GameView.frameTime * Metrics.unit * 4.0f;
            if(positionX < destPositionX) {
                positionX = destPositionX;
                moveEnabled = true;
            }
        }

        // 오른쪽 이동
        else if(moveState == 1) {
            positionX += GameView.frameTime * Metrics.unit * 4.0f;
            if(positionX > destPositionX) {
                positionX = destPositionX;
                moveEnabled = true;
            }
        }

        // 이동 중에는 점프
        if(!moveEnabled) {
            float totalMove = Metrics.unit * 1.2f;
            float moved = Math.abs(destPositionX - positionX);
            float progress = moved / totalMove;
            if(progress > 1.0f) progress = 1.0f;

            sinNum = progress * (float)Math.PI;
            heightOffset = -(float)Math.sin(sinNum) * Metrics.unit * 1.0f;
        }
        else {
            heightOffset = 0.0f;
            sinNum = 0.0f;
        }

        sizeNum += GameView.frameTime * 4.0f;
        sizeOffset = (float)Math.sin(sizeNum) * Metrics.unit * 0.01f;

        collisionRect.set(
                positionX - Metrics.unit * 0.06f,
                positionY - Metrics.unit * 0.06f,
                positionX + Metrics.unit * 0.06f,
                positionY + Metrics.unit * 0.06f
        );

        // 몬스터 접촉 시 체력 감소 후 쿨타임 1초 설정
        if(!damageIgnore && moveEnabled) {
            Scene scene = Scene.top();
            ArrayList<IGameObject> objects = scene.objectsAt(MainScene.Layer.LAYER1);
            for (IGameObject obj : objects) {
                if (obj instanceof Monster) {
                    Monster monster = (Monster) obj;
                    if (collisionRect.intersect((monster.getCollisionRect()))) {
                        if (!monster.dead) {
                            monster.setAttackAnimation();

                            HP--;
                            Sound.playEffect(R.raw.hurt);
                            if (HP < 0)
                                HP = 0;

                            damageIgnore = true;
                            damageCooltime = 1.0f;
                            MainScene.camera.AddShake(0.05f);

                            break;
                        }
                    }
                }

                if (obj instanceof BigMonster) {
                    BigMonster monster = (BigMonster) obj;
                    if (collisionRect.intersect((monster.getCollisionRect()))) {
                        if (!monster.dead) {
                            monster.setAttackAnimation();

                            HP--;
                            Sound.playEffect(R.raw.hurt);
                            if (HP < 0)
                                HP = 0;

                            damageIgnore = true;
                            damageCooltime = 1.0f;
                            MainScene.camera.AddShake(0.05f);

                            break;
                        }
                    }
                }
            }
        }

        else {
            damageCooltime -= GameView.frameTime;
            if(damageCooltime <= 0.0f) {
                damageCooltime = 0.0f;
                damageIgnore = false;
            }
        }

        setPosition(
                positionX + MainScene.camera.shakeResultX,
                positionY + heightOffset+ MainScene.camera.shakeResultY - sizeOffset * 0.5f,
                Metrics.unit * 0.5f, Metrics.unit * 0.5f * spriteRatio + sizeOffset
        );
    }

    public float getPosition() {
        return positionX;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}

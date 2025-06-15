package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
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
    private float positionY = screenHeight * 0.8f;

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
    // -1 ~ 1 가능
    private int moveCount = 0;

    float spriteRatio = 0.0f;
    public Player(int mipmapId) {
        super(mipmapId);
        currentImage = BitmapPool.get(mipmapId);

        float spriteWidth = bitmap.getWidth();
        float spriteHeight = bitmap.getHeight();
        spriteRatio = spriteHeight / spriteWidth;

        // 가로가 더 기므로 세로 길이를 줄인다.
        // 0.5, 0.5 scale로 그린다.
        setPosition(positionX, positionY, unit * 0.5f, unit * 0.5f * spriteRatio);
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

            destPositionX += unit * 1.5f;
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

            destPositionX -= unit * 1.5f;
            moveState = 0;
            moveCount --;
            if(prevMoveState != moveState) {
                setImageResourceId(R.mipmap.commando_right);
                prevMoveState = moveState;
            }
        }

        // 이동 입력 시 잠시 입력을 무시한다.
        moveEnabled = false;
    }

    @Override
    public void update()  {
        // 목표 지점에 도달하면 입력을 다시 받는다.
        // 왼쪽 이동
        if(moveState == 0) {
            positionX -= 1000.0f * GameView.frameTime * 2.0;
            if(positionX < destPositionX) {
                positionX = destPositionX;
                moveEnabled = true;
            }
        }

        // 오른쪽 이동
        else if(moveState == 1) {
            positionX += 1000.0f * GameView.frameTime * 2.0;
            if(positionX > destPositionX) {
                positionX = destPositionX;
                moveEnabled = true;
            }
        }

        // 이동 중에는 점프
        if(!moveEnabled) {
            float totalMove = unit * 1.5f;
            float moved = Math.abs(destPositionX - positionX);
            float progress = moved / totalMove;
            if(progress > 1.0f) progress = 1.0f;

            sinNum = progress * (float)Math.PI;
            heightOffset = -(float)Math.sin(sinNum) * unit * 1.0f;
        }
        else {
            heightOffset = 0.0f;
            sinNum = 0.0f;
        }


        setPosition(positionX, positionY + heightOffset, unit * 0.5f, unit * 0.5f * spriteRatio);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}

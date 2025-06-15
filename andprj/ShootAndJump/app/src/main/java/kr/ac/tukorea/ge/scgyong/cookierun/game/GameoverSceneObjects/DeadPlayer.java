package kr.ac.tukorea.ge.scgyong.cookierun.game.GameoverSceneObjects;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class DeadPlayer extends Sprite implements IGameObject {
    private float positionX = Metrics.cvtX(Metrics.ASP(-1.0f)) - Metrics.unit * 0.5f;
    private float positionY = Metrics.cvtY(0.0f);

    public DeadPlayer(int mipmapId) {
        super(mipmapId);
    }

    @Override
    public void update() {
        positionX = positionX + (Metrics.cvtX(1.0f) - positionX) * GameView.frameTime * 3.0f;
        setPosition(positionX, positionY, Metrics.unit, Metrics.unit);
    }
}

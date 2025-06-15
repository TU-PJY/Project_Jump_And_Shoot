package kr.ac.tukorea.ge.scgyong.cookierun.game.PauseSceneObjects;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseBackground extends Sprite implements IGameObject {
    public PauseBackground(int mipmapId) {
        super(mipmapId);
        setPosition(Metrics.cvtX(0.0f), Metrics.cvtY(0.0f), Metrics.width, Metrics.height);
    }
}

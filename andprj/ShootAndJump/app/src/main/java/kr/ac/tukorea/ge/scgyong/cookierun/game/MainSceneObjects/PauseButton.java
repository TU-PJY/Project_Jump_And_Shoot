package kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseButton extends Sprite implements IGameObject {
    public PauseButton(int mipmapId) {
        super(mipmapId);
        setPosition(Metrics.cvtX(Metrics.ASP(-0.9f)), Metrics.cvtY(0.8f),
                Metrics.unit * 0.2f, Metrics.unit * 0.2f);
    }
}

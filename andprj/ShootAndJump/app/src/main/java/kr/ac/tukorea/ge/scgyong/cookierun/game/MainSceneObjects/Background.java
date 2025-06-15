package kr.ac.tukorea.ge.scgyong.cookierun.game.MainSceneObjects;

import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Background extends Sprite implements IGameObject {
    public Background(int mipmapId) {
        super(mipmapId);

    }

    @Override
    public void update() {
        setPosition(Metrics.cvtX(0.0f) + MainScene.camera.shakeResultX,
                Metrics.cvtY(0.0f) + MainScene.camera.shakeResultY,
                Metrics.width, Metrics.height);
    }
}

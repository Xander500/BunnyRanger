package io.BunnyRanger;

import com.badlogic.gdx.Gdx;

public class ScreenLevel7 extends ScreenLevel {

    @Override
    public void create() {
        super.create();
        changeBackground("vally.png");
    }

    public void makeEnemies() {
        EnemyBombardierSlime enemy1 = new EnemyBombardierSlime(worldHandler, 22, 5);
        EnemyMossSlime enemy2 = new EnemyMossSlime(worldHandler, 27, 5);
        EnemyBanditFox enemy3 = new EnemyBanditFox(worldHandler, 32, 5);
        EnemyBanditFox enemy4 = new EnemyBanditFox(worldHandler, 36, 5);

        enemies.addEnemy(enemy1);
        enemies.addEnemy(enemy2);
        enemies.addEnemy(enemy3);
        enemies.addEnemy(enemy4);

        enemy1.addWeapon(new WeaponBomb2(false));
        enemy2.addWeapon(new WeaponBomb(false));
        enemy3.addWeapon(new WeaponPistol2(false));
        enemy4.addWeapon(new WeaponBow3(false));

        addArena();
        GameHandler.makeSign(GameHandler.screenLevel8);
        drawSign = false;
    }

    public void render(float delta) {
        super.render(delta);
        if (levelDisplayCounter < 300) {
            GameHandler.batch.begin();
            this.displayTitle("LEVEL 7 --- Bomb Garden", 350, 650);
            GameHandler.batch.end();
        }
        WorldHandler.camera.update();
    }
}

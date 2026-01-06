package io.BunnyRanger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Filter;

import java.util.ArrayList;

public class Enemies {

    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    ArrayList<Enemy> enemyListRemove = new ArrayList<Enemy>();

    ArrayList<Projectile> projectileList;

    ArrayList<Integer> counterList = new ArrayList<Integer>();

    WorldInstance world;

    public Enemies(WorldInstance world, ArrayList<Projectile> projectileList) {
        this.projectileList = projectileList;
        this.world = world;
    }

    public void addEnemy(Enemy enemy) {

        counterList.add(0);
        enemyList.add(enemy);
        System.out.println("added enemy " + enemyList.size());
        System.out.println("added counter " + counterList.size());


    }

    public void drawAll(int i, Batch batch) {

        enemyList.get(i).getHealthBarSpriteBack().draw(batch);
        enemyList.get(i).getHealthBarSprite().draw(batch);

        enemyList.get(i).getWeaponSprite().draw(batch);

        enemyList.get(i).updateEnemySprite();

        enemyList.get(i).getEnemySprite().draw(batch);

    }

    // enemyList.get(i).move(enemyList.get(i).getCurrentWeapon().getDirection()); only moving during attack works currently


    public void update(Batch batch) throws Exception {

        try {

            for (int i = 0; i < enemyList.size(); i++) {

                if (enemyList.get(i).checkIfDead()) {

                    // REMOVED BROKE HTML
                    //world.addDestroyBody(enemyList.get(i).getBody());
                    //world.addDestroyBody(enemyList.get(i).bodyB);

                    Filter filter = new Filter();
                    filter.maskBits = 0x0000;
                    enemyList.get(i).getFixture().setFilterData(filter);

                    enemyListRemove.add(enemyList.get(i));
                    //counterList.remove(i);
                    MainApplication.getParty().addGold(5); // change 5 to enemy.getGoldValue()

                } else {

                    enemyList.get(i).updateHealthBar();

                    enemyList.get(i).drawWeapon();

                    enemyList.get(i).moveAttack(enemyList.get(i).getCurrentWeapon().getDirection());

                    this.drawAll(i, batch);

                    int value = counterList.get(i); // get value
                    value = value + 1; // increment value
                    counterList.set(i, value); // replace value

                    if (counterList.get(i) > enemyList.get(i).getCurrentWeapon().getDelay(100)) {

                        ((Weapon) enemyList.get(i).getCurrentWeapon()).getClosestTarget();
                        enemyList.get(i).useWeapon();
                        //1234
                        //projectileList.addAll(enemyList.get(i).getCurrentWeapon().getProjectiles());

                        counterList.set(i, 0); // replace value

                    }

                }

            }
            enemyList.removeAll(enemyListRemove);
        } catch (Exception e) {
            System.out.println("size at error in draw" + enemyList.size());
            enemyList.size();

        }
    }

    public boolean checkAllIfDead() {
        for (Enemy enemy : this.enemyList) {
            if (!enemy.checkIfDead()) {
                return false;
            }
        }
        return true;
    }

    public void removeAll() {
        this.enemyList.clear();
        this.counterList.clear();
    }

}

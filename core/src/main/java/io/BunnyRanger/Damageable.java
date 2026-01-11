package io.BunnyRanger;

abstract public interface Damageable {

    //Body bodyB;
    //float health;

    abstract public float takeDamage(float damage);

    //abstract public void getHealth();

    abstract public void updateHealthBar();

    abstract void createHealthBar(WorldHandler world);

    abstract boolean checkIfDead();

}

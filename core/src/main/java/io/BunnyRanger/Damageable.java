package io.BunnyRanger;

abstract public interface Damageable {

    //Body bodyB;
    //float health;

    default public float takeDamage(float damage) {
        return takeDamage(DamageCalculator.calculate(null, this, damage, DamageCalculator.DamageType.REGULAR, DamageCalculator.defaultPalette()));
    }

    abstract public float takeDamage(DamageCalculator.Result damage);

    //abstract public void getHealth();

    abstract public void updateHealthBar();

    abstract void createHealthBar(WorldHandler world);

    abstract boolean checkIfDead();

}

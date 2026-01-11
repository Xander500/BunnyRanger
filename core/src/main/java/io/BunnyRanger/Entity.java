package io.BunnyRanger;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface Entity {

    abstract public String getNameID();

    abstract public void executeBegin(Entity secondEntity);
    abstract public void executeEnd(Entity secondEntity);

    abstract public Body getBody();

    abstract public Fixture getFixture();

    abstract public WorldHandler getWorldInstance();

    abstract public void flip(boolean facingRight);
}

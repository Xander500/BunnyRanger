package io.BunnyRanger;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {


    public void beginContact(Contact contact) {

        if (contact.getFixtureA() == null || contact.getFixtureB() == null) {
            return;
        }

        if (contact.getFixtureA().getUserData() == null || contact.getFixtureB().getUserData() == null) {
            return;
        }

        //System.out.println("contact made between " + contact.getFixtureA().getUserData() + " and " + contact.getFixtureB().getUserData());


        Entity currentEntity = (Entity) contact.getFixtureA().getUserData();
        Entity secondEntity = (Entity) contact.getFixtureB().getUserData();

        currentEntity.executeBegin(secondEntity);
        secondEntity.executeBegin(currentEntity);

    }

    public void endContact(Contact contact) {

        if (contact.getFixtureA() == null || contact.getFixtureB() == null) {
            return;
        }

        if (contact.getFixtureA().getUserData() == null || contact.getFixtureB().getUserData() == null) {
            return;
        }

        Entity currentEntity = (Entity) contact.getFixtureA().getUserData();
        Entity secondEntity = (Entity) contact.getFixtureB().getUserData();

        currentEntity.executeEnd(secondEntity);
        secondEntity.executeEnd(currentEntity);

    }

    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}


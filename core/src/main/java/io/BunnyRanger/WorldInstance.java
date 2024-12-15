package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class WorldInstance {

    World world;
    Array<Body> bodyDestroyList;
    boolean removeingTime;

    public WorldInstance() {

        this.world = new World(new Vector2(0, -10), true);

    }

    public World getWorld() {
        return this.world;
    }

    public void destroyBodies() {

        if (!removeingTime) {
            return;
        }

        this.bodyDestroyList = new Array<Body>(world.getBodyCount());
        world.getBodies(bodyDestroyList);

        for (Body body : bodyDestroyList) {
            if (body != null && !(body.getUserData() == null) && body.getUserData().equals("remove")) {
                world.destroyBody(body);
            }
        }
        this.removeingTime = false;

    }

    public void addDestroyBody(Body body) {
        body.setUserData("remove");
        this.removeingTime = true;
    }

}

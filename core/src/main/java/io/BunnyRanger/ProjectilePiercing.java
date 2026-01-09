package io.BunnyRanger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class ProjectilePiercing extends Projectile {

    public ProjectilePiercing(WorldInstance world, float x, float y, float xSize, float ySize, float angle, Vector2 magnitude, float damage, float density, boolean friendly, boolean facingRight, int parent) {
        super(world, x, y, xSize, ySize, angle, magnitude, damage, density, friendly, facingRight, parent);
        this.fixture.setSensor(true);
    }
    public void executeBegin(Entity secondEntity) {

        if (secondEntity.getNameID().equals("Floor")) {

            remove = true;

            for (Fixture fixture : body.getFixtureList()) {

                // Get the current filter data
                Filter filter = fixture.getFilterData();

                // Set the new category bits
                filter.categoryBits = 0x0000;
                filter.maskBits = 0x0001;
                // Apply the updated filter data to the fixture
                fixture.setFilterData(filter);

            }

        }

    }
}

package io.github.some_example_name.engine.collision;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private List<Collidable> collidables = new ArrayList<>();
    
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    
    public void update() {
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable a = collidables.get(i);
                Collidable b = collidables.get(j);
                if (checkOverlap(a, b)) {
                    resolve(a, b);
                }
            }
        }
    }
    
    private boolean checkOverlap(Collidable a, Collidable b) {
        return a.getBounds().overlaps(b.getBounds());
    }
    
    private void resolve(Collidable a, Collidable b) {
        a.onCollision(b);
        b.onCollision(a);
    }
}

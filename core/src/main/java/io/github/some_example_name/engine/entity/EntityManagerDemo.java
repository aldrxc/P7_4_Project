package io.github.some_example_name.engine.entity;

import java.util.Collection;

/**
 * Demo class to test the Entity Manager system.
 * 
 * This demonstrates all 4 OOP pillars in action!
 */

public class EntityManagerDemo {

  public static void main(String[] args) {
    System.out.println("=== Entity Manager Demo ===\n");

    // Create the manager (using interface type!)
    IEntityManager manager = new EntityManager();

    // Test 1: Create entities
    System.out.println("--- TEST 1: Creating Entities ---");
    TestEntity entity1 = new TestEntity("Player", 100, 100);
    entity1.setVelocity(50, 0); // Moving right at 50 units/sec
    manager.create(entity1);

    TestEntity entity2 = new TestEntity("Enemy", 200, 100);
    entity2.setVelocity(-30, 0);
    manager.create(entity2);

    TestEntity entity3 = new TestEntity("Item", 150, 150);
    // No velocity - stationary
    manager.create(entity3);

    // Test 2: Update entities
    System.out.println("--- TEST 2: Updating Entities ---");
    float deltaTime = 0.1f; // Simulate 0.1 seconds per frame

    for (int i = 1; i <=3; i++) {
      System.out.println("Frame " + i + ":");
      manager.update(deltaTime);
      System.out.println();
    }

    // Test3: Retrieve entities
    System.out.println("--- TEST 3: Retrieving Entities ---");
    Entity retrieved = manager.get(entity1.getId());
    System.out.println("Retrieved: " + retrieved.getId());
    System.out.println("Position: (" + retrieved.getPositionX() + ", " + retrieved.getPositionY() + ")\n");

    // Test 4: Get all entities
    System.out.println("--- TEST 4: Getting All Entities ---");
    Collection<Entity> all = manager.getAll();
    System.out.println("Total entities: " + all.size());
    for (Entity e: all) {
      if (e instanceof TestEntity) {
        TestEntity te = (TestEntity) e;
        System.out.println(" -" + te.getName() + " at (" + te.getPositionX() + ", " + te.getPositionY() + ")");
      }
    }
    System.out.println();

    // Test 5: Remove an entity
    System.out.println("--- TEST 5: Removing Entity ---");
    System.out.println("Before removal: " + manager.size() + " entities");
    manager.remove(entity2.getId());
    System.out.println("After removal: " + manager.size() + " entities\n");

    // Test 6: Test encapsulation
    System.out.println("--- TEST 6: Testing Encapsulation ---");
    Collection<Entity> entities = manager.getAll();
    try {
      entities.clear(); // Try to break it
      System.out.println("ERROR: Collection was modifiable!");
    } catch (UnsupportedOperationException e) {
      System.out.println("✓ Encapsulation protected! Cannot modify collection.");
    }
    System.out.println("Manager still has " + manager.size() + " entities\n");

    // Test 7: Clear all
    System.out.println("--- TEST 7: Clear All ---");
    manager.clear();
    System.out.println("After clear: " + manager.size() + " entities\n");

    System.out.println("=== Demo Complete! ===");
  }
  
}

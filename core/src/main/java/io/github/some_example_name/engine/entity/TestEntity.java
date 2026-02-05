package io.github.some_example_name.engine.entity;

/**
 * Simple test entity for demonstrating the Entity Manager.
 * This entity just moves based on its velocity.
 */
public class TestEntity extends Entity {
  
  private String name;

  public TestEntity(String name, float x, float y) {
    super(x, y);
    this.name = name;
  }

  @Override
  public void update(float deltaTime) {
    // Use the helper method from Entity
    applyMovement(deltaTime);

    // Print position every update (for testing)
    System.out.printf("%s is at (%.2f, %.2f)%n", name, getPositionX(), getPositionY());
  }

  public String getName() {
    return name;
  }
}

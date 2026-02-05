package io.github.some_example_name.engine.entity;

/**
 * NPCEntity represents a non-player character or object
 * 
 * This demonstrates INHERITANCE with different behavior than PlayerEntity.
 * Shows POLYMORPHISM - NPCs update differently than Players
 */

public class NPCEntity extends Entity {
  
  // ===== ATTRIBUTES =====
  private String type;
  private float updateTimer;
  private boolean aiEnabled;

  // ===== CONSTRUCTORS =====
  
  public NPCEntity(String type) {
    super();
    this.type = type;
    this.updateTimer = 0.0f;
    this.aiEnabled = true;
  }

  public NPCEntity(String type, float positionX, float positionY) {
    super(positionX, positionY);
    this.type = type;
    this.updateTimer = 0.0f;
    this.aiEnabled = true;
  }

  // ===== ABSTRACT METHOD IMPLEMENTATION =====

  @Override
  public void update(float deltaTime) {
    // Update internal timer
    updateTimer += deltaTime;

    // Only update if AI is enabled and entity is active
    if (aiEnabled && isActive()) {
      // Apply movement
      applyMovement(deltaTime);

      // Simple friction effect (slows down over time)
      float friction = 0.98f;
      setVelocity(getVelocityX() * friction, getVelocityY() * friction);
    }
  }

  // ===== NPC-SPECIFIC METHODS =====

  public void applyImpulse(float impulseX, float impulseY) {
    setVelocity(getVelocityX() + impulseX, getPositionY() + impulseY);
  }

  public void resetTimer() {
    this.updateTimer = 0.0f;
  }

  // ===== GETTERS AND SETTERS =====

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float getUpdateTimer() {
    return updateTimer;
  }

  public boolean isAiEnabled() {
    return aiEnabled;
  }

  public void setAiEnabled(boolean aiEnabled) {
    this.aiEnabled = aiEnabled;
  }

}

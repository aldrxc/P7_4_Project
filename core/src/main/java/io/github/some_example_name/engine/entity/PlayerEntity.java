package io.github.some_example_name.engine.entity;

/**
 * PlayerEntity represents a player-controlled entity.
 * 
 * This demonstrates INHERITANCE and POLYMORPHISM.
 * Still part of the Abstract Engine (context-free)
 */

public class PlayerEntity extends Entity {
  
  // ===== ATTRIBUTES =====
  private String name;
  private float moveSpeed;
  private boolean movingUp;
  private boolean movingDown;
  private boolean movingLeft;
  private boolean movingRight;

  // ===== CONSTRUCTORS =====
   
  public PlayerEntity(String name) {
    super();
    this.name = name;
    this.moveSpeed = 100.0f; // Default: 100 units/second
    initializeMovementFlags();
  }
  public PlayerEntity(String name, float positionX, float positionY) {
    super(positionX, positionY);
    this.name = name;
    this.moveSpeed = 100.0f;
    initializeMovementFlags();
  }

  public PlayerEntity(String name, float positionX, float positionY, float moveSpeed) {
    super(positionX, positionY);
    this.name = name;
    this.moveSpeed = moveSpeed;
    initializeMovementFlags();
  }

  private void initializeMovementFlags() {
    this.movingUp = false;
    this.movingDown = false;
    this.movingLeft = false;
    this.movingRight = false;
  }

  // ===== ABSTRACT METHOD IMPLEMENTATION =====

  @Override
  public void update(float deltaTime) {
    // Calculate velocity based on movement flags
    float targetVelX = 0.0f;
    float targetVelY = 0.0f;

    if (movingRight) targetVelX += moveSpeed;
    if (movingLeft) targetVelX -= moveSpeed;
    if (movingUp) targetVelY += moveSpeed;
    if (movingDown) targetVelY -= moveSpeed;

    // Normalize diagonal movement (prevent faster diagonal speed)
    if ((movingLeft || movingRight) && (movingUp || movingDown)) {
      float diagonal = (float) (moveSpeed / Math.sqrt(2));
      if (targetVelX != 0) targetVelX = Math.signum(targetVelX) * diagonal;
      if (targetVelY != 0) targetVelY = Math.signum(targetVelY) * diagonal;
    }

    setVelocity(targetVelX, targetVelY);
    applyMovement(deltaTime);
  }

  // ===== PLAYER-SPECIFIC METHODS =====

  public void move(float forceX, float forceY) {
    float velX = forceX * moveSpeed;
    float velY = forceY * moveSpeed;
    setVelocity(velX, velY);
  }

  public void stop() {
    setVelocity(0, 0);
    movingUp = false;
    movingDown = false;
    movingLeft = false;
    movingRight = false;
  }

  // ===== MOVEMENT FLAG SETTERS =====

  public void setMovingUp(boolean moving) {
    this.movingUp = moving;
  }

  public void setMovingDown(boolean moving) {
    this.movingDown = moving;
  }

  public void setMovingLeft(boolean moving) {
    this.movingLeft = moving;
  }

  public void setMovingRight(boolean moving) {
    this.movingRight = moving;
  }

  // ===== GETTERS =====

  public String getName() {
    return name;
  }

  public float getMoveSpeed() {
    return moveSpeed;
  }

  public boolean isMoving() {
    return movingUp || movingDown || movingLeft || movingRight;
  }

}

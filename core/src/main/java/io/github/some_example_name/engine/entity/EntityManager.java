package io.github.some_example_name.engine.entity;

import java.util.*;

/**
 * Concrete implementation of IEntityManager.
 * 
 * This class manages the lifecycle of all entities using a HashMap
 * for 0(1) lookups by UUID
 * 
 * Design: Uses composition - EntityManager OWNS the entities.
 */

public class EntityManager implements IEntityManager {
  
  // ===== ATTRIBUTES =====
  private final Map<UUID, Entity> entities;
  private final List<UUID> pendingRemoval;

  // ===== CONSTRUCTOR =====
  public EntityManager() {
    this.entities = new HashMap<>();
    this.pendingRemoval = new ArrayList<>();
  }

    // TODO: Implement create() method
    @Override
    public UUID create(Entity entity) {
      if (entity == null) {
        throw new IllegalArgumentException("Cannot create null entity");
      }

      UUID id = entity.getId();
      entities.put(id, entity);

      return id;
    }

    // TODO: Implement remove() method
    @Override
    public Entity remove(UUID id) {
      if (id == null) {
        return null;
      }
      return entities.remove(id);
    }

    // TODO: Implement get() method
    @Override
    public Entity get(UUID id) {
      if (id == null) {
        return null;
      }
      return entities.get(id);
    }

    // TODO: Implement update() method
    @Override
    public void update(float deltaTime) {
      // Update all active entities
      for (Entity entity: entities.values()) {
        if (entity.isActive()) {
          entity.update(deltaTime);
        }
      }

      // Process pending removals (safe - after iteration)
      if (!pendingRemoval.isEmpty()) {
        for (UUID id : pendingRemoval) {
          remove(id);
        }
        pendingRemoval.clear();
      }
    }

    // TODO: Implement getAll() method
    @Override
    public Collection<Entity> getAll() {
      return Collections.unmodifiableCollection(entities.values());
    }

    // TODO: Implement size() method
    @Override
    public int size() {
      return entities.size();
    }

    // TODO: Implement contains() method
    @Override
    public boolean contains(UUID id) {
      return id != null && entities.containsKey(id);
    }

    // TODO: Implement clear() method
    @Override
    public void clear() {
      entities.clear();
      pendingRemoval.clear();
    }
}

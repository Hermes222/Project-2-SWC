package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class VehicleDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void create(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return;
    }
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }
}

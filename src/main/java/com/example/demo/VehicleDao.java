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

    /**
     * Crud
     * INSERT SQL
     */
    public void create(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }

    /**
     * cRud
     * SELECT SQL
     * @param id
     * @return
     */
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    /**
     * crUd
     * UPDATE SQL
     * @param newV
     */
    public void updateVehicle(Vehicle newV){
        entityManager.merge(newV);
    }

    /**
     * cruD
     * DELETE SQL
     * @param id
     */
    public void deleteVehicle(int id) {
        entityManager.remove(getById(id));
    }

    /**
     * cruD
     * DELETE SQL
     */
    public Vehicle getLatestVehicle() {
        return (Vehicle) entityManager.createNativeQuery("SELECT * FROM egr327 ORDER BY ID DECREASING LIMIT 1");
    }
}

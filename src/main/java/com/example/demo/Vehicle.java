package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Vehicle class. Parent Class to Car and Truck
 */
@Entity
@Table (name = "Vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String makeModel;
    private Integer year;
    private Double retailPrice;

    /**
     * Default Constructor. Default data is empty
     */
    public Vehicle() {
    }

    public Vehicle(int inputId, String inputMakeModel, Integer inputModelYear, Double inputRetailPrice) {
        this.id = inputId;
        this.makeModel = inputMakeModel;
        this.year = inputModelYear;
        this.retailPrice = inputRetailPrice;
    }
    public Vehicle(String inputMakeModel, Integer inputModelYear, Double inputRetailPrice) {
        makeModel = inputMakeModel;
        year = inputModelYear;
        retailPrice = inputRetailPrice;
    }
    public void setId(){
        this.id = id;
    }

    /**
     * getter method for id of vehicle
     *
     * @return the id of the vehicle
     */
    public int getId() { return this.id; }

    /**
     * setter method for make and model of vehicle.
     *
     * @param input String, the make and model is set to this input
     */
    public void setMakeModel(String input) {
        makeModel = input;
    }

    /**
     * getter method for make and model of vehicle
     *
     * @return the make of the vehicle
     */
    public String getMakeModel() {
        return makeModel;
    }

    /**
     * setter method for the model year of the vehicle
     *
     * @param input int, the model year of the vehicle is set to this input
     */
    public void setModelYear(int input) {
        year = input;
    }

    /**
     * getter method for model year of vehicle
     *
     * @return the model year of the vehicle
     */
    public int getModelYear() {
        return year;
    }


    /**
     * setter method for reatil price of vehicle
     *
     * @param input int, the retail price is set to this input
     */
    public void setRetailPrice(Double input) {
        this.retailPrice = input;
    }

    /**
     * getter method for retail price
     *
     * @return the retail price of the vehicle
     */
    public Double getRetailPrice() {
        return retailPrice;
    }

}
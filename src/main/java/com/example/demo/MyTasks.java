package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;

@Component
public class MyTasks {
    RestTemplate restTemplate = new RestTemplate();
    /**
     * At some periodic rate, make POST request to add a vehicle.
     * Simply create a vehicle with random values and write it to file.
     * Vehicle Id should start from 1 and increment by 1 each time.
     * Vehicle makeAndModel should be a randomly generated string.
     * Vehicle year should be a random number between 1986-2016.
     * Vehicle retailPrice should be a random number between 15000-45000.
     *
     * I think this is done
     */
    @Scheduled(fixedRate = 7000)
    public void addVehicle() throws IOException {

        String[] makeAndModel = {"Toyota Camry", "Honda Civic", "Toyota Tundra", "Honda Ridgeline"};
        Integer randomYear = new Random().nextInt(31) + 1986;
        Integer randomRetailPrice = new Random().nextInt(30001) + 15000;
        String randomMakeAndModel = makeAndModel[new Random().nextInt(makeAndModel.length)];

        Vehicle vehicle = new Vehicle(randomMakeAndModel, randomYear, (double) randomRetailPrice);

        restTemplate.put("http://localhost:8080/addVehicle/", vehicle);

    }

    /**
     * At some periodic rate, make DELETE request to delete a vehicle.
     * Generate a random id, with some reasonable range (0-100)
     * Then make the DELETE request
     */
    @Scheduled(fixedRate = 7000)
    public void deleteVehicle() {
        try {
            restTemplate.delete("http://localhost:8080/deleteVehicle/" + new Random().nextInt(101));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * At some periodic rate, make PUT request to update a vehicle.
     * Create a new vehicle object with random values
     * Id should be some reasonable random number between 0 – 100
     * The id should be likely to exist in the file already
     * makeAndModel, year, and retail price can be hard-coded with some special
     * values in this case to easily identify that you actually updated the vehicle.
     * After the update, make a GET request on the same id
     * To verify that you actually updated the vehicle
     */
    @Scheduled(fixedRate = 7000)
    public void updateVehicle() {
        // Create new Vehicle
        int someIndex = new Random().nextInt(4);

        String[] makeAndModel = {"Toyota Camry", "Honda Civic", "Toyota Tundra", "Honda Ridgeline"};
        int randomID = new Random().nextInt(100);
        Integer randomYear = new Random().nextInt(31) + 1986;
        Integer randomRetailPrice = new Random().nextInt(30001) + 15000;
        String randomMakeAndModel = makeAndModel[new Random().nextInt(makeAndModel.length)];


        Vehicle newVehicle = new Vehicle(randomID, randomMakeAndModel, randomYear, (double) randomRetailPrice );

        restTemplate.put("http://localhost:8080/updateVehicle/", newVehicle);
        restTemplate.getForObject("http://localhost:8080/getVehicle/"+ randomID, Vehicle.class);

    }

    /**
     * At the top of each hour (ie. 9:00, 10:00, 11:00, etc… for every hour in the day), make a
     * GET request to /getLatestVehicles. The latest 10 vehicles added to the inventory should
     * be printed on the console.
     */
    @Scheduled(cron="0 0 * * * *")
    public void latestVehicleReport(){
        String getUrl = "http://localhost:8080/getLatestVehicle/";
        restTemplate.getForObject(getUrl, Vehicle.class);

    }
}
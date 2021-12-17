package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class restApiVehicles {
    @Autowired
    private VehicleDao vehicleDao;

    /**
     * take in vehicle object and add it to database
     * always appends to database
     *
     * @return
     * @throws IOException
     * @paramewVehicle n
     */

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST, consumes = "application/json")
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDao.create(newVehicle);
        return newVehicle;

    }

    /**
     * take given id and find the vehicle that has the matching id
     * return the vehicle object.
     *
     * @param id id of the vehicle that wants to be found
     * @return vehicle that matches ID
     * @throws IOException throws if there is no vehicle with matching id
     */
    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        if (vehicleDao.getById(id) != null) {
            return vehicleDao.getById(id);
        }
        throw new IOException("Id does not exist.");

    }

    /**
     * updates vehicle
     *
     * @param newVehicle vehicle that wants to be added to database
     * @return the new vehicle
     * @throws IOException if the vehicle does not exist in the first place.
     */

    // TODO: finish return
    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDao.updateVehicle(newVehicle);
        return newVehicle;
    }

    /**
     * takes the given id and deletes from the database.
     *
     * @param id
     * @return ResponseEntity<String>
     * @throws IOException
     */
    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        if (vehicleDao.getById(id) != null) {
            vehicleDao.deleteVehicle(id);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity("Not Found", HttpStatus.BAD_REQUEST);
    }

    /**
     * return the most recent 10 vehicles (as a list) that were added to
     * the database.
     *
     * @return
     * @throws IOException
     */

    // TODO: Do this entire method lol
    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        List<Vehicle> returnList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            returnList.add(vehicleDao.getLatestVehicle());
        }
        if (returnList.size() != 10) {
            System.out.println(new ResponseEntity("Not Enough Entries", HttpStatus.BAD_REQUEST));
        }
        return returnList;
    }
}

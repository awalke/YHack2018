package com.mindorks.demo.OurStuff.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twalke on 3/24/18.
 */

public class ShelterInfo {
    private List<DogProfile> dogs;
    public static ShelterInfo instance = null;

    public ShelterInfo(List<DogProfile> dogs) {
        this.dogs = dogs;
    }

    public ShelterInfo() {
        if (instance == null) {
            instance = new ShelterInfo(new ArrayList<DogProfile>());
        }
    }

    public List<DogProfile> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogProfile> dogs) {
        this.dogs = dogs;
    }

    public void addDog(DogProfile dog) {
        dogs.add(dog);
    }


}

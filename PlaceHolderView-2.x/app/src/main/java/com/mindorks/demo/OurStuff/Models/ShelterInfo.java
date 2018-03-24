package com.mindorks.demo.OurStuff.Models;

import java.util.List;

/**
 * Created by twalke on 3/24/18.
 */

public class ShelterInfo {
    private List<DogProfile> dogs;

    public ShelterInfo(List<DogProfile> dogs) {
        this.dogs = dogs;
    }

    public ShelterInfo() {
    }

    public List<DogProfile> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogProfile> dogs) {
        this.dogs = dogs;
    }


}

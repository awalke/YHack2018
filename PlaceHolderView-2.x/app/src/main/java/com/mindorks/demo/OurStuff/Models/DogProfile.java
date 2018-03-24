package com.mindorks.demo.OurStuff.Models;

/**
 * Created by twalke on 3/24/18.
 */

public class DogProfile {

    private String name;
    private String breed;
    private Integer age;
    private boolean kidFriendly;
    private boolean dogFriendly;
    private boolean catFriendly;
    private ActivityLevel howActive;
    private boolean housetrained;
    private String description;

    public DogProfile() {
    }

    public DogProfile(String name, String breed, Integer age, boolean kidFriendly, boolean dogFriendly, boolean catFriendly, ActivityLevel howActive, boolean housetrained, String description) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.kidFriendly = kidFriendly;
        this.dogFriendly = dogFriendly;
        this.catFriendly = catFriendly;
        this.howActive = howActive;
        this.housetrained = housetrained;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    public boolean isDogFriendly() {
        return dogFriendly;
    }

    public void setDogFriendly(boolean dogFriendly) {
        this.dogFriendly = dogFriendly;
    }

    public boolean isCatFriendly() {
        return catFriendly;
    }

    public void setCatFriendly(boolean catFriendly) {
        this.catFriendly = catFriendly;
    }

    public ActivityLevel getHowActive() {
        return howActive;
    }

    public void setHowActive(ActivityLevel howActive) {
        this.howActive = howActive;
    }

    public boolean isHousetrained() {
        return housetrained;
    }

    public void setHousetrained(boolean housetrained) {
        this.housetrained = housetrained;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DogProfile that = (DogProfile) o;

        if (kidFriendly != that.kidFriendly) return false;
        if (dogFriendly != that.dogFriendly) return false;
        if (catFriendly != that.catFriendly) return false;
        if (housetrained != that.housetrained) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (breed != null ? !breed.equals(that.breed) : that.breed != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (howActive != that.howActive) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (kidFriendly ? 1 : 0);
        result = 31 * result + (dogFriendly ? 1 : 0);
        result = 31 * result + (catFriendly ? 1 : 0);
        result = 31 * result + (howActive != null ? howActive.hashCode() : 0);
        result = 31 * result + (housetrained ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

package com.mindorks.demo.OurStuff.Models;

/**
 * Created by allisonwalke on 3/24/18.
 */

public class Individual {
    private String name;
    private String address;
    private String email;
    private HousingType housing;
    private int kids;
    private int pets;

    public Individual(String name, String address, String email, HousingType housing, int kids, int pets) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.housing = housing;
        this.kids = kids;
        this.pets = pets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HousingType getHousing() {
        return housing;
    }

    public void setHousing(HousingType housing) {
        this.housing = housing;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Individual that = (Individual) o;

        if (kids != that.kids) return false;
        if (pets != that.pets) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return housing == that.housing;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (housing != null ? housing.hashCode() : 0);
        result = 31 * result + kids;
        result = 31 * result + pets;
        return result;
    }


}

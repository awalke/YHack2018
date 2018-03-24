package com.mindorks.demo.OurStuff.Models;

/**
 * Created by twalke on 3/24/18.
 */

public class Shelter {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private OrganizationType organization;
    public static Shelter instance = null;
    int id;

    public Shelter() {
    }

    public Shelter(String name, String email, String password, String phone, String address, OrganizationType organization) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrganizationType getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationType organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelter shelter = (Shelter) o;

        if (name != null ? !name.equals(shelter.name) : shelter.name != null) return false;
        if (email != null ? !email.equals(shelter.email) : shelter.email != null) return false;
        if (password != null ? !password.equals(shelter.password) : shelter.password != null)
            return false;
        if (phone != null ? !phone.equals(shelter.phone) : shelter.phone != null) return false;
        if (address != null ? !address.equals(shelter.address) : shelter.address != null)
            return false;
        return organization == shelter.organization;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }
}

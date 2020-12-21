package org.backend.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class PostAddress {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    @NotEmpty
    private Integer postCode;
    @Column
    @NotEmpty
    private String cityName;
    @Column
    @NotEmpty
    private  String streetName;
    @Column
    @NotEmpty
    private  Integer houseNumber;
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}

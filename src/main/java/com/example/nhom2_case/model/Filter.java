package com.example.nhom2_case.model;

public class Filter {
    private Double minPrice;
    private Double maxPrice;
    private Integer count_bathroom;
    private Integer count_bedroom;
    private Address address;
    private City city;
    private Status status;

    public Filter() {
    }

    public Filter(Double minPrice, Double maxPrice,
                  Integer count_bathroom, Integer count_bedroom,
                  Address address, City city, Status status) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.count_bathroom = count_bathroom;
        this.count_bedroom = count_bedroom;
        this.address = address;
        this.city = city;
        this.status = status;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCount_bathroom() {
        return count_bathroom;
    }

    public void setCount_bathroom(Integer count_bathroom) {
        this.count_bathroom = count_bathroom;
    }

    public Integer getCount_bedroom() {
        return count_bedroom;
    }

    public void setCount_bedroom(Integer count_bedroom) {
        this.count_bedroom = count_bedroom;
    }
}

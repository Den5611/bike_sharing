package com.example.bike_sharing1.model;

import jakarta.persistence.*;

@Entity
public class Bike {
    private String model;
    private boolean available;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @ManyToOne
    private Station station;
    private boolean inUse;

    public Bike() {}

    public Bike(double latitude, double longitude, Station station) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.station = station;
    }
    public Bike(String model) {
    this.model = model;
    this.available = true;
}

    // Getters and setters
    public Long getId() { return id; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public boolean isInUse() { return inUse; }
    public void setInUse(boolean inUse) { this.inUse = inUse; }
        public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @ManyToOne


    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }


}
//package com.example.bike_sharing1.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Bike {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String model;
//
//    private boolean available = true;
//
//    // Конструкторы
//    public Bike() {}
//
//    public Bike(String model) {
//        this.model = model;
//        this.available = true;
//    }
//
//    // Геттеры и сеттеры
//    public Long getId() {
//        return id;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public boolean isAvailable() {
//        return available;
//    }
//
//    public void setAvailable(boolean available) {
//        this.available = available;
//    }
//}


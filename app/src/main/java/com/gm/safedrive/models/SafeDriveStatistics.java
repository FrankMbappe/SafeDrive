package com.gm.safedrive.models;

public class SafeDriveStatistics {
    private double tankAutonomy;

    public SafeDriveStatistics(){

    }
    public SafeDriveStatistics(double tankAutonomy) {
        this.tankAutonomy = tankAutonomy;
    }

    public double getTankAutonomy() {
        return tankAutonomy;
    }

    public void setTankAutonomy(double tankAutonomy) {
        this.tankAutonomy = tankAutonomy;
    }
}

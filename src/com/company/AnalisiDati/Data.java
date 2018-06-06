package com.company.AnalisiDati;

public class Data {
    private final int fatherAge;
    private final int motherAge;
    private final int weekGestation;
    private final int preNatalVisit;
    private final boolean married;
    private final int motherWeightgained;
    private final boolean lowBirthWeight;
    private final Double weight;
    private final boolean primogenito;
    private final boolean fewVisit;

    public Data(int fatherAge, int motherAge, int weekGestation, int preNatalVisit, boolean married, int motherWeightgained, boolean lowBirthWeight, Double weight, boolean primogenito, boolean fewVisit) {
        this.fatherAge = fatherAge;
        this.motherAge = motherAge;
        this.weekGestation = weekGestation;
        this.preNatalVisit = preNatalVisit;
        this.married = married;
        this.motherWeightgained = motherWeightgained;
        this.lowBirthWeight = lowBirthWeight;
        this.weight = weight;
        this.primogenito = primogenito;
        this.fewVisit = fewVisit;
    }

    public int getFatherAge() {
        return fatherAge;
    }

    public int getMotherAge() {
        return motherAge;
    }

    public int getWeekGestation() {
        return weekGestation;
    }

    public int getPreNatalVisit() {
        return preNatalVisit;
    }

    public boolean isMarried() {
        return married;
    }

    public int getMotherWeightgained() {
        return motherWeightgained;
    }

    public boolean isLowBirthWeight() {
        return lowBirthWeight;
    }

    public Double getWeight() {
        return weight;
    }

    public boolean isPrimogenito() {
        return primogenito;
    }

    public boolean isFewVisit() {
        return fewVisit;
    }
}


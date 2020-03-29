package com.demo.app.ncov2020.Logic;

import com.demo.app.ncov2020.Logic.Disease.Disease;
import com.demo.app.ncov2020.Logic.Disease.TypeTrans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Country {
    private final String name;
    private final long amountOfPeople;
    private long deadPeople = 0;
    private long infectedPeople = 0;
    private long heathyPeople;
    private final boolean rich;
    private boolean openAirport;
    private boolean openSeaport;
    private boolean openBorder=true;
    private boolean openSchool = true;
    private boolean infected = false;
    private double cureCoef = 0;
    private List<Country> pathsAir;
    private List<Country> pathsSea;
    private List<Country> pathsGround;

    public Country(String name, long amountOfPeople, boolean rich, boolean openAirport, boolean openSeaport) {
        this.name = name;
        this.amountOfPeople = amountOfPeople;
        this.heathyPeople = amountOfPeople;
        this.rich = rich;
        this.openAirport = openAirport;
        this.openSeaport = openSeaport;
        pathsAir = new ArrayList<>();
        pathsSea = new ArrayList<>();
        pathsGround = new ArrayList<>();
    }

    public Country beginInfection(){
        infected=true;
        heathyPeople--;
        infectedPeople=1;
        return this;
    }

    public void infectAnotherCountryBy(TypeTrans typeTrans){
        switch (typeTrans){
            case AIR: infectAnotherCountryByAir(); break;
            case WATER: infectAnotherCountryByWater(); break;
            case GROUND: infectAnotherCountryByGround(); break;
        }
    }

    public void infectAnotherCountryByGround(){
        if(!openBorder) return;
        Collections.shuffle(pathsGround);
        for(Country country: pathsGround){
            if(country.openBorder && !country.infected){
                country.beginInfection();
                pathsGround.remove(country);
                return;
            }
            else pathsGround.remove(country);
        }
    }

    public void infectAnotherCountryByAir(){
        if(!openAirport) return;
        Collections.shuffle(pathsAir);
        for(Country country: pathsAir){
            if(country.openAirport && !country.infected){
                country.beginInfection();
                pathsAir.remove(country);
                return;
            }
            else pathsAir.remove(country);
        }
    }

    public void infectAnotherCountryByWater(){
        if(!openSeaport) return;
        Collections.shuffle(pathsSea);
        for(Country country: pathsSea){
            if(country.openSeaport && !country.infected){
                country.beginInfection();
                pathsSea.remove(country);
                return;
            }
            else pathsSea.remove(country);
        }
    }


    public void addPathAir(Country country) {
        pathsAir.add(country);
    }

    public void addPathSea(Country country) {
        pathsSea.add(country);
    }

    public void addPathsGround(Country country) {
        pathsGround.add(country);
    }

    public void pastOneUnit(Disease disease) {

    }


    public double getPercentOfInfectedPeople(){
        return (double) infectedPeople/heathyPeople;
    }

    public String getName() {
        return name;
    }

    public long getAmountOfPeople() {
        return amountOfPeople;
    }

    public long getDeadPeople() {
        return deadPeople;
    }

    public void setDeadPeople(long deadPeople) {
        this.deadPeople = deadPeople;
    }

    public long getInfectedPeople() {
        return infectedPeople;
    }

    public void setInfectedPeople(long infectedPeople) {
        this.infectedPeople = infectedPeople;
    }

    public long getHeathyPeople() {
        return heathyPeople;
    }

    public void setHeathyPeople(long heathyPeople) {
        this.heathyPeople = heathyPeople;
    }

    public boolean isRich() {
        return rich;
    }

    public boolean isOpenAirport() {
        return openAirport;
    }

    public void setOpenAirport(boolean openAirport) {
        this.openAirport = openAirport;
    }

    public boolean isOpenSeaport() {
        return openSeaport;
    }

    public void setOpenSeaport(boolean openSeaport) {
        this.openSeaport = openSeaport;
    }

    public boolean isOpenSchool() {
        return openSchool;
    }

    public void setOpenSchool(boolean openSchool) {
        this.openSchool = openSchool;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public double getCureCoef() {
        return cureCoef;
    }

    public void setCureCoef(double cureCoef) {
        this.cureCoef = cureCoef;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                ", deadPeople=" + deadPeople +
                ", infectedPeople=" + infectedPeople +
                ", heathyPeople=" + heathyPeople +
                ", rich=" + rich +
                ", openAirport=" + openAirport +
                ", openSeaport=" + openSeaport +
                ", openSchool=" + openSchool +
                ", infected=" + infected +
                ", cureCoef=" + cureCoef +
                ", pathsAir=" + pathsAir +
                ", pathsSea=" + pathsSea +
                ", pathsGround=" + pathsGround +
                '}';
    }
}
package com.demo.app.ncov2020.logic.Transsmission;

import com.demo.app.ncov2020.logic.BaseHandler;
import com.demo.app.ncov2020.logic.Country.Country;

public class HandlerWater extends BaseHandler {
    @Override
    public void handle(Country country) {
        if(country.isOpenSeaport()){
            double luck = Math.random();
            if (country.getPercentOfInfectedPeople()>0.8 && luck>0.3){
                infectAnotherCountryByWater(country);
            }
            else if (country.getPercentOfInfectedPeople()>0.5 && luck>0.5) {
                infectAnotherCountryByWater(country);
            }
            else if (country.getPercentOfInfectedPeople()>0.3 && luck>0.8) {
                infectAnotherCountryByWater(country);
            }
        }
        delegateNext(country);
    }

    public void infectAnotherCountryByWater(Country baseCountry){
        baseCountry.shufflePathSea();
        for (int i = 0; i < baseCountry.getPathsSea().size(); i++) {
            Country country = baseCountry.getPathsSea().get(i);
            if(country.isOpenSeaport() && !country.isInfected()){
                country.beginInfection();
                baseCountry.removePathSea(country);
                return;
            }
            else {
                baseCountry.removePathSea(country);
                i--;
            }
        }
    }
}

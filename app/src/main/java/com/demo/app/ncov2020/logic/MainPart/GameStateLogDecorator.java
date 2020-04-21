package com.demo.app.ncov2020.logic.MainPart;

import com.demo.app.ncov2020.logic.Abilities.HandlerAntibiotics1;
import com.demo.app.ncov2020.logic.Callback.Callback;
import com.demo.app.ncov2020.logic.Callback.CallbackType;
import com.demo.app.ncov2020.logic.Callback.ConcreateCallback;
import com.demo.app.ncov2020.logic.Callback.GameStateForEntity;
import com.demo.app.ncov2020.logic.Country.Climate;
import com.demo.app.ncov2020.logic.Country.Country;
import com.demo.app.ncov2020.logic.Country.CountryBuilder;
import com.demo.app.ncov2020.logic.Country.CountryComposite;
import com.demo.app.ncov2020.logic.Country.Hronology;
import com.demo.app.ncov2020.logic.Country.MedicineLevel;
import com.demo.app.ncov2020.logic.Disease.Ability;
import com.demo.app.ncov2020.logic.Disease.Disease;
import com.demo.app.ncov2020.logic.Disease.Symptom;
import com.demo.app.ncov2020.logic.Disease.Transmission;
import com.demo.app.ncov2020.logic.Disease.TypeAbility;
import com.demo.app.ncov2020.logic.Disease.TypeTrans;
import com.demo.app.ncov2020.logic.Transsmission.HandlerAIR;
import com.demo.app.ncov2020.logic.Transsmission.HandlerGround;
import com.demo.app.ncov2020.logic.Transsmission.HandlerWater;
import com.demo.app.ncov2020.logic.cure.GlobalCure;

import java.util.ArrayList;


public class GameStateLogDecorator extends BaseDecorator {
    public static GameStateLogDecorator instance;

    public GameStateLogDecorator(ComponentDec componentDec) {
        super(componentDec);
        instance = this;
    }

    public CallbackType pastOneTimeUnit() {
        CallbackType callbackType = super.pastOneTimeUnit();
        System.out.println(callbackType.name());
        return callbackType;
    }

    public void addSymptom(Symptom symptom){
        super.addSymptom(symptom);
        System.out.println(CallbackType.SYMPTOMADD.name());
    }

    public void addTransmission(Transmission transmission){
        super.addTransmission(transmission);
        System.out.println(CallbackType.TRANSADD.name());
    }

    public void addAbility(Ability ability){
        super.addAbility(ability);
        System.out.println(CallbackType.ABILITYADD.name());
    }

}

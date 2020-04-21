package com.demo.app.ncov2020.logic.MainPart;

import com.demo.app.ncov2020.logic.Callback.CallbackType;
import com.demo.app.ncov2020.logic.Disease.Ability;
import com.demo.app.ncov2020.logic.Disease.Symptom;
import com.demo.app.ncov2020.logic.Disease.Transmission;

public abstract class BaseDecorator implements ComponentDec {
    ComponentDec wrappee;

    public BaseDecorator(ComponentDec wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public CallbackType pastOneTimeUnit() {
        return wrappee.pastOneTimeUnit();
    }

    @Override
    public void addSymptom(Symptom symptom) {
        wrappee.addSymptom(symptom);
    }

    @Override
    public void addTransmission(Transmission transmission) {
        wrappee.addTransmission(transmission);
    }

    @Override
    public void addAbility(Ability ability) {
        wrappee.addAbility(ability);
    }

    public ComponentDec getWrappee() {
        return wrappee;
    }

    public void setWrappee(ComponentDec wrappee) {
        this.wrappee = wrappee;
    }
}
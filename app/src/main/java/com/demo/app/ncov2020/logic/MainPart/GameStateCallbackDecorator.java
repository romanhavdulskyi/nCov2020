package com.demo.app.ncov2020.logic.MainPart;

import com.demo.app.ncov2020.logic.Abilities.HandlerAntibiotics1;
import com.demo.app.ncov2020.logic.Callback.Callback;
import com.demo.app.ncov2020.logic.Callback.CallbackType;
import com.demo.app.ncov2020.logic.Callback.ConcreateCallback;
import com.demo.app.ncov2020.logic.Country.CountryComposite;
import com.demo.app.ncov2020.logic.Disease.Ability;
import com.demo.app.ncov2020.logic.Disease.Disease;
import com.demo.app.ncov2020.logic.Disease.Symptom;
import com.demo.app.ncov2020.logic.Disease.Transmission;
import com.demo.app.ncov2020.logic.Disease.TypeAbility;
import com.demo.app.ncov2020.logic.Disease.TypeTrans;
import com.demo.app.ncov2020.logic.Callback.GameStateForEntity;
import com.demo.app.ncov2020.logic.Country.Climate;
import com.demo.app.ncov2020.logic.Country.Country;
import com.demo.app.ncov2020.logic.Country.CountryBuilder;
import com.demo.app.ncov2020.logic.Country.Hronology;
import com.demo.app.ncov2020.logic.Country.MedicineLevel;
import com.demo.app.ncov2020.logic.Transsmission.HandlerAIR;
import com.demo.app.ncov2020.logic.Transsmission.HandlerGround;
import com.demo.app.ncov2020.logic.Transsmission.HandlerWater;
import com.demo.app.ncov2020.logic.cure.GlobalCure;

import java.util.ArrayList;


public class GameStateCallbackDecorator extends BaseDecorator {
    private Callback callback;
    public static GameStateCallbackDecorator instance;

    public GameStateCallbackDecorator(ComponentDec componentDec, Callback callback) {
        super(componentDec);
        this.callback = callback;
        instance = this;
    }
/*This part can be deleted */
    public static GameStateCallbackDecorator init(ComponentDec gameStateReali, Callback callback){
        if (instance != null){
            throw new AssertionError("You already initialized me");
        }
        instance = new GameStateCallbackDecorator(gameStateReali,callback);
        return instance;
    }

    public static GameStateCallbackDecorator getInstance(){
        if(instance==null) throw new RuntimeException("Create one object through init");
        return instance;
    }

    public CallbackType pastOneTimeUnit() {
        CallbackType callbackType = super.pastOneTimeUnit();
        callback.callingBack(new GameStateForEntity(GameStateReali.getInstance()), callbackType);
        return callbackType;
    }

    public void addSymptom(Symptom symptom){
        super.addSymptom(symptom);
        callback.callingBack(new GameStateForEntity(GameStateReali.getInstance()),CallbackType.SYMPTOMADD);
    }

    public void addTransmission(Transmission transmission){
        super.addTransmission(transmission);
        callback.callingBack(new GameStateForEntity(GameStateReali.getInstance()),CallbackType.TRANSADD);
    }

    public void addAbility(Ability ability){
        super.addAbility(ability);
        callback.callingBack(new GameStateForEntity(GameStateReali.getInstance()),CallbackType.ABILITYADD);
    }

    static public void testGameModel(){
        CountryComposite countryComposite= new CountryComposite();
        Country ukraine = new CountryBuilder()
                .setName("Ukraine")
                .setAmountOfPeople(42_000_000)
                .setRich(false)
                .setClimate(Climate.NORMAL)
                .setMedicineLevel(MedicineLevel.FIRST)
                .setHronology(new Hronology(new ArrayList<>()))
                .buildCountry();
        Country italy = new CountryBuilder()
                .setName("Italy")
                .setAmountOfPeople(60_000_000)
                .setRich(true)
                .setClimate(Climate.NORMAL)
                .setMedicineLevel(MedicineLevel.SECOND)
                .setHronology(new Hronology(new ArrayList<>()))
                .buildCountry();
        Country china= new CountryBuilder()
                .setName("China")
                .setAmountOfPeople(1_400_000_000)
                .setRich(false)
                .setClimate(Climate.HOT)
                .setMedicineLevel(MedicineLevel.THIRD)
                .setHronology(new Hronology(new ArrayList<>()))
                .buildCountry();
        Country japan = new CountryBuilder()
                .setName("Japan")
                .setAmountOfPeople(78_000_000)
                .setRich(true)
                .setClimate(Climate.NORMAL)
                .setMedicineLevel(MedicineLevel.SECOND)
                .buildCountry();
        ukraine.addPathAir(china);
        ukraine.addPathSea(italy);
        china.addPathSea(japan);

        countryComposite.addComponent(ukraine);
        countryComposite.addComponent(italy);
        countryComposite.addComponent(china);
        Disease disease = new Disease("nCov2019");
        BaseDecorator baseDecorator = new GameStateLogDecorator(GameStateCallbackDecorator.init(GameStateReali.init(1,"1",countryComposite,disease,new GlobalCure(1000000)),new ConcreateCallback()));
        baseDecorator.addSymptom(new Symptom("Pnevmonia","Hard to breathe",2,4,0));
        baseDecorator.addSymptom(new Symptom("Cough","A-a-a-pchi",2,4,0));
        baseDecorator.addAbility(new Ability("Antibiotics1","Can survive Level1 antibiotics", TypeAbility.ANTIBIOTICS1, new HandlerAntibiotics1()));
        baseDecorator.addTransmission(new Transmission("Plains transmission","You will be able to infect by plains", TypeTrans.AIR,new HandlerAIR()));
        baseDecorator.addTransmission(new Transmission("Tourist transmission","You will be able to infect by tourists", TypeTrans.GROUND,new HandlerGround()));
        baseDecorator.addTransmission(new Transmission("Ship transmission","You will be able to infect by ships", TypeTrans.WATER,new HandlerWater()));
        ukraine.beginInfection();
        for (int i=0;i<50;i++) {
            System.out.println(baseDecorator);
            baseDecorator.pastOneTimeUnit();
        }
        disease.addSymptom(new Symptom("Kill all","People started dying",2,4,2));
        for (int i=0;i<100;i++) {
            System.out.println(baseDecorator);
            baseDecorator.pastOneTimeUnit();
        }
        System.out.println(baseDecorator);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}
package com.demo.app.ncov2020.game

import com.demo.app.ncov2020.common.MapBoxUtils
import com.demo.app.ncov2020.logic.Callback.GameStateForEntity
import com.demo.app.ncov2020.logic.Country.Country
import com.demo.app.ncov2020.logic.Disease.Ability
import com.demo.app.ncov2020.logic.Disease.Disease
import com.demo.app.ncov2020.map.MapCountryData
import java.util.*
import kotlin.collections.HashMap

class Game() {
    var infectedCountryShort: HashMap<String, MapCountryData> = HashMap()
    var infectedCountries: HashMap<String, Country> = HashMap()
    var dateTime: Date? = null
    var upgradePoints: Int? = null
    var disease : Disease? = null

    constructor(gameStateForEntity: GameStateForEntity) : this()
    {
        infectedCountries = gameStateForEntity.infectedCountries
        if(gameStateForEntity.infectedCountries.isNotEmpty())
        {
            for(item in gameStateForEntity.infectedCountries)
                infectedCountryShort[item.key] = (MapCountryData(item.key, MapBoxUtils.getPointsForCountry(item.key), item.value.percentOfInfAndDeadPeople))

        }
        dateTime = gameStateForEntity.date.clone() as Date
        upgradePoints = gameStateForEntity.upgradePointsCalc.upgradePoints
        disease = gameStateForEntity.disease
    }

    override fun toString(): String {
        return "$infectedCountryShort $dateTime $upgradePoints $disease"
    }
}
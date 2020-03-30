package com.demo.app.ncov2020.data.db_data

import com.demo.app.ncov2020.logic.Country
import com.demo.app.ncov2020.logic.Disease.Disease
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

data class GameState(@PrimaryKey var id : Int, var userId : String, var amountOfPeople: Long = 0, var deadPeople: Long = 0, var infectedPeople: Long = 0,
                     var healthyPeople: Long = 0, var countries: RealmList<Country>, var disease: Disease) : RealmObject()
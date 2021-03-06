package com.demo.app.ncov2020.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.demo.app.basics.ViewModelFactory
import com.demo.app.basics.mvvm.BaseAndroidViewModel
import java.lang.reflect.Constructor

class AndroidViewModelFactoryImpl constructor(private val app: Application) : ViewModelFactory {
    override fun createViewModel(className: Class<*>): BaseAndroidViewModel {
        val constructor: Constructor<*> = className.getConstructor(Application::class.java)
        return constructor.newInstance(app) as BaseAndroidViewModel
    }


}
package com.demo.app.ncov2020.gamedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.demo.app.ncov2020.R
import kotlinx.android.synthetic.main.map_fragment.view.*

class DiseaseDialogImpl : DialogFragment(), DiseaseDialog {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.map_fragment, container)

        return rootView
    }

    override fun openDialog(fragmentManager: FragmentManager, tag: String) {
        show(fragmentManager, tag)
    }

}
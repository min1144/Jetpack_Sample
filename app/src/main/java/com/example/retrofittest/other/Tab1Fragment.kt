package com.example.retrofittest.other

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.retrofittest.R
import com.example.retrofittest.databinding.FragmentTab1Binding
import com.example.retrofittest.paging.util.LOG
import kotlinx.android.synthetic.main.fragment_tab_1.*
import kotlinx.android.synthetic.main.fragment_tab_1.view.*

class Tab1Fragment : Fragment() {

    private lateinit var databinding: FragmentTab1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tab_1, container, false)
        databinding.apply {
            lifecycleOwner = this@Tab1Fragment
        }
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
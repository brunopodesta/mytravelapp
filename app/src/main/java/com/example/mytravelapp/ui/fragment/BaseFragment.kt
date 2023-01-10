package com.example.mytravelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.mytravelapp.data.Attraction
import com.example.mytravelapp.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attraction : List<Attraction> get() = (activity as MainActivity).attractions

}
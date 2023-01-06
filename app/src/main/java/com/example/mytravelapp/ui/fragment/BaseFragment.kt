package com.example.mytravelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.mytravelapp.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

}
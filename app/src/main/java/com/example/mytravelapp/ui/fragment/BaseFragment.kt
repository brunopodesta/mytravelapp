package com.example.mytravelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.mytravelapp.data.Attraction
import com.example.mytravelapp.ui.MainActivity
import com.example.mytravelapp.viewmodel.AttractionViewModel

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val activityViewModel : AttractionViewModel
        get() = (activity as MainActivity).viewModel

}
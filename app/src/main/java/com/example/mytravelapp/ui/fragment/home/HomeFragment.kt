package com.example.mytravelapp.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mytravelapp.R
import com.example.mytravelapp.databinding.FragmentHomeBinding
import com.example.mytravelapp.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeFragmentAdapter { attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }
        binding.recyclerview.adapter = homeAdapter

        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            homeAdapter.setData(attractions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
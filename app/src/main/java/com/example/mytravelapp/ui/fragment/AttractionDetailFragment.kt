package com.example.mytravelapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.mytravelapp.R
import com.example.mytravelapp.data.Attraction
import com.example.mytravelapp.databinding.FragmentAttactionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttactionDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionid }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttactionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = attraction.title
        binding.descriptionTextView.text = attraction.description
        Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.factsTextView.text = "${attraction.facts.size} facts"

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        val uri =
                            Uri.parse("geo:${attraction.location.latitude}, " +
                                    "${attraction.location.longitude}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(mapIntent)
                        true
                    }
                    else -> false
                }
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail_attraction, menu)
            }

        }, viewLifecycleOwner, Lifecycle.State.CREATED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
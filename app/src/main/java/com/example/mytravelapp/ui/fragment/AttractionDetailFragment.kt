package com.example.mytravelapp.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.View.OnClickListener
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

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) {
            attraction ->
            binding.titleTextView.text = attraction.title
            binding.descriptionTextView.text = attraction.description
            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.factsTextView.text = "${attraction.facts.size} facts"

            binding.factsTextView.setOnClickListener {

                val stringBuilder = StringBuilder()
                attraction.facts.forEach {
                    stringBuilder.append(it)
                    stringBuilder.append("\n")
                }
                val message = stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n"))

                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("Ok") { dialog, p1 -> dialog.dismiss()}
                    .show()

            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                        activityViewModel.onLocationSelected(attraction)
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
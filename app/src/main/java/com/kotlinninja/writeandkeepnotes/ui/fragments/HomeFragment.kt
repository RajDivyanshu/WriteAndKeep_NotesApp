package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentHomeBinding
import com.kotlinninja.writeandkeepnotes.ui.adapter.NotesAdapter
import com.kotlinninja.writeandkeepnotes.view_model.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    @InternalCoroutinesApi
    val viewModel: NotesViewModel by viewModels()


    @OptIn(InternalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
//observe the live data
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
//            for (i in notesList) {
//                Log.e("@@@", "onCreateView: $i")
//            }
            binding.rvAllNotes.layoutManager=GridLayoutManager(context,2)
            binding.rvAllNotes.adapter= NotesAdapter( requireContext(), notesList)

        })


        //for this button view is 'it'
        // navigate from where to where
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }


}
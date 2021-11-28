package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentCreateNotesBinding


class CreateNotesFragment : Fragment() {

    lateinit var binding:FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        return binding.root
        //inflater.inflate(R.layout.fragment_create_notes, container, false)
    }


}
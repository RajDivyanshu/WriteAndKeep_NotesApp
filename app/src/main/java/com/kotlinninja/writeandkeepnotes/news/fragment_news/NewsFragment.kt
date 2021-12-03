package com.kotlinninja.writeandkeepnotes.news.fragment_news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentEditNotesBinding
import com.kotlinninja.writeandkeepnotes.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

lateinit var binding:FragmentNewsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}
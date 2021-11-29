package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentCreateNotesBinding
import com.kotlinninja.writeandkeepnotes.model.Notes
import com.kotlinninja.writeandkeepnotes.view_model.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi


import java.util.*


@InternalCoroutinesApi
class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"


    @InternalCoroutinesApi
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done) //default set to green
        // clicking on priority button set to done
        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }


        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
        //inflater.inflate(R.layout.fragment_create_notes, container, false)
    }


    @InternalCoroutinesApi
    private fun createNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
//        Log.e("@@@@", "createNotes: $s")

        val data = Notes(
            null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

       // Toast.makeText(this, "Notes created successfully", Toast.LENGTH_SHORT).show( )
    }


}
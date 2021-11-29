package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentEditNotesBinding
import com.kotlinninja.writeandkeepnotes.model.Notes
import com.kotlinninja.writeandkeepnotes.view_model.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*


@InternalCoroutinesApi
class EditNotesFragment : Fragment() {

    // those arguments you will get here
    val preNotes by navArgs<EditNotesFragmentArgs>()
    var priority: String = "1"


    val viewModel: NotesViewModel by viewModels()
    lateinit var binding: FragmentEditNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.edtTitle.setText(preNotes.data.title)
        binding.edtSubtitle.setText(preNotes.data.subTitle)
        binding.edtNotes.setText(preNotes.data.notes)

        when (preNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
        //  return inflater.inflate(R.layout.fragment_edit_notes, container, false)
    }

    private fun updateNotes(it: View?) {


        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
//        Log.e("@@@@", "updateNotes: $title")

        val data = Notes(
            preNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes updated successfully", Toast.LENGTH_SHORT).show()

        // ending the createnotefragment after clicking on save button
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }


}
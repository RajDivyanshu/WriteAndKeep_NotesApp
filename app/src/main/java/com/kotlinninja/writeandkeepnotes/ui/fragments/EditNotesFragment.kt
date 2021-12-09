package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
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
        savedInstanceState: Bundle?,
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Edit Notes")// toolbar title
        //delete button start to show on action bar
        setHasOptionsMenu(true)

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
        val subTitle = binding.edtSubtitle.text.toString()
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

    /** delete menu on action bar */

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      //   here 'item' variable brings all the items from menu
        if (item.itemId == R.id.menuDelete) {
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomStyleSheet)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val txtViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val txtViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            txtViewYes?.setOnClickListener {
                viewModel.deleteNotes(preNotes.data.id!!)  //notNull assertion '!!'
                bottomSheet.dismiss()
                Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)
//                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                transaction?.replace(R.id.createNotesFragment, CreateNotesFragment())
//                transaction?.commit()

            }

            txtViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()

        }

//        val builder = AlertDialog.Builder(requireContext())
//        //set title for alert dialog
//        builder.setTitle("Do you want to Delete it?")
//        //set message for alert dialog
//        builder.setMessage("This will delete permanently")
//        builder.setIcon(android.R.drawable.ic_delete)
//
//        //performing positive action
//        builder.setPositiveButton("Yes") { dialogInterface, which ->
//            viewModel.deleteNotes(preNotes.data.id!!)  //notNull assertion '!!'
//            Toast.makeText(requireContext(), "clicked yes", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)
//
//        }
//        //performing cancel action
//        builder.setNeutralButton("Cancel") { dialogInterface, which ->
////            Toast.makeText(requireContext(), "clicked cancel\n operation cancel", Toast.LENGTH_LONG)
////                .show()
//        }
//        //performing negative action
//        builder.setNegativeButton("No") { dialogInterface, which ->
//
//        }
//        // Create the AlertDialog
//        val alertDialog: AlertDialog = builder.create()
//        // Set other dialog properties
//        alertDialog.setCancelable(false)
//        alertDialog.show()




return super.onOptionsItemSelected(item)
    }




}
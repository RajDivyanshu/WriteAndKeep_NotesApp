package com.kotlinninja.writeandkeepnotes.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.FragmentHomeBinding
import com.kotlinninja.writeandkeepnotes.model.Notes

import com.kotlinninja.writeandkeepnotes.ui.adapter.NotesAdapter
import com.kotlinninja.writeandkeepnotes.view_model.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    @InternalCoroutinesApi
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "My Notes"

        setHasOptionsMenu(true) // for showing search icon on action bar
        staggeredGridLayout()

//observe the live data
        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
//            for (i in notesList) {
//                Log.e("@@@", "onCreateView: $i")
//            }
            oldMyNotes = notesList as ArrayList<Notes>
            adapter=NotesAdapter(requireContext(), notesList)
            binding.rvAllNotes.adapter = adapter

        })


        // getting all notes by clicking on filter btn
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                staggeredGridLayout()
                oldMyNotes = notesList as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }
        //filter button implementation
        // high priority notes
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                staggeredGridLayout()
                oldMyNotes = notesList as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }
        // medium priority notes
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                staggeredGridLayout()
                oldMyNotes = notesList as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }

        // low priority notes
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                staggeredGridLayout()
                oldMyNotes = notesList as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }


        //for this button view is 'it'
        // navigate from where to where
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        /// opens action bar menus
//        when (item.itemId) {
//            // for rating
//            R.id.openNews -> {
//
//            }
//            // share this app
//            R.id.exitApp -> {
//                val alertDialogBuilder = AlertDialog.Builder(requireActivity())
//                alertDialogBuilder.setTitle(R.string.app_name)
//                alertDialogBuilder.setIcon(R.drawable.logo)
//                alertDialogBuilder.setMessage("Are you sure do want to Exit?")
//                alertDialogBuilder.setCancelable(false)
//                alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
//                 //   finish()
//                }
//                alertDialogBuilder.setNegativeButton("No") { _, _ ->
//                }
//                alertDialogBuilder.setNeutralButton("Cancel") { _, _ ->
//                }
//                alertDialogBuilder.create().show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu,menu)
//        val item= menu.getItem(R.id.app_bar_search)
//        val menuAction= it
//        super.onCreateOptionsMenu(menu, inflater)
//    }


    // creating search menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search) // use findItem() instead of getItem()
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter your text here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //  entering data then search

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // searching before entering whole data like searching on text change
                NotesFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
       // Log.e("@@@", "notes Filtering: $p0")

        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(p0!!) || i.subTitle.contains(p0!!)) {
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)
    }

    fun staggeredGridLayout() {
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
    }
}
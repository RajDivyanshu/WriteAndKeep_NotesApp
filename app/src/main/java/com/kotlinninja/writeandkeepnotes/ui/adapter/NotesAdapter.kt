package com.kotlinninja.writeandkeepnotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.databinding.ItemNotesBinding
import com.kotlinninja.writeandkeepnotes.model.Notes
import com.kotlinninja.writeandkeepnotes.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newFilterList: ArrayList<Notes>){
        notesList= newFilterList
        notifyDataSetChanged()
    }

    //itemView: View -> costum viewbinding
    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val data = notesList[notesList.size-position-1]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubtitle.text = data.subTitle.toString()
        holder.binding.notesDate.text = data.date.toString()

        when (data.priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }


        holder.binding.root.setOnClickListener{
            val action= HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            // it send the data form homefragment to edithomefragment
            // before this we have to make model class Parcelable
            //then add parcelable from nav_graph
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}
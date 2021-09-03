package com.example.todolist.ui.fragments

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.room.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.item_task.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListAdapterViewHolder>() {

    private var taskList = emptyList<Task>()

    class ListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ListAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapterViewHolder, position: Int) {
        val task = taskList[position]
        val date = "${task.date} ${task.hour}"
        holder.itemView.tv_title.setText(task.title).toString()
        holder.itemView.tv_date.setText(date).toString()
        holder.itemView.tv_description.setText(task.description).toString()
    }


    override fun getItemCount(): Int = taskList.size

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}
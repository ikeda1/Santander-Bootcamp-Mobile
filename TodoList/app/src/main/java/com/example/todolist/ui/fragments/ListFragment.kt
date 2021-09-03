package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.ui.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_task.view.*

class ListFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.rv_task_list
        recyclerView.adapter = adapter

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllTasks.observe(viewLifecycleOwner, { task ->
            adapter.setData(task)
            if (task.isNotEmpty()) {
                view.rv_task_list.visibility = View.VISIBLE
                view.include_empty_state.visibility = View.GONE
            } else if (task.isEmpty()) {
                view.include_empty_state.visibility = View.VISIBLE
            }
        })

        listeners(view)
        setHasOptionsMenu(true)
        return view
    }

    private fun listeners(view: View) {

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_list)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        view.fab.setOnClickListener {
            findNavController().navigate(R.id.listFragment_to_addFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_delete_item -> {
                deleteAll()
            }
            R.id.missile_item -> {
                Toast.makeText(
                    requireContext(),
                    "FIRE ZE MISSILES!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        mTaskViewModel.readAllTasks.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.label_no_tasks),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val alertDialog = MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.label_dialog_title_delete_all))
                    .setMessage(getString(R.string.label_dialog_message_delete_all))

                alertDialog.setNegativeButton("Não") {_, _ ->}
                alertDialog.setPositiveButton("Sim") {_, _ ->
                    mTaskViewModel.deleteAll()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.label_toast_all_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                alertDialog.show()
            }
        })
    }


}
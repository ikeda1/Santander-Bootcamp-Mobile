package com.example.todolist.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.extensions.format
import com.example.todolist.room.Task
import com.example.todolist.ui.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.*

class UpdateFragment : Fragment(){

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        listeners(view)
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_delete_item -> {
                deleteUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun listeners(view: View) {

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_update)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        view.til_date.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                til_date.editText?.setText(Date(it + offset).format()).toString()
            }
            datePicker.show(childFragmentManager, "tag")
        }

        view.til_time.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val time = "$hour:minute"
                til_time.editText?.setText(time)
            }
            timePicker.show(childFragmentManager, "tag")
        }

        view.til_title.editText?.setText(args.currentTask.title).toString()
        view.til_description.editText?.setText((args.currentTask.description).toString())
        view.til_date.editText?.setText((args.currentTask.date).toString())
        view.til_time.editText?.setText((args.currentTask.hour).toString())

        view.btn_update_task.setOnClickListener {
            updateItem()
        }

        view.btn_update_cancel.setOnClickListener {
            findNavController().navigate(R.id.updateFragment_to_listfragment)
        }
    }

    private fun updateItem() {
        val title = til_title.editText?.text.toString()
        val description = til_description.editText?.text.toString()
        val date = til_date.editText?.text.toString()
        val hour = til_time.editText?.text.toString()

        if (inputCheck(title, description, date, hour)) {
            val updateTask = Task(title, description, hour, date, args.currentTask.id)
            mTaskViewModel.updateTask(updateTask)
            Toast.makeText(
                requireContext(),
                getString(R.string.label_task_updated),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.updateFragment_to_listfragment)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.label_title_missing),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun deleteUser() {
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.label_dialog_title_delete))
            .setMessage(getString(R.string.label_dialog_message_delete))

        alertDialog.setNegativeButton(getString(R.string.label_no)) { _, _ ->}
        alertDialog.setPositiveButton(getString(R.string.label_yes)) { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                getString(R.string.label_toast_task_deleted),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.updateFragment_to_listfragment)
        }
        alertDialog.show()
    }

    private fun inputCheck(
        title: String,
        description: String,
        date: String,
        hour: String
    ): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(
            date
        ) || TextUtils.isEmpty(hour))

    }

}
package com.example.todolist.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.extensions.format
import com.example.todolist.room.Task
import com.example.todolist.ui.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        listeners(view)
        setHasOptionsMenu(true)

        return view
    }

    private fun listeners(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_add)
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
                val time = "$hour:$minute"
                til_time.editText?.setText(time)
            }
            timePicker.show(childFragmentManager, "tag")
        }

        view.btn_new_task.setOnClickListener {
            insertDataInDB()
        }

        view.btn_cancel_create.setOnClickListener {
            findNavController().navigate(R.id.addFragment_to_listFragment)
        }
    }

    private fun insertDataInDB() {
        val title = til_title.editText?.text.toString()
        val description = til_description.editText?.text.toString()
        val date = til_date.editText?.text.toString()
        val hour = til_time.editText?.text.toString()

        if(!(TextUtils.isEmpty(title))) {
            val task = Task(title, description, hour, date, 0)
            mTaskViewModel.addTask(task)

            Toast.makeText(
                requireContext(),
                getString(R.string.label_task_added),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.addFragment_to_listFragment)

        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.label_title_missing),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

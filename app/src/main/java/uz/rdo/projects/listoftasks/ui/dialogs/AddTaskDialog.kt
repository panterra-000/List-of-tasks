package uz.rdo.projects.listoftasks.ui.dialogs

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.DialogAddTaskBinding
import uz.rdo.projects.listoftasks.utils.SingleBlock

class AddTaskDialog(private val activity: Activity) :
    AlertDialog(activity) {

    private var _binding: DialogAddTaskBinding? = null
    private val binding: DialogAddTaskBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")


    private var editable: Boolean = false

    private var listenClick: SingleBlock<TaskModel>? = null


    init {
        _binding = DialogAddTaskBinding.inflate(layoutInflater)
        setView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadViews()
    }

    private fun loadViews() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnAdd.setOnClickListener {
                // TODO: 19.02.2021
                dismiss()
            }

        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

    fun setOnclickSaveCallback(f: SingleBlock<TaskModel>) {
        listenClick = f
    }


}
package uz.rdo.projects.listoftasks.ui.dialogs

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.DialogAddTaskBinding
import uz.rdo.projects.listoftasks.utils.SingleBlock
import uz.rdo.projects.listoftasks.utils.time.getCurrentDateTime
import uz.rdo.projects.listoftasks.utils.time.getMilliseconds
import uz.rdo.projects.listoftasks.utils.time.toMyString

@RequiresApi(Build.VERSION_CODES.O)
class AddTaskDialog(private val activity: Activity) :
    AlertDialog(activity) {

    private var _binding: DialogAddTaskBinding? = null
    private val binding: DialogAddTaskBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private lateinit var taskModel: TaskModel

    private var editable: Boolean = false

    private var listenClick: SingleBlock<TaskModel>? = null

    init {
        _binding = DialogAddTaskBinding.inflate(layoutInflater)
        setView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadViews() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnAdd.setOnClickListener {
                var day = datePicker.dayOfMonth
                var moth = datePicker.month
                var year = datePicker.year

                var deadlineDate = "$day/0${moth + 1}/$year"

                taskModel = TaskModel(
                    id = 0,
                    title = etTaskTitle.text.toString(),
                    desc = etTaskDesc.text.toString(),
                    completedPercent = 0F,
                    date = getCurrentDateTime(),
                    deadline = datePicker.getMilliseconds(),
                    status = "in_progress"
                )

                listenClick?.invoke(taskModel)
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
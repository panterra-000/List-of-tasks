package uz.rdo.projects.listoftasks.ui.dialogs

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.DialogUpdateTaskBinding
import uz.rdo.projects.listoftasks.utils.SingleBlock
import uz.rdo.projects.listoftasks.utils.time.getCurrentDateTime
import uz.rdo.projects.listoftasks.utils.time.getMilliseconds
import uz.rdo.projects.listoftasks.utils.time.toMyString

@RequiresApi(Build.VERSION_CODES.O)
class EditTaskDialog(private val activity: Activity, private val taskModel: TaskModel) :
    AlertDialog(activity) {

    private var _binding: DialogUpdateTaskBinding? = null
    private val binding: DialogUpdateTaskBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private var editable: Boolean = false

    private var listenClick: SingleBlock<TaskModel>? = null


    init {
        _binding = DialogUpdateTaskBinding.inflate(layoutInflater)
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

            etTaskTitle.setText(taskModel.title)
            etTaskDesc.setText(taskModel.desc)
            txtOldDeadline.text = "${taskModel.deadline}"

            btnEdit.setOnClickListener {
                var day = datePicker.dayOfMonth
                var moth = datePicker.month
                var year = datePicker.year

                var deadlineDate = "$day/0${moth + 1}/$year"

                var editedTaskModel = TaskModel(
                    id = taskModel.id,
                    title = etTaskTitle.text.toString(),
                    desc = etTaskDesc.text.toString(),
                    completedPercent = taskModel.completedPercent,
                    date = getCurrentDateTime(),
                    deadline = datePicker.getMilliseconds(),
                    status = "${taskModel.status}"
                )

                listenClick?.invoke(editedTaskModel)
                dismiss()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

    fun setOnclickEditCallback(f: SingleBlock<TaskModel>) {
        listenClick = f
    }


}
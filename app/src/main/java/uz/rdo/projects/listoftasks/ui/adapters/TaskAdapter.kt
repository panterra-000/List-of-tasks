package uz.rdo.projects.listoftasks.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.app.App
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.TaskItemBinding
import uz.rdo.projects.listoftasks.utils.DoubleBlock
import kotlin.random.Random

class TaskAdapter : ListAdapter<TaskModel, TaskAdapter.MyHolder>(DIFF_SEARCH_CALLBACK) {

    companion object {
        var DIFF_SEARCH_CALLBACK = object : DiffUtil.ItemCallback<TaskModel>() {
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.hashCode() == oldItem.hashCode()

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.title == oldItem.title
        }
    }

    lateinit var popupMenu: PopupMenu

    private var listenClickPopupMenu: DoubleBlock<TaskModel, Int>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder = MyHolder(
        TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind()

    inner class MyHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskModel = getItem(adapterPosition)
            binding.txtTaskTitle.text = taskModel.title
            binding.txtTaskDesc.text = taskModel.desc
            binding.txtDateOf.text = taskModel.date
            binding.txtDeadlineOf.text = taskModel.deadline
            if (taskModel.status == "completed") {
                binding.pbPercent.progress = 100
                binding.iconStatus.setImageResource(R.drawable.done_image)
            } else {
                val random = Random(100)
                binding.pbPercent.progress = random.nextInt()
                binding.iconStatus.setImageResource(R.drawable.progress_image)
            }

            binding.imgMore.setOnClickListener {
                showPopupMenu(getItem(adapterPosition), binding.imgMore)
            }


        }
    }

    private fun showPopupMenu(selectedTaskModel: TaskModel, view: View) {
        popupMenu = PopupMenu(App.instance, view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            listenClickPopupMenu?.invoke(selectedTaskModel, it.itemId)

            true
        }
        popupMenu.show()

    }

    fun setOnclickPopupMenuCallback(f: DoubleBlock<TaskModel, Int>) {
        listenClickPopupMenu = f
    }

}
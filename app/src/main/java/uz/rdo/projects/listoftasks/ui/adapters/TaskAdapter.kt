package uz.rdo.projects.listoftasks.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.TaskItemBinding

class TaskAdapter : ListAdapter<TaskModel, TaskAdapter.MyHolder>(DIFF_SEARCH_CALLBACK) {

    companion object {
        var DIFF_SEARCH_CALLBACK = object : DiffUtil.ItemCallback<TaskModel>() {
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.hashCode() == oldItem.hashCode()

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.title == oldItem.title
        }
    }

    inner class MyHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskModel = getItem(adapterPosition)
            binding.txtTaskTitle.text = taskModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder = MyHolder(
        TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind()
}
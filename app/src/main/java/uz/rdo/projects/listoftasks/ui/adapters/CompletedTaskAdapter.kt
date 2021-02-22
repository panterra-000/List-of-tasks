package uz.rdo.projects.listoftasks.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.app.App
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.CompletedTaskItemBinding
import uz.rdo.projects.listoftasks.utils.DoubleBlock
import uz.rdo.projects.listoftasks.utils.time.convertLongToTime

class CompletedTaskAdapter :
    ListAdapter<TaskModel, CompletedTaskAdapter.MyHolder>(DIFF_SEARCH_CALLBACK) {

    lateinit var popupMenu: PopupMenu
    private var listenClickPopupMenu: DoubleBlock<TaskModel, Int>? = null


    companion object {
        var DIFF_SEARCH_CALLBACK = object : DiffUtil.ItemCallback<TaskModel>() {
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.hashCode() == oldItem.hashCode()

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel) =
                newItem.title == oldItem.title
        }
    }

    inner class MyHolder(private val binding: CompletedTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val taskModel = getItem(adapterPosition)
            binding.txtTaskTitle.text = taskModel.title
            binding.txtTaskDesc.text = taskModel.desc
            binding.txtDateOf.text = convertLongToTime(taskModel.date)

            binding.imgMore.setOnClickListener {
                showPopupMenu(getItem(adapterPosition), binding.imgMore)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder = MyHolder(
        CompletedTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind()

    private fun showPopupMenu(selectedTaskModel: TaskModel, view: View) {
        popupMenu = PopupMenu(App.instance, view)
        popupMenu.inflate(R.menu.popup_menu_completed)
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
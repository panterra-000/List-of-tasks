package uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.ui.mainactivity.MainActivity
import uz.rdo.projects.listoftasks.databinding.FragmentInProgressBinding
import uz.rdo.projects.listoftasks.ui.adapters.InProgressTaskAdapter
import uz.rdo.projects.listoftasks.ui.dialogs.EditTaskDialog
import uz.rdo.projects.listoftasks.ui.fragments.completedtasks.CompletedTaskViewModel
import uz.rdo.projects.listoftasks.utils.extensions.format
import uz.rdo.projects.listoftasks.utils.extensions.showToast

@AndroidEntryPoint
class InProgressFragment : Fragment() {

    private val viewModel: InProgressTaskViewModel by viewModels()

    private var _binding: FragmentInProgressBinding? = null
    private val binding: FragmentInProgressBinding
        get() = _binding ?: throw NullPointerException("view is not available")

    private var adapter = InProgressTaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeToolBarMode(2)
        viewModel.getAllTasks()
        viewModel.getAllInProgressTasks()
        loadViews()
        loadObservers()
        setupAdapterCallBack()
    }

    private fun loadViews() {
        adapter.submitList(listOf())
        binding.rvProgressTasks.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProgressTasks.adapter = adapter
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        viewModel.allInProgressTasks.observe(this, allInProgressTasksObserver)
        viewModel.delete.observe(this, deleteObserver)
        viewModel.update.observe(this, updateObserver)
        viewModel.allTasks.observe(this, getAllTasksObserver)
    }

    private val allInProgressTasksObserver = Observer<List<TaskModel>> { taskModels ->
        adapter.submitList(taskModels)
    }

    private val getAllTasksObserver = Observer<List<TaskModel>> { taskModels ->
        setProgressBarAndNumbersTxtUI(taskModels)
    }

    private val deleteObserver = Observer<Boolean> {
        showToast("Успешно удален")
    }

    private val updateObserver = Observer<Boolean> {
        viewModel.getAllInProgressTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setProgressBarAndNumbersTxtUI(tasks: List<TaskModel>) {
        var progress: Float = 0.0F
        var allTasksCount: Int = 0
        var inProgressTasksCount: Int = 0

        for ((count, a) in tasks.withIndex()) {
            if (a.completedPercent == 0F) {
                inProgressTasksCount++
            }
            allTasksCount = count + 1
        }

        progress = (100F * inProgressTasksCount) / allTasksCount

        binding.circularProgressBar.progress = progress
        binding.txtNumberAll.text = "$allTasksCount"
        binding.txtNumberInProgress.text = "$inProgressTasksCount"
        binding.txtPercent.text = "${progress.format(1)}%"

        if (allTasksCount == null) {
            binding.txtPercent.text = "0.0%"
            binding.circularProgressBar.progress = 0F
        }
    }

    private fun setupAdapterCallBack() {
        adapter.setOnclickPopupMenuCallback { taskModel, i ->
            when (i) {
                R.id.pop_done -> {
                    taskModel.status = "completed"
                    taskModel.completedPercent = 100F
                    viewModel.updateTask(taskModel)
                }
                R.id.pop_edit -> {
                    updateTaskWithDialog(taskModel)
                }
                R.id.pop_delete -> {
                    viewModel.deleteTask(taskModel)
                }
            }
        }
    }

    private fun updateTaskWithDialog(taskModel: TaskModel) {
        val dialog = EditTaskDialog(requireActivity(), taskModel)
        dialog.show()
        dialog.setOnclickEditCallback { newTaskModel ->
            viewModel.updateTask(newTaskModel)
        }

    }
}
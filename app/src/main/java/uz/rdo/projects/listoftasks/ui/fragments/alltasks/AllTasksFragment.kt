package uz.rdo.projects.listoftasks.ui.fragments.alltasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.ui.mainactivity.MainActivity
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.FragmentAllBinding
import uz.rdo.projects.listoftasks.ui.adapters.TaskAdapter
import uz.rdo.projects.listoftasks.utils.extensions.format
import uz.rdo.projects.listoftasks.utils.extensions.showToast

@AndroidEntryPoint
class AllTasksFragment : Fragment() {

    private val viewModel: AllTasksViewModel by viewModels()
    private var _binding: FragmentAllBinding? = null
    private val binding: FragmentAllBinding
        get() = _binding ?: throw NullPointerException("view is not available")

    private var adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeToolBarMode(0)
        viewModel.getAllTasks()
        loadObservers()
        loadViews()
        loadMainCallBack()
        setupAdapterCallBack()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun loadObservers() {
        viewModel.allTasks.observe(this, allTasksObserver)
        viewModel.delete.observe(this, deleteObserver)
        viewModel.update.observe(this, updateObserver)
    }


    private val allTasksObserver = Observer<List<TaskModel>> { taskModels ->
        adapter.submitList(taskModels)
        setProgressBarAndNumbersTxtUI(taskModels)
    }


    private val deleteObserver = Observer<Boolean> {
        showToast("Успешно удален")
    }

    private val updateObserver = Observer<Boolean> {
        viewModel.getAllTasks()
        showToast("Ок")

    }

    private fun loadViews() {
        adapter.submitList(listOf())
        binding.rvAllTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllTasks.adapter = adapter
        binding.circularProgressBar.animate()
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
                    // TODO: 20.02.2021 """"""open edit_dialog ->

                }
                R.id.pop_delete -> {
                    viewModel.deleteTask(taskModel)

                }
            }
        }
    }

    private fun loadMainCallBack() {
        (requireActivity() as MainActivity).setAddTaskCallback {
            viewModel.getAllTasks()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setProgressBarAndNumbersTxtUI(tasks: List<TaskModel>) {
        var progress: Float = 0.0F
        var allTasksCount: Int = 0
        var completedTaskCount: Int = 0
        var inProgressTasksCount: Int = 0

        for ((count, a) in tasks.withIndex()) {
            if (a.completedPercent == 100F) {
                completedTaskCount++
            }
            allTasksCount = count + 1
        }

        progress = (100F * completedTaskCount) / allTasksCount
        inProgressTasksCount = allTasksCount - completedTaskCount

        binding.circularProgressBar.progress = progress
        binding.txtNumberAll.text = "$allTasksCount"
        binding.txtNumberCompleted.text = "$completedTaskCount"
        binding.txtNumberProgress.text = "$inProgressTasksCount"
        binding.txtPercent.text = "${progress.format(1)}%"

        if (allTasksCount == null) {
            binding.txtPercent.text = "0.0%"
            binding.circularProgressBar.progress = 0F
        }

    }


}
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
        // viewModel.updateToDone.observe()
    }


    private val allTasksObserver = Observer<List<TaskModel>> { taskModels ->
        adapter.submitList(taskModels)
    }


    private val deleteObserver = Observer<Boolean> {
        showToast("успешно удален")
    }


    private fun loadViews() {
        adapter.submitList(listOf())
        binding.rvAllTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllTasks.adapter = adapter

    }

    private fun setupAdapterCallBack() {
        adapter.setOnclickPopupMenuCallback { taskModel, i ->
            when (i) {
                R.id.pop_done -> {
                    viewModel.updateTaskToDone(taskModel)
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


}
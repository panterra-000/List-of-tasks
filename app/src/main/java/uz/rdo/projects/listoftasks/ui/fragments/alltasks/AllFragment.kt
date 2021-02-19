package uz.rdo.projects.listoftasks.ui.fragments.alltasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.ActivityMainBinding
import uz.rdo.projects.listoftasks.databinding.FragmentAllBinding
import uz.rdo.projects.listoftasks.ui.adapters.TaskAdapter

@AndroidEntryPoint
class AllFragment : Fragment() {


    private var _binding: FragmentAllBinding? = null
    private val binding: FragmentAllBinding
        get() = _binding ?: throw NullPointerException("view is not available")

    private var adapter = TaskAdapter()
    private var list: ArrayList<TaskModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews()
    }

    private fun loadViews() {
        list.add(TaskModel(1, "Task-1"))
        list.add(TaskModel(2, "Task-2"))
        list.add(TaskModel(3, "Task-3"))
        list.add(TaskModel(4, "Task-4"))
        list.add(TaskModel(5, "Task-5"))

        adapter.submitList(list)
        binding.rvAllTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllTasks.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
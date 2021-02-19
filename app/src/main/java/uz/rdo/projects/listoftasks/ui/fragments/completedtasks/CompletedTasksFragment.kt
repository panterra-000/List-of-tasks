package uz.rdo.projects.listoftasks.ui.fragments.completedtasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.ui.MainActivity
import uz.rdo.projects.listoftasks.databinding.FragmentCompletedBinding

@AndroidEntryPoint
class CompletedTasksFragment : Fragment() {

    private var _binding: FragmentCompletedBinding? = null
    private val binding: FragmentCompletedBinding
        get() = _binding ?: throw NullPointerException("view is not available")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeToolBarMode(1)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
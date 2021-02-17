package uz.rdo.projects.listoftasks.ui.fragments.performedtasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.databinding.FragmentInProgressBinding
import uz.rdo.projects.listoftasks.databinding.FragmentPerformedBinding

class PerformedFragment : Fragment() {

    private var _binding: FragmentPerformedBinding? = null
    private val binding: FragmentPerformedBinding
        get() = _binding ?: throw NullPointerException("view is not available")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerformedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
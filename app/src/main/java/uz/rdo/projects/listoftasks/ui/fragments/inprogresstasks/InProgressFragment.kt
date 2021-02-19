package uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.ui.MainActivity
import uz.rdo.projects.listoftasks.databinding.FragmentAllBinding
import uz.rdo.projects.listoftasks.databinding.FragmentInProgressBinding

@AndroidEntryPoint
class InProgressFragment : Fragment() {

    private var _binding: FragmentInProgressBinding? = null
    private val binding: FragmentInProgressBinding
        get() = _binding ?: throw NullPointerException("view is not available")


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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
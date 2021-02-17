package uz.rdo.projects.listoftasks.ui.fragments.alltasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.databinding.ActivityMainBinding
import uz.rdo.projects.listoftasks.databinding.FragmentAllBinding

@AndroidEntryPoint
class AllFragment : Fragment() {


    private var _binding: FragmentAllBinding? = null
    private val binding: FragmentAllBinding
        get() = _binding ?: throw NullPointerException("view is not available")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(layoutInflater, container, false)
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
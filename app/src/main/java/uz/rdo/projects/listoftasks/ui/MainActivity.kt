package uz.rdo.projects.listoftasks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.databinding.ActivityMainBinding
import uz.rdo.projects.listoftasks.ui.dialogs.AddTaskDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw NullPointerException("view is not available")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadViews()
    }

    private fun loadViews() {
        val navController = findNavController(R.id.fragment)
        binding.bottomMenuNav.setupWithNavController(navController)
        binding.imgToolbarAdd.setOnClickListener {
            val dialog = AddTaskDialog(this)
            dialog.show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun changeToolBarMode(fragmentPos: Int) {
        when (fragmentPos) {
            0 -> {
                binding.txtTitleToolbar.text = resources.getString(R.string.all_tasks_title)
            }
            1 -> {
                binding.txtTitleToolbar.text = resources.getString(R.string.completed_tasks)
            }
            2 -> {
                binding.txtTitleToolbar.text = resources.getString(R.string.in_progress_tasks)
            }
        }
    }

}


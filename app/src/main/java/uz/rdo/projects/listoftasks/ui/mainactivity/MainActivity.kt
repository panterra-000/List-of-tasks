package uz.rdo.projects.listoftasks.ui.mainactivity

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AlertDialogLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.rdo.projects.listoftasks.R
import uz.rdo.projects.listoftasks.app.App
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.databinding.ActivityMainBinding
import uz.rdo.projects.listoftasks.ui.dialogs.AddTaskDialog
import uz.rdo.projects.listoftasks.ui.dialogs.DeleteAllDialog
import uz.rdo.projects.listoftasks.utils.EmptyBlock
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        public var dLocale: Locale? = null
    }


    private val viewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw NullPointerException("view is not available")

    private var listenAddTaskClick: EmptyBlock? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadObservers()
        loadViews()
    }

    private fun loadObservers() {
        viewModel.resultLiveData.observe(this, resultObserver)
        viewModel.deleteAllTasksData.observe(this, deleteAllObserver)
    }

    private val resultObserver = Observer<Boolean> {
        listenAddTaskClick?.invoke()
    }

    private val deleteAllObserver = Observer<Boolean> {
        listenAddTaskClick?.invoke()
    }

    private fun loadViews() {
        val navController = findNavController(R.id.fragment)
        binding.bottomMenuNav.setupWithNavController(navController)
        binding.imgToolbarAdd.setOnClickListener {
            val dialog = AddTaskDialog(this)
            dialog.show()
            dialog.setOnclickSaveCallback { newTaskModel ->
                viewModel.addTaskModelToDB(newTaskModel)
            }
        }
        binding.imgToolbarMenu.setOnClickListener {
            showPopupMenu(binding.imgToolbarMenu)
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

    fun setAddTaskCallback(f: EmptyBlock) {
        listenAddTaskClick = f
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(App.instance, view)
        popupMenu.inflate(R.menu.main_popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.popup_delete_all -> {
                    val dialog = DeleteAllDialog(this)
                    dialog.show()
                    dialog.setOnclickDeleteAllCallback {
                        viewModel.deleteAllTasks()
                    }
                }
                R.id.popup_change_language -> {

                }
            }
            true
        }
        popupMenu.show()

    }

    fun updateConfig(wrapper: ContextThemeWrapper) {
        if(dLocale==Locale("") ) // Do nothing if dLocale is null
            return

        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }


}


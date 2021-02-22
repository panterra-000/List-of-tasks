package uz.rdo.projects.listoftasks.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import uz.rdo.projects.listoftasks.databinding.DialogDeleteAllBinding
import uz.rdo.projects.listoftasks.utils.EmptyBlock

class DeleteAllDialog(private val activity: Activity) : AlertDialog(activity) {

    private var _binding: DialogDeleteAllBinding? = null
    private val binding: DialogDeleteAllBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private var listenClick: EmptyBlock? = null


    init {
        _binding = DialogDeleteAllBinding.inflate(layoutInflater)
        setView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadViews()
    }

    private fun loadViews() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnOkDelete.setOnClickListener {
            listenClick?.invoke()
            dismiss()
        }
    }

    fun setOnclickDeleteAllCallback(f: EmptyBlock) {
        listenClick = f
    }

}
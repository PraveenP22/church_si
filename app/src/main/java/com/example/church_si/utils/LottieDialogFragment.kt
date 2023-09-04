package com.example.church_si.utils

import android.R
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.church_si.databinding.DialogLottieBinding

class LottieDialogFragment : DialogFragment() {

    private lateinit var binding: DialogLottieBinding;




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLottieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, R.style.Theme)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (dialog != null) {
            dialog.dismiss()
        }
    }

    companion object {
        fun newInstance(): LottieDialogFragment? {
            val args = Bundle()
            val fragment = LottieDialogFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

}
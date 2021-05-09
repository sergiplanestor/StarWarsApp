package com.revolhope.presentation.library.components.snackbar.view

import android.content.Context
import android.util.AttributeSet
import com.revolhope.mylibra.databinding.ComponentSnackbarSuccessViewBinding
import com.revolhope.presentation.library.components.snackbar.model.SnackBarModel
import com.revolhope.presentation.library.extensions.layoutInflater

class SnackBarSuccessView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SnackBarView<SnackBarModel.Success>(context, attrs, defStyleAttr) {

    private val binding = ComponentSnackbarSuccessViewBinding.inflate(
        context.layoutInflater,
        this,
        true
    ).also { super.contentLayout = it.contentLayout }

    override fun bind(model: SnackBarModel.Success) {
        binding.message.text = model.message
    }
}

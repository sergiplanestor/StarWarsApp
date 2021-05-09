package com.revolhope.presentation.library.components.snackbar.view

import android.content.Context
import android.util.AttributeSet
import com.revolhope.mylibra.databinding.ComponentSnackbarErrorViewBinding
import com.revolhope.presentation.library.components.snackbar.model.SnackBarModel
import com.revolhope.presentation.library.extensions.layoutInflater

class SnackBarErrorView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SnackBarView<SnackBarModel.Error>(context, attrs, defStyleAttr) {

    private val binding = ComponentSnackbarErrorViewBinding.inflate(
        context.layoutInflater,
        this,
        true
    ).also { super.contentLayout = it.contentLayout }

    override fun bind(model: SnackBarModel.Error) {
        binding.message.text = model.message
    }
}

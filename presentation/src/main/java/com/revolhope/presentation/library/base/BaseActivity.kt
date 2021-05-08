package com.revolhope.presentation.library.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.revolhope.mylibra.R
import com.revolhope.presentation.library.components.loader.LoaderView
import com.revolhope.presentation.library.components.snackbar.SnackBar
import com.revolhope.presentation.library.components.snackbar.model.SnackBarModel

abstract class BaseActivity : AppCompatActivity() {

    protected enum class NavTransition {
        LATERAL,
        MODAL
    }

    companion object {
        const val EXTRA_NAVIGATION_TRANSITION = "nav.transition"
    }

    private lateinit var root: View
    private val loader: LoaderView? by lazy { root.findViewById(R.id.loader_view) }

    abstract fun inflateView(): View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inflateView().also { root = it })
        bindViews()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        onLoadData()
    }

    override fun onPause() {
        super.onPause()
        onSaveChanges()
    }

    open fun bindViews() {
        // Nothing to do here
    }

    open fun initObservers() {
        // Nothing to do here
    }

    open fun onLoadData() {
        // Nothing to do here
    }

    open fun onSaveChanges() {
        // Nothing to do here
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overrideTransition()
    }

    override fun startActivity(intent: Intent?) {
        val anim = getNavAnimations(intent)
        super.startActivity(intent)
        overridePendingTransition(anim.first, anim.second)
    }

    /**
     * Private util method to obtain animation to perform when changing between activities.
     * @param intent [Intent] object to obtain extra param and know which [NavTransition] should be executed.
     * @param isStart [Boolean] indicates if this animation is from starting new activity or if it is from
     * going back from.
     *
     * @return [Pair] object containing animations to perform. [Pair.first] animation resource is for current activity,
     * [Pair.second] animation resource is for incoming activity.
     */
    private fun getNavAnimations(intent: Intent?, isStart: Boolean = true): Pair<Int, Int> {
        val bundle = intent?.extras
        return when (bundle?.getSerializable(EXTRA_NAVIGATION_TRANSITION) as NavTransition?) {
            NavTransition.LATERAL ->
                if (isStart) {
                    bundle?.putSerializable(EXTRA_NAVIGATION_TRANSITION, NavTransition.LATERAL)
                    Pair(R.anim.slide_in_right, R.anim.slide_out_left)
                } else {
                    Pair(R.anim.slide_in_left, R.anim.slide_out_right)
                }
            NavTransition.MODAL ->
                if (isStart) {
                    bundle?.putSerializable(EXTRA_NAVIGATION_TRANSITION, NavTransition.MODAL)
                    Pair(R.anim.slide_down, R.anim.hold)
                } else {
                    Pair(R.anim.hold, R.anim.slide_up)
                }
            else -> Pair(0, 0)
        }
    }

    /**
     * Private util method to override transitions between activities.
     */
    private fun overrideTransition() {
        val anim = getNavAnimations(intent, isStart = false)
        overridePendingTransition(anim.first, anim.second)
    }

    protected open fun onShowLoader(show: Boolean) { loader?.isVisible = show }

    protected open fun onShowFeedback(message: String?, isPositive: Boolean) {
        if (isPositive && !message.isNullOrBlank() || !isPositive) {
            SnackBar.show(
                view = root,
                model = if (isPositive) {
                    SnackBarModel.Success(message!!)
                } else {
                    SnackBarModel.Error(message = message ?: getString(R.string.error_default))
                }
            )
        }
    }
}

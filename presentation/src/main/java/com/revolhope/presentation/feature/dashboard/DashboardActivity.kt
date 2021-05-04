package com.revolhope.presentation.feature.dashboard

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.mylibra.databinding.ActivityDashboardBinding
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override val binding: ActivityDashboardBinding
        get() = ActivityDashboardBinding.inflate(layoutInflater)

    private val viewModel: DashboardViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, DashboardActivity::class.java).apply {
                putExtras(bundleOf(EXTRA_NAVIGATION_TRANSITION to NavTransition.LATERAL))
            })
        }
    }

    override fun bindViews() {
        super.bindViews()

        viewModel.fetchFilms()
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.filmsLiveData, ::onFilmsReceived)
    }

    private fun onFilmsReceived(films: List<FilmModel>) {

    }
}

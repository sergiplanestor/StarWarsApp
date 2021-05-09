package com.revolhope.presentation.feature.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import com.revolhope.mylibra.databinding.ActivitySplashBinding
import com.revolhope.presentation.feature.dashboard.DashboardActivity
import com.revolhope.presentation.library.base.BaseActivity

class SplashActivity : BaseActivity() {

    companion object {
        private const val SPLASH_DELAY = 2500L // 2.5s
    }

    private lateinit var binding: ActivitySplashBinding

    override fun inflateView(): View =
        ActivitySplashBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                DashboardActivity.start(this@SplashActivity)
                finish()
            },
            SPLASH_DELAY
        )
    }
}

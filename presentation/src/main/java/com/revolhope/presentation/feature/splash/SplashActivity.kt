package com.revolhope.presentation.feature.splash

import android.os.Handler
import android.os.Looper
import com.revolhope.mylibra.databinding.ActivitySplashBinding
import com.revolhope.presentation.feature.dashboard.DashboardActivity
import com.revolhope.presentation.library.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    companion object {
        private const val SPLASH_DELAY = 2500L // 2.5s
    }

    override val binding: ActivitySplashBinding
        get() = ActivitySplashBinding.inflate(layoutInflater)


    override fun bindViews() {
        super.bindViews()
        Handler(Looper.getMainLooper()).postDelayed(
                { DashboardActivity.start(this@SplashActivity) },
                SPLASH_DELAY
        )
    }
}

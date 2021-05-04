package com.revolhope.presentation.feature.splash

import android.os.Handler
import android.os.Looper
import com.revolhope.mylibra.databinding.ActivitySplashBinding
import com.revolhope.presentation.feature.dashboard.DashboardActivity
import com.revolhope.presentation.library.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val binding: ActivitySplashBinding
        get() = ActivitySplashBinding.inflate(layoutInflater)


    override fun bindViews() {
        super.bindViews()

        // FIXME: Dummy code. Replace
        Handler(Looper.getMainLooper()).postDelayed({ DashboardActivity.start(this) }, 3000/* 3s */)
    }
}

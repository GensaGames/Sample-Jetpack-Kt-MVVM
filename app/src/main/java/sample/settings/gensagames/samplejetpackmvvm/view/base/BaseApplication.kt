package sample.settings.gensagames.samplejetpackmvvm.view.base

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import sample.settings.gensagames.samplejetpackmvvm.BuildConfig
import sample.settings.gensagames.samplejetpackmvvm.inject.DaggerApplicationComponent
import javax.inject.Inject
import timber.log.Timber




class BaseApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create().inject(this);
        bind()
    }

    private fun bind() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    inner class ReleaseTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            /**
             * TODO(Crashlytics): Add sending report.
             * Crashlytics.report(new Exception(message));
             */
        }
    }
}
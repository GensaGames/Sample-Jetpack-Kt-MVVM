package sample.settings.gensagames.samplejetpackmvvm.view

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import sample.settings.gensagames.samplejetpackmvvm.view.inject.DaggerActivityComponent
import javax.inject.Inject


class MainApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerActivityComponent.create().inject(this);
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}
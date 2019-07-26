package sample.settings.gensagames.samplejetpackmvvm.inject.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import sample.settings.gensagames.samplejetpackmvvm.inject.VmFactoriesModule
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.DetailFragment
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.DetailViewModel


@Module(includes = [DetailModule.Arguments::class])
abstract class DetailModule {

    @Binds
    @IntoMap
    @VmFactoriesModule.ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Module
    class Arguments {

        @Provides
        fun provideInfoObject(fragment : DetailFragment) : InfoObject {
            return fragment.getDetailArgs().infoObject
        }
    }
}
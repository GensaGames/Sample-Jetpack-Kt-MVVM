package sample.settings.gensagames.samplejetpackmvvm.view

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.detail_fragment.*
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.DetailFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.setStaticKenburnsImages
import sample.settings.gensagames.samplejetpackmvvm.utils.setStaticSpanned
import sample.settings.gensagames.samplejetpackmvvm.utils.setStaticText
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.DetailViewModel
import timber.log.Timber
import javax.inject.Inject


class DetailFragment : Fragment() {

    private lateinit var args: DetailFragmentArgs
    private lateinit var binding: DetailFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        args = DetailFragmentArgs.fromBundle(
            requireActivity().intent.extras!!
        )
        Timber.d("onCreateView with Args: $args")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false
        )
        return binding.root
    }

    fun getDetailArgs() : DetailFragmentArgs {
        return args
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        setupToolbar()
        bind()
        observe()
    }

    private fun setupToolbar() {
        (activity as DetailActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
    }

    private fun bind() {
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory
        )
            .get(DetailViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun observe() {
        binding.viewModel!!.textContent.observe(this, Observer<String> { t ->
            setStaticSpanned(binding.detailContent.textViewContent,
                Html.fromHtml(t, Html.FROM_HTML_MODE_COMPACT))
        })

        binding.viewModel!!.infoObject.observe(this, Observer<InfoObject> { t ->
            setStaticKenburnsImages(binding.headerKenburnsView, t.imageUrl)
        })
    }

}

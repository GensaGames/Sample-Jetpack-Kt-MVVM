package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.AdapterInfoItemBinding
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.DetailActivity
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.AdapterViewModel
import timber.log.Timber

class MainGridInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var infoObjects: List<InfoObject>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding: AdapterInfoItemBinding = DataBindingUtil.inflate(
            LayoutInflater
                .from(parent.context), R.layout.adapter_info_item, parent, false
        )
        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::infoObjects.isInitialized) infoObjects.size else 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as InfoViewHolder
        movieViewHolder.bind(infoObjects[position])
    }

    fun setInfoCollection(objects: Collection<InfoObject>) {
        Timber.d( String.format("setInfoCollection. Size: %s", objects.size))
        infoObjects = objects.toList()
        notifyDataSetChanged()
    }


    class InfoViewHolder(private val binding: AdapterInfoItemBinding)
        : RecyclerView.ViewHolder(binding.root), AdapterViewModel.OnNavigate {

        private val viewModel = AdapterViewModel()

        fun bind(info: InfoObject) {
            binding.viewModel = viewModel
            binding.infoObject = info
            viewModel.bind(info, this)
        }

        fun isFeatured(): Boolean {
            return viewModel.infoObject.value!!.isFeatured
        }

        override fun onNavigateToInfo() {
            val bundle = bundleOf(DetailActivity.INFO_OBJECT_TAG
                    to viewModel.infoObject.value)

            Navigation
                .findNavController(binding.root)
                .navigate(
                    R.id.navigate_to_detail_activity, bundle,
                    NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .build()
                )
        }
    }
}
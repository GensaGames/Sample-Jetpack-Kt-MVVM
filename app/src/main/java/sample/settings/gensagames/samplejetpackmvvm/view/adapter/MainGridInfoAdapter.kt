package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.InfoAdapterItemBinding
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.AdapterViewModel
import kotlin.random.Random

class MainGridInfoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var infoObjects : List<InfoObject>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding: InfoAdapterItemBinding = DataBindingUtil.inflate(LayoutInflater
            .from(parent.context), R.layout.info_adapter_item, parent, false)
        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::infoObjects.isInitialized) infoObjects.size else 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as InfoViewHolder
        movieViewHolder.bind(infoObjects[position])
    }

    fun setInfoCollection(objects: Collection<InfoObject>) {
        Log.d(TAG, String.format("setInfoCollection. Size: %s", objects.size))
        infoObjects = objects.toList()
        notifyDataSetChanged()
    }


    class InfoViewHolder(private val binding: InfoAdapterItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        private val viewModel = AdapterViewModel()

        fun bind(info:InfoObject) {
            binding.viewModel = viewModel
            viewModel.bind(info)
        }

        fun isFeatured(): Boolean {
            return Random(System.currentTimeMillis())
                .nextBoolean()
        }

    }
}
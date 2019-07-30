package sample.settings.gensagames.samplejetpackmvvm.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import timber.log.Timber

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    visibility?.observe(view.getParentActivity() as AppCompatActivity,
        Observer { value -> view.visibility = value?:View.VISIBLE})
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    text?.observe(view.getParentActivity() as AppCompatActivity,
        Observer { value -> view.text = value?:""})
}

@BindingAdapter("infoObjectName")
fun setInfoObjectName(view: TextView, info: MutableLiveData<InfoObject>?) {
    setInfoObject(view, info, action={
            v : View, i : InfoObject ->
        (v as TextView).text = i.name
    })
}

@BindingAdapter("infoObjectContact")
fun setInfoObjectContact(view: TextView, info: MutableLiveData<InfoObject>?) {
    setInfoObject(view, info, action={
            v : View, i : InfoObject ->
        (v as TextView).text = i.contact
    })
}

@BindingAdapter("infoObjectSummary")
fun setInfoObjectSummary(view: TextView, info: MutableLiveData<InfoObject>?) {
    setInfoObject(view, info, action={
            v : View, i : InfoObject ->
        (v as TextView).text = i.summary
    })
}

fun setInfoObject(view: View, info: MutableLiveData<InfoObject>?,
                  action: (v: View, i: InfoObject) -> Unit) {
    info?.observe(
        view.getParentActivity()!!,
        Observer { value -> action(view, value)})
}

@BindingAdapter("kenburnsImages")
fun setKenburnsImages(view: KenBurnsView, info: MutableLiveData<InfoObject>?) {
    val loadImage = fun (v : View, i : InfoObject) : Unit {

        val target = object : CustomTarget<Bitmap>(){
            override fun onResourceReady(
                resource: Bitmap, transition: Transition<in Bitmap>?) {
                Timber.d( "onResourceReady.")
                (v as KenBurnsView).setResourceIds(resource, resource)
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                Timber.e( "onLoadFailed. Loading random!")
            }
        }

        Glide.with(v.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(i.imageUrl)
            .into(target)
    }
    setInfoObject(view, info, loadImage)
}


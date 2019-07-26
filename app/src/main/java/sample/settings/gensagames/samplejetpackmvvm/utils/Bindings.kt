package sample.settings.gensagames.samplejetpackmvvm.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import timber.log.Timber


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("staticText")
fun setStaticText(view: TextView, text: String?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        view.text = text ?: ""
    }
}
@BindingAdapter("staticSpanned")
fun setStaticSpanned(view: TextView, text: Spanned?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        view.text = text ?: ""
    }
}


@BindingAdapter("staticKenburnsImages")
fun setStaticKenburnsImages(view: KenBurnsView, url : String?) {

    val target = object : CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            Timber.d( "onResourceReady.")
            view.setResourceIds(resource, resource)
        }
        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            Timber.e( "onLoadFailed. Loading random!")
            super.onLoadFailed(errorDrawable)
            /**
             * Just Stub to load random Image.
             */
            val newUrl = "https://picsum.photos/800"
            setStaticKenburnsImages(view, newUrl)
        }
    }

    Glide.with(view.context)
        .asBitmap()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .load(url!!)
        .into(target)
}

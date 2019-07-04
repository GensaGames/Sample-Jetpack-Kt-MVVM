package sample.settings.gensagames.samplejetpackmvvm.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import sample.settings.gensagames.samplejetpackmvvm.R
import java.util.*


class KenBurnsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle) {

    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private lateinit var mResourceIds: Array<out Bitmap>

    private var mImageViews: Array<ImageView>? = null
    private var mActiveImageIndex = -1

    private val random = Random()
    private val mSwapMs = 10000
    private val mFadeInOutMs = 1000
    private val mFadeInStartMs = 1500

    private val maxScaleFactor = 1.5f
    private val minScaleFactor = 1.2f

    private val mSwapImageRunnable : Runnable = object : Runnable {
        override fun run() {
            swapImage()
            mHandler.postDelayed(this, (
                    mSwapMs - mFadeInOutMs * 2).toLong())
        }
    }

    fun setResourceIds(vararg resourceIds: Bitmap) {
        mResourceIds = resourceIds
        fillImageViews()
    }

    private fun swapImage() {
        if (mActiveImageIndex == -1) {
            mActiveImageIndex = 1
            animate(mImageViews!![mActiveImageIndex])
            animateStart(mImageViews!![1])
            return
        }

        val inactiveIndex = mActiveImageIndex
        mActiveImageIndex = (1 + mActiveImageIndex) % mImageViews!!.size

        val activeImageView = mImageViews!![mActiveImageIndex]
        activeImageView.alpha = 0.0f
        val inactiveImageView = mImageViews!![inactiveIndex]

        animate(activeImageView)
        animateSwap(inactiveImageView, activeImageView)
    }

    private fun animateSwap(inactive : ImageView, active : ImageView) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = mFadeInOutMs.toLong()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(inactive, "alpha", 1.0f, 0.0f),
            ObjectAnimator.ofFloat(active, "alpha", 0.0f, 1.0f)
        )
        animatorSet.start()
    }

    private fun animateStart(active : ImageView) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = mFadeInStartMs.toLong()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(active, "alpha", 0.0f, 1.0f)
        )
        animatorSet.start()
    }

    private fun start(view: View, duration: Long, fromScale: Float, toScale: Float, fromTranslationX: Float,
                      fromTranslationY: Float, toTranslationX: Float, toTranslationY: Float) {
        view.scaleX = fromScale
        view.scaleY = fromScale
        view.translationX = fromTranslationX
        view.translationY = fromTranslationY
        val propertyAnimator = view.animate()
            .translationX(toTranslationX)
            .translationY(toTranslationY)
            .scaleX(toScale).scaleY(toScale).setDuration(duration)
        propertyAnimator.start()
    }

    private fun pickScale(): Float {
        return this.minScaleFactor + this.random.nextFloat() * (this.maxScaleFactor - this.minScaleFactor)
    }

    private fun pickTranslation(value: Int, ratio: Float): Float {
        return value.toFloat() * (ratio - 1.0f) * (this.random.nextFloat() - 0.5f)
    }

    fun animate(view: View) {
        val fromScale = pickScale()
        val toScale = pickScale()
        val fromTranslationX = pickTranslation(view.width, fromScale)
        val fromTranslationY = pickTranslation(view.height, fromScale)
        val toTranslationX = pickTranslation(view.width, toScale)
        val toTranslationY = pickTranslation(view.height, toScale)
        start(view, this.mSwapMs.toLong(), fromScale, toScale,
            fromTranslationX, fromTranslationY, toTranslationX, toTranslationY)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startKenBurnsAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mHandler.removeCallbacks(mSwapImageRunnable)
    }

    private fun startKenBurnsAnimation() {
        mHandler.post(mSwapImageRunnable)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val view = View.inflate(context, R.layout.view_kenburns, this)

        mImageViews = arrayOf(
                view.findViewById<View>(R.id.image0) as ImageView,
                view.findViewById<View>(R.id.image1) as ImageView)
    }

    private fun fillImageViews() {
        for (i in mImageViews!!.indices) {
            mImageViews!![i].alpha = 0.0f
            mImageViews!![i].setImageBitmap(mResourceIds[i])
        }
    }
}
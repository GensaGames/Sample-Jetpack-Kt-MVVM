package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.widget.ImageView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.fresco.FrescoImage
import com.facebook.yoga.YogaPositionType
import sample.settings.gensagames.samplejetpackmvvm.R

@LayoutSpec
object KenburnsItemSpec {


    @OnCreateLayout
    @JvmStatic
    fun onCreateLayout(c: ComponentContext, @Prop uri : String): Component {

        val controller: DraweeController = Fresco
            .newDraweeControllerBuilder()
            .setUri(uri)
            .build();

        val kenburnsView = Column
            .create(c)
            .child(
                FrescoImage
                    .create(c)
                    .placeholderImageRes(R.color.colorImagePlaceHolder)
                    .widthPercent(100f)
                    .heightPercent(100f)
                    .placeholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                    .controller(controller)
                    .positionType(YogaPositionType.ABSOLUTE)
            )
        return kenburnsView.build()
    }
}
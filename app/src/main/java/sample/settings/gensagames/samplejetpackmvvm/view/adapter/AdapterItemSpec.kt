package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Layout
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.litho.*
import com.facebook.litho.animation.AnimatedProperties
import com.facebook.litho.widget.*
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.thedeanda.lorem.LoremIpsum
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import com.facebook.litho.annotations.*
import com.facebook.litho.fresco.FrescoImage
import timber.log.Timber


@LayoutSpec(events = [AdapterItemClickEvent::class])
object AdapterItemSpec {


    private const val TRANS_K = "Kenburns"

    @JvmStatic
    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop info : InfoObject): Component {

        val controller: DraweeController = Fresco
            .newDraweeControllerBuilder()
            .setUri(info.imageUrl)
            .build();

        val animatedImage =  FrescoImage
            .create(c)
            .placeholderImageRes(R.color.colorImagePlaceHolder)
            .widthPercent(100f)
            .heightPercent(100f)
            .scale(2f)
            .transitionKey(TRANS_K)
            .placeholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
            .controller(controller)
            .positionType(YogaPositionType.ABSOLUTE)

        val main = Column
            .create(c)
            .backgroundColor(Color.WHITE)
            .heightRes(R.dimen.grid_card_height)
            .wrapInView()
            .clipChildren(true)
            .child(
                animatedImage
            )
            .child(
                Text.create(c)
                    .backgroundRes(R.color.colorTransparentWhite)
                    .textColorRes(android.R.color.white)
                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                    .text(info.name)
                    .maxLines(1)
                    .textSizeAttr(android.R.attr.textSize)
                    .textSizeRes(R.dimen.text_size_large)
            )
            .child(
                Text.create(c)
                    .text(info.contact)
                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
                    .textColorRes(android.R.color.white)
                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                    .textStyle(Typeface.BOLD)
                    .textSizeRes(R.dimen.text_size_low)
            )

            .child(
                Text.create(c)
                    .text(info.summary)
                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
                    .textColorRes(android.R.color.white)
                    .textSizeRes(R.dimen.text_size_low)
            )
            .build()

        return Column.create(c)
            .paddingRes(YogaEdge.ALL, R.dimen.generic_padding_low)
            .wrapInView()
            .clipChildren(true)
            .child(
                Card
                    .create(c)
                    .cornerRadiusRes(R.dimen.rounded_grid_corners)
                    .wrapInView()
                    .clipChildren(true)
                    .shadowElevationRes(R.dimen.header_elevation)
                    .clickHandler(AdapterItem.onViewClicked(c))
                    .content(main)
                    .build()
            ).build()
    }

    @OnCreateTransition
    fun onCreateTransition(c: ComponentContext): Transition {
        return Transition.create(TRANS_K)
            .animate(AnimatedProperties.ALPHA)
            .appearFrom(0f)
            .animate(AnimatedProperties.SCALE)
            .appearFrom(0f)
    }
//
//    @JvmStatic
//    @OnCreateLayout
//    fun onCreateLayout(c: ComponentContext, @Prop info : InfoObject): Component {
//
//        val main = Column
//            .create(c)
//            .backgroundColor(Color.WHITE)
//            .heightRes(R.dimen.grid_card_height)
//            .child(
//                KenburnItem
//                    .create(c)
//                    .widthPercent(100f)
//                    .heightPercent(100f)
//                    .uri(info.imageUrl)
//                    .positionType(YogaPositionType.ABSOLUTE)
//            )
//            .child(
//                Text.create(c)
//                    .backgroundRes(R.color.colorTransparentWhite)
//                    .textColorRes(android.R.color.white)
//                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
//                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
//                    .text(info.name)
//                    .maxLines(1)
//                    .textSizeAttr(android.R.attr.textSize)
//                    .textSizeRes(R.dimen.text_size_large)
//            )
//            .child(
//                Text.create(c)
//                    .text(info.contact)
//                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
//                    .textColorRes(android.R.color.white)
//                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
//                    .textStyle(Typeface.BOLD)
//                    .textSizeRes(R.dimen.text_size_low)
//            )
//
//            .child(
//                Text.create(c)
//                    .text(info.summary)
//                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_text_medium)
//                    .textColorRes(android.R.color.white)
//                    .textSizeRes(R.dimen.text_size_low)
//            )
//            .build()
//
//        return Column.create(c)
//            .paddingRes(YogaEdge.ALL, R.dimen.generic_padding_low)
//            .child(
//                Card
//                    .create(c)
//                    .cornerRadiusRes(R.dimen.rounded_grid_corners)
//                    .shadowElevationRes(R.dimen.header_elevation)
//                    .clickHandler(AdapterItem.onViewClicked(c))
//                    .content(main)
//                    .build()
//            ).build()
//    }


    @OnEvent(ClickEvent::class)
    fun onViewClicked(c: ComponentContext, @Prop info: InfoObject) {
        Timber.d("onViewClicked: $info")

        AdapterItem.dispatchAdapterItemClickEvent(
            AdapterItem.getAdapterItemClickEventHandler(c), info)
    }
}
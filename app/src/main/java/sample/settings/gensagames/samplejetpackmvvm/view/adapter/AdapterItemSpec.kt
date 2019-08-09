package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.*
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.thedeanda.lorem.LoremIpsum
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject


@LayoutSpec
object AdapterItemSpec {

    @JvmStatic
    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop info : InfoObject): Component {

        val main = Column
            .create(c)
            .backgroundColor(Color.WHITE)
            .heightRes(R.dimen.grid_card_height)
            .child(
                KenburnsItem
                    .create(c)
                    .widthPercent(100f)
                    .heightPercent(100f)
                    .uri(info.imageUrl)
                    .positionType(YogaPositionType.ABSOLUTE)
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
            .child(
                Card
                    .create(c)
                    .cornerRadiusRes(R.dimen.rounded_grid_corners)
                    .shadowElevationRes(R.dimen.header_elevation)
                    .content(main)
                    .build()
            ).build()
    }
}
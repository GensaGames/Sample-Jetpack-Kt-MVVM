package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import android.content.Context
import com.facebook.litho.ComponentContext
import com.facebook.litho.EventHandler
import com.facebook.litho.LithoView
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.SingleComponentSection
import com.facebook.litho.sections.widget.GridRecyclerConfiguration
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.sections.widget.RecyclerConfiguration
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject


@GroupSectionSpec
object AdapterListSpec {
    private const val GRID_COLUMNS = 2

    @JvmStatic
    fun buildListItems(
        context: Context, items: List<InfoObject>,
        callback : EventHandler<InfoObject>): LithoView? {

        val component = RecyclerCollectionComponent
            .create(ComponentContext(context))
            .disablePTR(true)
            .recyclerConfiguration(
                GridRecyclerConfiguration
                    .create()
                    .numColumns(GRID_COLUMNS)
                    .build()
            )
            .section(
                AdapterList
                    .create(SectionContext(context))
                    .callback(callback)
                    .items(items)
                    .build()
            )
            .build();
        return LithoView.create(context, component)
    }

    @JvmStatic
    @OnCreateChildren
    fun onCreateChildren(
        c: SectionContext, @Prop items: List<InfoObject>,
        @Prop callback : EventHandler<InfoObject>): Children {

        val builder = Children.create()

        for (i in items) {
            builder.child(
                SingleComponentSection
                    .create(c)
                    .key(i.toString())
                    .component(
                        AdapterItem
                            .create(c)
                            .adapterItemClickEventHandler(callback)
                            .info(i)
                    )
            )
        }
        return builder.build()
    }
}
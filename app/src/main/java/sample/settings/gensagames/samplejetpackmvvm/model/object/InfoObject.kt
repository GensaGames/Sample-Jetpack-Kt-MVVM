package sample.settings.gensagames.samplejetpackmvvm.model.`object`

import android.os.Parcel
import android.os.Parcelable

data class InfoObject (
    val name : String, val contact : String,
    val summary : String, val imageUrl : String, val isFeatured: Boolean) : Parcelable {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(contact)
        parcel.writeString(summary)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isFeatured) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoObject> {
        override fun createFromParcel(parcel: Parcel): InfoObject {
            return InfoObject(parcel)
        }

        override fun newArray(size: Int): Array<InfoObject?> {
            return arrayOfNulls(size)
        }
    }

}
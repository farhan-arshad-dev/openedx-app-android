package org.openedx.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.openedx.core.domain.model.iap.ProductInfo
import java.util.Date

@Parcelize
data class CourseInfoOverview(
    val name: String,
    val number: String,
    val org: String,
    val start: Date?,
    val startDisplay: String,
    val startType: String,
    val end: Date?,
    val isSelfPaced: Boolean,
    var media: Media?,
    val courseSharingUtmParameters: CourseSharingUtmParameters,
    val courseAbout: String,
    val courseModes: List<CourseMode>?,
    val productInfo: ProductInfo?
) : Parcelable {
    val isStarted: Boolean
        get() = start?.before(Date()) ?: false
}
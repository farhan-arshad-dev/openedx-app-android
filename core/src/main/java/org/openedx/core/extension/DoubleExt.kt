package org.openedx.core.extension

fun Double.nonZero(): Double? {
    return if (this != 0.0) this else null
}

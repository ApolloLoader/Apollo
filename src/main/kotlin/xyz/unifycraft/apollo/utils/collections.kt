package xyz.unifycraft.apollo.utils

private fun <T> MutableList<T>.addIfNotPresent(element: T) {
    if (!contains(element)) {
        add(element)
    }
}
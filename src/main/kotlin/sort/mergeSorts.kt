package sort

fun <T: Comparable<T>> mergeSort(a: Array<T>, lo: Int = 0, hi: Int = a.size - 1) {
    if (hi <= lo) return
    val mid = (lo + hi) / 2

    mergeSort(a, lo, mid)
    mergeSort(a, mid+1, hi)
    merge(a, lo, mid, hi)
}
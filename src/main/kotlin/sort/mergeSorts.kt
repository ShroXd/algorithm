package sort

fun <T : Comparable<T>> mergeSort(a: Array<T>, lo: Int = 0, hi: Int = a.size - 1) {
    if (hi <= lo) return
    val mid = (lo + hi) / 2

    mergeSort(a, lo, mid)
    mergeSort(a, mid + 1, hi)
    merge(a, lo, mid, hi)
}


fun <T : Comparable<T>> mergeSort1(a: Array<T>, lo: Int = 0, hi: Int = a.size - 1) {
    if (hi <= lo) return
    val mid = (lo + hi) / 2

    mergeSort(a, lo, mid)
    mergeSort(a, mid + 1, hi)

    // The array is sorted
    // T(N) = 2T(N/2) + 1, with T(1) = 0
    // The running time for any sorted sub-array is linear
    if (a[mid] <= a[mid + 1]) return

    merge(a, lo, mid, hi)
}


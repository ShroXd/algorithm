package sort

fun <T : Comparable<T>> partition(a: Array<T>, lo: Int, hi: Int): Int {
    var i = lo
    var j = hi + 1
    val v = a[lo]

    while (true) {
        while (less(a[++i], v))
            if (i == hi) break
        while (less(v, a[--j]))
            if (j == lo) break
        if (i >= j) break

        swap(a, i, j)
    }

    swap(a, lo, j)

    return j
}

fun <T : Comparable<T>> quickSort(a: Array<T>, lo: Int, hi: Int) {
    if (hi <= lo) return
    val j = partition(a, lo, hi)
    quickSort(a, lo, j - 1)
    quickSort(a, j + 1, hi)
}

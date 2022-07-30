package sort

import edu.princeton.cs.algs4.StdRandom

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

fun <T : Comparable<T>> sort(a: Array<T>, lo: Int, hi: Int) {
    if (hi <= lo) return
    val j = partition(a, lo, hi)
    sort(a, lo, j - 1)
    sort(a, j + 1, hi)
}

fun <T: Comparable<T>> quickSort(a: Array<T>) {
    StdRandom.shuffle(a)
    sort(a, 0, a.size - 1)
}
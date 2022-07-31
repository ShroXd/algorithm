package sort

import edu.princeton.cs.algs4.StdRandom

fun partition(a: Array<Int>, lo: Int, hi: Int): Int {
    var i = lo
    var j = hi + 1
    val v = a[lo]

    while (true) {
        while (a[++i] < v) {}
        while (a[--j] > v) {}

        if (i >= j) break
        swap(a, i, j)
    }

    swap(a, lo, j)

    return j
}

fun sort(a: Array<Int>, lo: Int, hi: Int) {
    if (hi <= lo) return
    val j = partition(a, lo, hi)
    sort(a, lo, j - 1)
    sort(a, j + 1, hi)
}

fun quickSort(a: Array<Int>) {
    StdRandom.shuffle(a)

    val array = a.copyOf(a.size + 1)
    array[array.size - 1] = Int.MAX_VALUE

    sort(array as Array<Int>, 0, a.size - 1)

    for (idx in a.indices)
        a[idx] = array[idx] as Int
}
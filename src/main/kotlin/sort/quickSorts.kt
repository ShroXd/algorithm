package sort

fun partition(a: Array<Int>, lo: Int, hi: Int): Int {
    var i = lo
    var j = hi + 1
    val v = a[lo]

    while (true) {
        // while (less(a[++i], v)) if (i == hi) break
        while (less(a[++i], v))
//            if (i == hi) break
            continue
        // while (less(v, a[--j])) if (j == lo) break
        while (less(v, a[--j]))
            if (j == lo) break
        if (i >= j) break

        swap(a, i, j)
    }

    swap(a, lo, j)

    return j
}

// TODO: use list instead of array
fun quickSort(a: Array<Int>): Array<Int> {
    var local = a.clone()
    local.shuffle()
    local = append(local, 2147483647)
    quickSort(local, 0, a.size - 1)
    local = remove(local, local.size - 1)

    return local
}

fun quickSort(a: Array<Int>, lo: Int, hi: Int) {
    if (hi <= lo) return
    val j = partition(a, lo, hi)
    quickSort(a, lo, j - 1)
    quickSort(a, j + 1, hi)
}

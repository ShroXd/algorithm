package sort

fun <T : Comparable<T>> selection(a: Array<T>) {
    val n = a.size - 1

    for (i in 0..n) {
        var min = i
        for (j in i + 1..n)
            if (less(a[j], a[min])) min = j
        swap(a, i, min)
    }
}

fun <T : Comparable<T>> insertion(a: Array<T>) {
    val n = a.size - 1

    for (i in 0..n)
        for (j in i downTo 1)
            if (less(a[j], a[j - 1]))
                swap(a, j, j - 1)
}

package sort

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun <T : Comparable<T>> less(v: T, w: T): Boolean = v < w

fun <T : Comparable<T>> swap(a: Array<T>, i: Int, j: Int) {
    a[i] = a[j].also { a[j] = a[i] }
}

fun <T : Comparable<T>> show(a: Array<T>) {
    for (i in a)
        print("$i ")
    println()
}

fun <T : Comparable<T>> isSorted(a: Array<T>): Boolean {
    for (i in 1..a.size)
        if (less(a[i], a[i - 1])) return false
    return true
}

fun run(fn: Runnable) {
    val time = measureTimeMillis {
        fn.run()
    }

    print("Time: $time")
}

fun <T : Comparable<T>> merge(a: Array<T>, lo: Int, mid: Int, hi: Int) {
    var i = lo
    var j = mid + 1
    val temp = a.clone()

    for (k in lo..hi) {
        if (i > mid) {
            a[k] = temp[j++]
        } else if (j > hi) {
            a[k] = temp[i++]
        } else if (less(temp[i], temp[j])) {
            a[k] = temp[i++]
        } else {
            a[k] = temp[j++]
        }
    }
}

fun append(arr: Array<Int>, element: Int): Array<Int> {
    val list: MutableList<Int> = arr.toMutableList()
    list.add(element)
    return list.toTypedArray()
}

inline fun <reified T : Comparable<T>> remove(arr: Array<T>, index: Int): Array<T> {
    val list: MutableList<T> = arr.toMutableList()
    list.removeAt(index)
    return list.toTypedArray()
}

fun generateRandomIntArray(n: Int): Array<Int> = Array(n) { Random.nextInt() }

fun generateSortedArray(a: Array<Int>): Array<Int> {
    val copy: Array<Int> = a.clone()
    copy.sort()
    return copy
}

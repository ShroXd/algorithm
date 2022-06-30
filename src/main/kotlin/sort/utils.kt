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
    // TODO: show the log during the test
    val time = measureTimeMillis {
        fn.run()
    }

    print("Time: $time")
}

fun generateRandomIntArray(n: Int): Array<Int> = Array(n) { Random.nextInt() }

fun generateSortedArray(a: Array<Int>): Array<Int> {
    val copy: Array<Int> = a.clone()
    copy.sort()
    return copy
}

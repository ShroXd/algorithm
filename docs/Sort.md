# Elementary sorts

Sorting is the process of rearranging a sequence of objects so as to put them in some logical order, of course.

## Selection sort

1. Find the smallest item in the array and exchange it with the first entry (itself if the first entry is already the smallest).
2. Find the next smallest item and exchange it with the second entry.
3. Continue in this way until the entire array is sorted.

```
repeat (numOfElements - 1) times
	set the first unsorted element as the minimum
	for each of the unsorted elements
		if element < currentMinimum
			set element as new minium
	swap minimum with first unsorted position
```

## Insertion sort
Inserting each into its proper place among those already considered (keeping them sorted).

```
mark first element as sorted
for each unsorted element X
	'extract' the element X
	for j = lastSortedIndex down to 0
		if current element j > X
			move sorted element to the right by 1
		break loop and insert X here
```

### Partially sorted array
If the number of inversions in an array is less than a constant multiple of the array size, we say that the array is partially sorted.

When the number of inversions is low, insertion sort is likely to be faster than any sorting method.

## Shell sort

```
for gap = size / 2n down to 1
    for each gap in array
        sort all the elements at gap 
```


# Merge sorts

The algorithms that we consider in this section are based on a simple operation known as _merging_: combining two ordered arrays to make one larger ordered array.
To sort an array, divide it into two halves, sort the two halves (recursively), and them merge the result.
Since we divide the array into two halves, so for an N items array, the time will be NlogN.

## Top-down mergeSort

The algorithm is: if it sorts the two sub-arrays, it sorts the whole array, by merging together the sub-arrays.
This is one-of best-known examples of the utility of the _divide-and-conquer_ paradigm for efficient algorithm design.

```
find midIdx of array
recursively sort left part
recursively sort right part
i = lower bounds
j = midIdx
for k = lower bounds to upper bounds
    if i out of mid: put the right part into result
    if j out of high bounds: put the left part into result
    put the lower value into result
```

def quicksort(l):
	''' Sorts the given list l using the quicksort algorithm. '''
	if len(l) == 0:
		return l
	pivot = l[(len(l) - 1) // 2]
	l.remove(pivot)
	less = []
	greater = []
	for num in l:
		if num <= pivot:
			less.append(num)
		else:
			greater.append(num)
	return quicksort(less) + [pivot] + quicksort(greater)

print(quicksort([4, 3, 2, 1]))
print(quicksort([32, 6, 54, 32, 454, 765, 565, 32, 90]))
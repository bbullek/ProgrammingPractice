/**
 * An implementation of a minimum heap with handles.
 * @author Brooke Bullek
 */

import java.util.Arrays;

public class Heap {

    private HeapElt[] array;
    int heapsize;
    int arraysize;

    /**
     * The constructor has been set up with an initial array of size 4
     * so that your doubleHeap() method will be tested. Don't change
     * this!
     */
    public Heap() {
        array = new HeapElt[4];
        heapsize = 0;
        arraysize = 4;
    }

    /**
     * Exchanges the values at positions pos1 and pos2 in the heap array.
     * Handles must be exchanged correctly as well.
     *
     * Running time = O(1)
     */
    private void exchange(int pos1, int pos2) {
        // Swap positions within the underlying array
        HeapElt tempElt = this.array[pos1];
        this.array[pos1] = this.array[pos2];
        this.array[pos2] = tempElt;

        // Swap handles of the HeapElts
        int tempHandle = this.array[pos1].getHandle();
        this.array[pos1].setHandle(this.array[pos2].getHandle());
        this.array[pos2].setHandle(tempHandle);
    }

    /**
     * Doubles the size of the array.  A new array is created, the elements in
     * the heap are copied to the new array, and the array data member is set
     * to the new array. Data member arraysize is set to the size of the
     * new array.
     *
     * Running time = O(n) (where n is the number of heap elements)
    */
    private void doubleHeap() {
        this.arraysize *= 2;
        HeapElt[] newArray = new HeapElt[this.arraysize];
        
        // Copy old elements into the new array
        for (int i = 1; i < this.heapsize; i++) {
            newArray[i] = this.array[i].copy(); // Deep copy
        }
        
        this.array = newArray;
    }

    /**
     * Fixes the heap if the value at position pos may be smaller than its
     * parent. Using exchange() to swap elements will simplify your
     * implementation. Heap elements contain records that are Comparable;
     * you will need to figure out what to test and how to test it.
     *
     * Running time = O(lgn)
     */
    public void heapifyUp(int pos) {
        int parent = getParent(pos);
        if (pos > 1 && compareElts(pos, parent) == -1) {
            exchange(pos, parent);
            heapifyUp(parent);
        }
    }

    /**
     * Fixes the heap if the value at position pos may be bigger than one of
     * its children.  Using exchange() to swap elements will simplify your
     * implementation.  Heap elements contain records that are Comparable;
     * you will need to figure out what to test and how to test it.
     *
     * Running time = O(lgn)
     */
    public void heapifyDown(int pos) {
        int left = getLChild(pos);
        int right = getRChild(pos);
        int smallest;

        // If A[left] is smaller
        if (left <= heapsize && compareElts(pos, left) == 1)
            smallest = left;
        else
            smallest = pos;

        // If A[right] is smaller
        if (right <= heapsize && compareElts(smallest, right) == 1)
            smallest = right;

        // Swap if left or right child is smaller
        if (smallest != pos) {
            exchange(pos, smallest);
            heapifyDown(smallest);
        }
    }

    /**
     * Insert inElt into the heap. Before doing so, make sure that there is
     * an open spot in the array for doing so. If you need more space, call
     * doubleHeap() before doing the insertion.
     *
     * Running time = 
     *      Case 1: O(1) (when the array does not need to grow and
     *              the new element is not smaller than its parent)
     *      Case 2: O(n) (when the array needs to grow and the new
     *              element is not smaller than its parent)
     *      Case 3: O(lgn) (when the array does not need to grow and
     *              the new element IS smaller than its parent -- so
     *              heapifyUp must be called)
     *      Case 4: O(n + lgn) = O(n) (when the array needs to grow
     *              and the new element is smaller than its parent)
     */
    public void insert(HeapElt inElt) {
        this.heapsize++;

        if (this.arraysize <= this.heapsize)
            doubleHeap();

        inElt.setHandle(this.heapsize);
        this.array[this.heapsize] = inElt;
        heapifyUp(this.heapsize);
    }

    /**
     * Remove the minimum element from the heap and return it. Restore
     * the heap to heap order. Assumes heap is not empty, and will
     * cause an exception if the heap is empty.
     *
     * Running time = O(lgn)
     */
    public HeapElt removeMin() {
        HeapElt min = this.array[1].copy();
        exchange(1, this.heapsize);
        this.heapsize--;
        heapifyDown(1);

        return min;
    }

    /**
     * Return the number of elements in the heap.
     *
     * Running time = O(1)
     */
    public int getHeapsize() {
        return this.heapsize;
    }

   /**
    * Return the index corresponding to the parent of the given index.
    *
    * Running time = O(1)
    */
    public int getParent(int pos) {
        return (int) Math.floor(pos / 2);
    }

   /**
    * Return the index corresponding to the left child of the given
    * index.
    *
    * Running time = O(1)
    */
    public int getLChild(int pos) {
        return 2 * pos;
    }

   /**
    * Return the index corresponding to the right child of the given
    * index.
    *
    * Running time = O(1)
    */
    public int getRChild(int pos) {
        return 2 * pos + 1;
    }

   /**
    * Compares the records of two HeapElts at the given positions. Returns
    * 0 if the record of pos1 == record of pos2, -1 if the record of pos1
    * is smaller than the record of pos2, and 1 if the record of pos1 is
    * larger than the record of pos2. (This functionality is identical to
    * that of the Comparable interface's compareTo method.)
    *
    * Running time: O(1)
    */
    public int compareElts(int pos1, int pos2) {
        Comparable record1 = this.array[pos1].getRecord();
        Comparable record2 = this.array[pos2].getRecord();
        return record1.compareTo(record2);
    }

   /**
    * Print out the heap for debugging purposes. It is recommended to 
    * print both the key from the record and the handle.
    *
    * A tree structure is printed with each HeapElt represented by the
    * following notation: Record(Handle)
    *
    * Running time = O(n) (where n is the number of heap elements)
    */
    public void printHeap() {
        int height = log2(this.arraysize) + 1;

        for (int i = 1; i <= this.heapsize; i++) {
            int record = (int) this.array[i].getRecord();
            int handle = this.array[i].getHandle();

            int depth = log2(i) + 1;
            int numSpaces = (3 - depth) + height - depth;

            // Create spaces for padding each element
            char[] padding = new char[numSpaces];
            Arrays.fill(padding, ' ');

            System.out.print(new String(padding));
            String data = Integer.toString(record) + '(' +
                Integer.toString(handle) + ')';
            System.out.print(data);

            if ((int) Math.pow(2, depth) - 1 == i) {
                System.out.println();
            }
        }

        System.out.println();
    }

    /**
     * Computes the log in base 2 of a given integer.
     *
     * Running time: O(1)
     */
    public static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}

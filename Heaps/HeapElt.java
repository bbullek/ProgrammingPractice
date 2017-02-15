/**
 * A generic class for heap elements that include handles.
 */

public class HeapElt {

    protected Comparable record;

    protected int handle = 0;

    public void setRecord(Comparable inRec) {
        this.record = inRec;
    }

    public Comparable getRecord() {
	    return record;
    }

    public void setHandle(int inHandle){
	    this.handle = inHandle;
    }

    public int getHandle() {
	    return handle;
    }
    
    /**
     * Creates and returns a deep copy of this object.
     */ 
    public HeapElt copy() {
        HeapElt newElement = new HeapElt();
        
        newElement.setHandle(this.handle);
        newElement.setRecord(this.record);
        
        return newElement;
    }
}

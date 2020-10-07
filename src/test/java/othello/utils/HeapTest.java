/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author riikoro
 */
public class HeapTest {

    private Heap minHeap;
    private Heap maxHeap;

    @Before
    public void setUp() {
        this.minHeap = new Heap(false);
        this.maxHeap = new Heap(true);
    }

    @Test
    public void initializedHeapExists() {
        assert (minHeap != null && maxHeap != null);
    }

    @Test
    public void emptyHeapPeekReturnsNull() {
        assert (minHeap.peek() == null);
    }

    @Test
    public void emptyHeapPollReturnsNull() {
        assert (maxHeap.peek() == null);
    }

    @Test
    public void heapWithOneNodePeeksSameNode() {
        minHeap.add(new int[][]{{0, 0}, {10, 0}});
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {10, 0}}));
    }

    @Test
    public void heapWithOneNodeEmptyAfterPoll() {
        maxHeap.add(new int[][]{{0, 0}, {10, 0}});
        maxHeap.poll();
        assert (maxHeap.peek() == null);
    }

    @Test
    public void minHeapWithMultipleNodesPollsInCorrectOrder() {
        minHeap.add(new int[][]{{0, 0}, {4, 0}});
        minHeap.add(new int[][]{{0, 0}, {6, 0}});
        minHeap.add(new int[][]{{0, 0}, {3, 0}});
        minHeap.add(new int[][]{{0, 0}, {3, 0}});
        minHeap.add(new int[][]{{0, 0}, {7, 0}});
        
        
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {3, 0}}));
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {3, 0}}));
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {4, 0}}));
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {6, 0}}));
        assert (Arrays.deepEquals(minHeap.poll(), new int[][]{{0, 0}, {7, 0}}));
    }

    @Test
    public void maxHeapWithMultipleNodesPollsInCorrectOrder() {
        maxHeap.add(new int[][]{{0, 0}, {4, 0}});
        maxHeap.add(new int[][]{{0, 0}, {6, 0}});
        maxHeap.add(new int[][]{{0, 0}, {3, 0}});
        maxHeap.add(new int[][]{{0, 0}, {1, 0}});
        maxHeap.add(new int[][]{{0, 0}, {7, 0}});
        
        assert (Arrays.deepEquals(maxHeap.poll(), new int[][]{{0, 0}, {7, 0}}));
        assert (Arrays.deepEquals(maxHeap.poll(), new int[][]{{0, 0}, {6, 0}}));
        assert (Arrays.deepEquals(maxHeap.poll(), new int[][]{{0, 0}, {4, 0}}));
        assert (Arrays.deepEquals(maxHeap.poll(), new int[][]{{0, 0}, {3, 0}}));
        assert (Arrays.deepEquals(maxHeap.poll(), new int[][]{{0, 0}, {1, 0}}));
    }
    
    @Test
    public void heapIsEmptyAfterInitialization() {
        assert(minHeap.isEmpty());
    }
    
    @Test
    public void heapIsNotEmptyAfterNodeInserted() {
        minHeap.add(new int[][]{{0, 0}, {4, 0}});
        assert(!minHeap.isEmpty());
    }
    
    @Test
    public void heapIsEmptyAfterNodeInsertedAndPolled() {
        minHeap.add(new int[][]{{0, 0}, {4, 0}});
        minHeap.poll();
        assert(minHeap.isEmpty());
    }
}

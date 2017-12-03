package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        /* TODO: implement it */
        stack1 = new LinkedStack<Item>();
        stack2 = new LinkedStack<Item>();
    }

    @Override
    public void enqueue(Item item) {
        /* TODO: implement it */
        Iterator<Item> it = stack2.iterator();
        stack1.push(item);
        while(it.hasNext()){
            stack1.push(stack2.pop());
        }
        Object tmp;
        tmp = stack1;
        stack1 = stack2;
        stack2 = (LinkedStack<Item>)tmp;
    }

    @Override
    public Item dequeue() {
        /* TODO: implement it */
        return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        /* TODO: implement it */
        return stack2.isEmpty();
    }

    @Override
    public int size() {
        /* TODO: implement it */
        return stack2.size();
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it (optional) */
        return stack2.iterator();
    }

}

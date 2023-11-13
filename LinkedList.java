 

//doubly-linked list, both head and tail pointers, generic type
public class LinkedList<T>
{
    
    private class Node<T>
    {
        T data;
        Node<T> next;
        Node<T> prev;
        
        Node()
        {
            data = null;
            next = null;
            prev = null;
        }
        Node(T d)
        {
            data = d;
            next = null;
            prev = null;
        }
        
        public void setData(T d)
        {
            data = d;
        }
        public void setNext(Node<T> n)
        {
            next = n;
        }
        public void setPrev(Node<T> p)
        {
            prev = p;
        }
        public T getData()
        {
            return data;
        }
        public Node<T> getNext()
        {
            return next;
        }
        public Node<T> getPrev()
        {
            return prev;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public LinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }
    
    public Node<T> getHead()
    {
        return head;
    }
    public Node<T> getTail()
    {
        return tail;
    }
    public int getSize()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return (size == 0);
    }
    public T getAtIndex(int i)
    {
        if ((isEmpty()) || (i > size))
        {
            return null;
        }
        Node<T> newNode = head;
        for (int a = 1; a < i; a += 1)
        {
            if (newNode == null)
            {
                return null;
            }
            newNode = newNode.getNext();
        }
        return newNode.getData();
    }
    public int getIndexOf(T d)
    {
        if (isEmpty())
        {
            return -1;
        }
        Node<T> newNode = head;
        int count = 1;
        while (newNode != null)
        {
            if (newNode.getData().equals(d))
            {
                return count;
            }
            newNode = newNode.getNext();
            count += 1;
        }
        return -1;
    }
    public T get(T d)
    {
        if (isEmpty())
        {
            return null;
        }
        Node<T> newNode = head;
        while (newNode != null)
        {
            if (((T)(newNode.getData())).equals(((T)d)))
            {
                return newNode.getData();
            }
            newNode = newNode.getNext();
        }
        return null;
    }
    
    public T remove (T d)
    {
        //remove specific node from list with data "d"
        if ((head == null) || (tail == null))
        {
            //if list is empty, return null
            return null;
        }
        if (head == tail)
        {
            //exactly 1 node in list
            if (head.getData().equals(d))
            {
                //the node to remove is the only node in list
                Node<T> tempNode = new Node<T>();
                tempNode.setData(head.getData());
                head = null;
                tail = null;
                size -= 1;
                return tempNode.getData();
            }
            else
            {
                //node to remove does not exist in list
                return null;
            }
        }
        else
        {
            //list contains more than 1 node
            Node<T> tempNode = head;
            while (tempNode != null)
            {
                if (((T)tempNode.getData()).equals(d))
                {
                    //current node is the one to remove
                    if ((tempNode.getPrev() != null) && 
                        (tempNode.getNext() != null))
                    {
                        //there are nodes on both sides of node to remove
                        tempNode.getPrev().setNext(tempNode.getNext());
                        tempNode.getNext().setPrev(tempNode.getPrev());
                        tempNode.setNext(null);
                        tempNode.setPrev(null);
                        size -= 1;
                        return tempNode.getData();
                    }
                    else if ((tempNode.getPrev() != null) && 
                             (tempNode.getNext() == null))
                    {
                        //there is a node before node to remove, not after
                        tempNode.getPrev().setNext(tempNode.getNext());
                        tail = tempNode.getPrev();
                        tempNode.setNext(null);
                        tempNode.setPrev(null);
                        size -= 1;
                        return tempNode.getData();
                    }
                    else if ((tempNode.getPrev() == null) && 
                             (tempNode.getNext() != null))
                    {
                        //there is a node after node to remove, not before
                        tempNode.getNext().setPrev(tempNode.getPrev());
                        head = tempNode.getNext();
                        tempNode.setNext(null);
                        tempNode.setPrev(null);
                        size -= 1;
                        return tempNode.getData();
                    }
                    else
                    {
                        //no other nodes in list
                        head = null;
                        tail = null;
                        size -= 1;
                        return tempNode.getData();
                    }
                }
                tempNode = tempNode.getNext();
            }
            return null;
        }
    }
    
    public void push(T n)
    {
        //inserts new node at front of list
        Node<T> newNode = new Node<T>();
        newNode.setData(n);
        newNode.setNext(head);
        
        if (head != null)
        {
            //there exists a head node - at least 1 node in list
            head.setPrev(newNode);
        }
        else
        {
            //there are no nodes in list
            tail = newNode;
        }
        head = newNode;
        newNode.setPrev(null);
        size += 1;
    }
    
    public T pop()
    {
        //removes node from front of list
        if ((head == null) || (tail == null))
        {
            //if the list is empty, return null
            return null;
        }
        if (head != tail)
        {
            //list contains more than 1 node
            Node<T> tempNode = head;
            head = head.getNext();
            head.setPrev(null);
            tempNode.setNext(null);
            size -= 1;
            return tempNode.getData();
        }
        else
        {
            //list contains exactly 1 node
            Node<T> tempNode = head;
            head = null;
            tail = null;
            size -= 1;
            return tempNode.getData();
        }
    }
    
    public void enqueue(T d)
    {
        //inserts new node at end of list
        Node<T> newNode = new Node<T>();
        newNode.setData(d);
        newNode.setPrev(tail);
        if (tail != null)
        {
            //there is a tail node in the list, so list has at least 1 node
            tail.setNext(newNode);
        }
        else
        {
            head = newNode;
        }
        tail = newNode;
        tail.setNext(null);
        size += 1;
    }
    
    public T slice()
    {
        //removes node from the end of list
        if ((head == null) || (tail == null))
        {
            //if there are no nodes in the list, return null
            return null;
        }
        if (head == tail)
        {
            //there is exactly one node in the list
            Node<T> tempNode = tail;
            tail = null;
            head = null;
            size -= 1;
            return tempNode.getData();
        }
        else
        {
            //list contains more than 1 node
            Node<T> tempNode = tail;
            tail = tail.getPrev();
            tail.setNext(null);
            tempNode.setPrev(null);
            size -= 1;
            return tempNode.getData();
        }
    }
    
    public Object[] toArray()
    {
        if ((head == null) || (tail == null))
        {
            return null;
        }
        Node<T> newNode = new Node<T>();
        newNode = head;
        Object[] tArr = new Object[size];
        int currIndex = 0;
        while (newNode != null)
        {
            tArr[currIndex] = newNode.getData();
            currIndex += 1;
            newNode = newNode.getNext();
        }
        return tArr;
    }
    
    public void printList()
    {
        Node<T> newNode = head;
        for (int a = 1; a <= size; a += 1)
        {
            System.out.println(newNode.getData());
            newNode = newNode.getNext();
        }
    }
    public String toStringLines()
    {
        String s = "";
        Node<T> newNode = head;
        for (int a = 1; a <= size; a += 1)
        {
            s += newNode.getData().toString() + "\n";
            newNode = newNode.getNext();
        }
        return s;
    }
    public String toString()
    {
        String s = "";
        Node<T> newNode = head;
        for (int a = 1; a <= size; a += 1)
        {
            s += newNode.getData().toString();
            if (newNode.getNext() != null)
            {
                s += ", \n\t";
            }
            newNode = newNode.getNext();
        }
        return s;
    }
}
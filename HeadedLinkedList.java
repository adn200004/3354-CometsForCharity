 


public class HeadedLinkedList<T> extends LinkedList<T>
{
    private int header;
    
    HeadedLinkedList()
    {
        super();
        header = -1;
    }
    HeadedLinkedList(int h)
    {
        header = h;
    }
    
    public int getHeader()
    {
        return header;
    }
    public void setHeader(int h)
    {
        header = h;
    }
}
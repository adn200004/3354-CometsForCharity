 


public class HashTable<T>
{
    private LinkedList<Integer>[] keys;//holds keys corresponding to the 
            //entries in the linkedlists in the hashtable
    private LinkedList<LinkedList<T>>[] data; 
            //holds the actual data needed to be stored in hashtable - 
            //first list holds each of dif 
    private int size; //size of data array
    private int load; //number of entries in data array
    
    public HashTable()
    {
        size = 47;
        load = 0;
        data = new LinkedList[size];
        keys = new LinkedList[size];
        for (int a = 0; a < size; a += 1)
        {
            data[a] = new LinkedList<LinkedList<T>>();
            keys[a] = new LinkedList<Integer>();
        }
    }
    public void setKeys(LinkedList<Integer>[] newKeys)
    {
        if (newKeys != null)
        {
            keys = newKeys;
        }
    }
    public void setLoad(int l)
    {
        load = l;
    }
    public void setKeys(LinkedList<Integer> newKeys, int index)
    {
        if (newKeys != null)
        {
            keys[index] = newKeys;
        }
    }
    public void setPunches(LinkedList<LinkedList<T>>[] newData)
    {
        if (newData != null)
        {
            data = newData;
        }
    }
    public void setPunches(LinkedList<LinkedList<T>> newData, int index)
    {
        if (newData != null)
        {
            data[index] = newData;
        }
    }
    public static int getNextPrime(int p)
    {
        int n = p + 1;
        if ((n % 2) == 0)
        {
            //if n is even, make it odd - no evens are prime
            n += 1;
        }
        boolean prime = true;
        outer: 
        while (1 == 1)
        {
            prime = true;
            inner: 
            for (int a = ((n + 1) / 2); a > 1; a -= 1)
            {
                //cycle through all numbers from n/2 to 0 to see if n is prime
                if ((n % a) == 0)
                {
                    //if n is divisible by a evenly, n is not prime
                    prime = false;
                    break inner;
                }
            }
            if (prime == true)
            {
                return n;
            }
            n += 2; //increment by 2 to only check odd numbers
        }
        
    }
    
    public Object getAtIndex(int i)
    {
        return data[i];
    }
    public int getLoad()
    {
        return load;
    }
    public double getLoadFactor()
    {
        return (double)(((double)(load))/((double)(size)));
    }
    public int getSize()
    {
        return size;
    }
    public int unalteredHash(int key)
    {
        //converts key to index for table
        int keyTwo = key;
        int numDigits = 0;
        while (keyTwo > 0)
        {
            keyTwo /= 10;
            numDigits += 1;
        }
        keyTwo = key;
        int h = 0;
        int digit = 1;
        while (keyTwo > 0)
        {
            h += (Math.pow(((keyTwo % 10) + 2), numDigits - digit + 1));
            digit += 1;
            keyTwo /= 10;
        }
        
        return h;
    }
    public int hash(int key)
    {
        //converts key to index for table
        int keyTwo = key;
        int numDigits = 0;
        while (keyTwo > 0)
        {
            keyTwo /= 10;
            numDigits += 1;
        }
        keyTwo = key;
        int h = 0;
        int digit = 1;
        while (keyTwo > 0)
        {
            h += (Math.pow(((keyTwo % 10) + 2), numDigits - digit + 1));
            digit += 1;
            keyTwo /= 10;
        }
        
        return (h % size);
    }
    public void insert(T d, int key)
    {
        if (load >= size)
        {
            //STILL NEED TO DO: resize & rehash table here
            resize(getNextPrime(size * 2));
        }
        int i = hash(key);
        if (keys[i].get(key) == null)
        {
            //current key to insert does not exist in list yet
            LinkedList<T> newList = new LinkedList<T>();
            newList.push(d);
            data[i].enqueue(newList);
            keys[i].enqueue(key);
        }
        else
        {
            //current key to insert already has a list at this location
            int indexOfCurrentKey = keys[i].getIndexOf(key);
            if (indexOfCurrentKey < 1)
            {
                //error in program if this occurs
                System.out.println("Error inserting with already existing key");
                return;
            }
            data[i].getAtIndex(indexOfCurrentKey).enqueue(d);
            //key doesn't need to be updated
        }
        load += 1;
    }
    public LinkedList<T> remove(int key)
    {
        int h = hash(key);
        if (keys[h].isEmpty())
        {
            return null;
        }
        int indexOfKey = keys[h].getIndexOf(key);
        if (indexOfKey >= 1)
        {
            //here we need to remove key and punch list from both arrays, 
                //and return the punch list
            LinkedList<T> tempList = data[h].remove(data[h].getAtIndex(indexOfKey));
                //tempList holds the list of punches, now removed from data
            keys[h].remove(key); //key is removed
            return tempList;
        }
        return null;
    }
    public int getKeyIndex(int key)
    {
        //'key' resides at location 'keys[h]' in a list; 
            //getKeyIndex returns index i where 'key' is in that list - 
            //still need hash function to get 'h' - index of array to 
            //reference for data array
        int h = hash(key);
        if (keys[h].isEmpty())
        {
            return -1;
        }
        int indexOfKey = keys[h].getIndexOf(key);
        if (indexOfKey >= 1)
        {
            return indexOfKey;
        }
        return -1;
    }
    
    public void resize(int s)
    {
        //s is new size
        LinkedList<Integer>[] newKeys = new LinkedList[s];
        LinkedList<LinkedList<T>>[] newData = new LinkedList[s];
        for (int a = 0; a < s; a += 1)
        {
            newData[a] = new LinkedList<LinkedList<T>>();
            newKeys[a] = new LinkedList<Integer>();
        }
        
        for (int a = 0; a < size; a += 1)
        {
            while (keys[a].isEmpty() == false)
            {
                int copyKey = keys[a].pop();
                int newHash = (unalteredHash(copyKey) % s);
                newData[newHash].enqueue(data[a].pop());
                newKeys[newHash].enqueue(copyKey);
            }
        }
        
        keys = newKeys;
        data = newData;
        size = s;
    }
    
    private String toStringOld()
    {
        String s = "";
        for (int a = 0; a < size; a += 1)
        {
            if (keys[a].getSize() > 1)
            {
                System.out.print("");
            }
            s += "\t" + keys[a].toString() + "\n" + data[a].toString() + "\n";
        }
        return s;
    }
    public String toString()
    {
        String s = "";
        
        for (int a = 0; a < size; a += 1)
        {
            if (keys[a].isEmpty() == false)
            {
                s += a + ": KEYS: {" + keys[a].toString() + "}; PUNCHES: {";
                for (int b = 1; b <= data[a].getSize(); b += 1)
                {
                    s += "{" + data[a].getAtIndex(b).toString() + "}";
                    if (b < data[a].getSize())
                    {
                        s += ", \n\t";
                    }
                }
                s += "}\n";
            }
        }
        
        return s;
    }
    /*public String[] toCSV()
    {
        String[] strings = new String[load];
        int currIndex = 0;
        LinkedList<LinkedList<T>> tempDoubleData = null;
        LinkedList<T> tempData = null;
        for (int a = 0; a < data.length; a += 1)
        {
            tempDoubleData = data[a];
            for (int b = 1; b < tempDoubleData.getSize(); b += 1)
            {
                tempData = tempDoubleData.getAtIndex(b);
                for (int c = 1; c < tempData.getSize(); c += 1)
                {
                    
                }
            }
        }
        return strings;
    }*/
}
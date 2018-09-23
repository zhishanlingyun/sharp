package org.sharp.sd.ds;

public interface IList<T> {

    public boolean add(T t);

    public T get(int index);

    public T remove(T t);

    /**
     * return first element
     * @return
     */
    public T poll();

    /**
     * return tail element
     * @return
     */
    public T tail();

    public int size();

    public boolean isEmpty();

}

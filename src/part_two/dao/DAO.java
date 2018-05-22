package part_two.dao;

public abstract class DAO<T> {
    public abstract T find(int t);

    public abstract boolean create(T object);

    public abstract boolean update(T object);

    public abstract boolean delete(T object);
}

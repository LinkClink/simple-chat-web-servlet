package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T send(T element);

    Optional<T> get(Long id);

    List<T> getAll();
}

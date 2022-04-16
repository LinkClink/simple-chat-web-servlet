package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T save(T element);

    Optional<T> get(Long id);

    List<T> getAll();
}

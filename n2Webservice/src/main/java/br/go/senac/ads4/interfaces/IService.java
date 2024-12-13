package br.go.senac.ads4.interfaces;

import br.go.senac.ads4.dto.BookDto;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface genérica para serviços.
 *
 * @param <T> - Dto
 * @param <N> - Tipo do identificador (ex.: Integer)
 */
public interface IService<T, N> {

    T create(T entity);

    T read(N id, SimpleJpaRepository<T, Integer> userRepository);

    @Transactional(readOnly = true)
    BookDto read(Integer id);

    List<T> read();

    T update(N id, T entity) throws InterruptedException;

    void delete(N id);
}

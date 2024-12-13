package br.go.senac.ads4.service;
import br.go.senac.ads4.dto.BookDto;
import br.go.senac.ads4.interfaces.IService;
import br.go.senac.ads4.mapper.BookMapper;
import br.go.senac.ads4.model.BookModel;
import br.go.senac.ads4.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class BookService implements IService<BookDto, Integer> {

    final BookRepository bookRepository;
    final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BookDto create(BookDto dto) {
        log.info("BookService::create");
        log.debug("Valores: {}", dto);

        BookModel bookModel = bookMapper.toModel(dto);
        BookModel bookModelGravado = bookRepository.save(bookModel);
        return bookMapper.toDto(bookModelGravado);
    }

    @Override
    public BookDto read(Integer id, SimpleJpaRepository<BookDto, Integer> userRepository) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto read(Integer id) {
        log.info("BookService::read(id)");
        log.debug("Valores: {}", id);

        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> read() {
        log.info("BookService::read()");
        log.debug("Valores: sem parâmetros");

        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BookDto update(Integer id, BookDto entity) throws InterruptedException {
        log.info("BookService::update");
        log.debug("Valores: {} e {}", id, entity);

        BookModel bookPresente = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));



        return bookMapper.toDto(bookRepository.save(bookPresente));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Integer id) {
        log.info("BookService::delete");
        log.debug("Valores: {}", id);

        BookModel bookToDelete = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        bookRepository.delete(bookToDelete);
    }
}

package br.go.senac.ads4.resource;

import br.go.senac.ads4.dto.BookDto;
import br.go.senac.ads4.interfaces.IResource;
import br.go.senac.ads4.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/books")
@Tag(name = "book", description = "Documentação relacionada ao recurso Book")
public class BookResource implements IResource<BookDto, Integer> {

    final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Cria um livro",
            description = "Método responsável por criar um livro no sistema",
            tags = {"livro"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = BookResource.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public BookDto create(@RequestBody BookDto entity) {
        log.info("BookResource::create");
        log.debug("Valores: {}", entity);
        return bookService.create(entity);
    }

    @Override
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Recupera um livro baseado em um identificador",
            description = "Método responsável por recuperar um livro no sistema baseado no identificador",
            tags = {"livro"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BookResource.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public BookDto get(@PathVariable Integer id) {
        log.info("BookResource::get(id)");
        log.debug("Valores: {}", id);
        return bookService.read(id);
    }

    @Override
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Recupera uma lista de livros",
            description = "Método responsável por recuperar uma lista de livros",
            tags = {"livro"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BookResource.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public List<BookDto> get() {
        log.info("BookResource::get");
        log.debug("Valores: sem parâmetro");
        return bookService.read();
    }

    @SneakyThrows
    @Override
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Atualiza todos os dados de um livro",
            description = "Método responsável por atualizar todos os dados de um livro.",
            tags = {"livro"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BookResource.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public BookDto update(@PathVariable Integer id, @RequestBody BookDto entity) {
        log.info("BookResource::update");
        log.debug("Valores: {} e {}", id, entity);
        return bookService.update(id, entity);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deleta um livro com base no identificador.",
            description = "Método responsável por deletar um livro com base no identificador.",
            tags = {"livro"})
    @ApiResponses({
            @ApiResponse(responseCode = "206", content = {@Content(schema = @Schema(implementation = BookResource.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public void delete(@PathVariable Integer id) {
        log.info("BookResource::delete");
        log.debug("Valores: {}", id);
        bookService.delete(id);
    }
}

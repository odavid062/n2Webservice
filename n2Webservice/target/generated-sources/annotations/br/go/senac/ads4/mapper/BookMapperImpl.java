package br.go.senac.ads4.mapper;

import br.go.senac.ads4.dto.BookDto;
import br.go.senac.ads4.model.BookModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T23:06:00-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(BookModel entity) {
        if ( entity == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        return bookDto;
    }

    @Override
    public BookModel toModel(BookDto dto) {
        if ( dto == null ) {
            return null;
        }

        BookModel bookModel = new BookModel();

        return bookModel;
    }

    @Override
    public List<BookDto> toDtoList(List<BookModel> all) {
        if ( all == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( all.size() );
        for ( BookModel bookModel : all ) {
            list.add( toDto( bookModel ) );
        }

        return list;
    }

    @Override
    public List<BookModel> toModelList(List<BookDto> all) {
        if ( all == null ) {
            return null;
        }

        List<BookModel> list = new ArrayList<BookModel>( all.size() );
        for ( BookDto bookDto : all ) {
            list.add( toModel( bookDto ) );
        }

        return list;
    }
}

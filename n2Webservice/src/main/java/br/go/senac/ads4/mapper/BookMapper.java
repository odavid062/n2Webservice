package br.go.senac.ads4.mapper;

import br.go.senac.ads4.dto.BookDto;
import br.go.senac.ads4.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(BookModel entity);

    BookModel toModel(BookDto dto);

    List<BookDto> toDtoList(List<BookModel> all);

    List<BookModel> toModelList(List<BookDto> all);
}
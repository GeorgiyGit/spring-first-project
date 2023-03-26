package program.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import program.DTOs.ProductDTOs.FullProductDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;
import program.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    SimpleProductDTO SimpleProductByProduct(Product product);


    FullProductDTO FullProductByProduct(Product product);
}

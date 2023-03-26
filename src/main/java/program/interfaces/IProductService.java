package program.interfaces;

import program.DTOs.ProductDTOs.FullProductDTO;
import program.DTOs.ProductDTOs.ProductCreateDTO;
import program.DTOs.ProductDTOs.ProductEditDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;

import java.util.List;

public interface IProductService {
    FullProductDTO create(ProductCreateDTO model);
    List<SimpleProductDTO> getSimple();
    FullProductDTO edit(int id, ProductEditDTO model);
    FullProductDTO getById(int id);

    FullProductDTO deleteById(int id);
}
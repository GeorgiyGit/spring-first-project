package program.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import program.DTOs.ProductDTOs.FullProductDTO;
import program.DTOs.ProductDTOs.ProductCreateDTO;
import program.DTOs.ProductDTOs.ProductEditDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;
import program.entities.Category;
import program.entities.Product;
import program.entities.ProductImage;
import program.interfaces.IProductService;
import program.mappers.CategoryMapper;
import program.mappers.ProductMapper;
import program.repositories.CategoryRepository;
import program.repositories.ProductImageRepository;
import program.repositories.ProductRepository;
import program.storage.IStorageService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    private final IStorageService storageService;
    private final ProductMapper productMapper;

    private final CategoryMapper categoryMapper;

    @Override
    public FullProductDTO create(ProductCreateDTO model) {
        var p = new Product();
        var categories = new ArrayList<Category>();
        p.setName(model.getName());
        p.setDescription(model.getDescription());
        p.setPrice(model.getPrice());
        p.setDateCreated(new Date(System.currentTimeMillis()));


        for(var c:model.getCategories()){
            var cNormal=categoryRepository.getById(c);
            categories.add(cNormal);
        }



        p.setCategories(categories);

        p.setDelete(false);
        productRepository.save(p);
        int priority = 1;
        for (var img : model.getFiles()) {
            var file = storageService.saveMultipartFile(img);
            ProductImage pi = new ProductImage();
            pi.setName(file);
            pi.setDateCreated(new Date(System.currentTimeMillis()));
            pi.setPriority(priority);
            pi.setDelete(false);
            pi.setProduct(p);
            productImageRepository.save(pi);
            priority++;
        }
        return null;
    }
    @Override
    public List<SimpleProductDTO> getSimple() {
        var products = productRepository.findAll();
        var result = new ArrayList<SimpleProductDTO>();
        for (var p:products) {
            var item = productMapper.SimpleProductByProduct(p);
            //for(var img : p.getImages())
                //item.getFiles().add(img.getName());
            item.setId(p.getId());
            result.add(item);
        }
        return result;
    }
    @Override
    public FullProductDTO getById(int id) {
        var productOptinal = productRepository.findById(id);
        if(productOptinal.isPresent())
        {
            var product = productOptinal.get();
            var data = productMapper.FullProductByProduct(product);
            //for(var img : product.getImages())
                //data.getImages().add(img.getName());
            return data;
        }
        return null;
    }

    @Override
    public FullProductDTO deleteById(int id) {
        Product product = productRepository.findById(id).get();
        for(var img : product.getImages())
            storageService.removeFile(img.getName());;
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public FullProductDTO edit(int id, ProductEditDTO model) {
        var p = productRepository.findById(id);
        //якщо по такому id - є продукт
        if(p.isPresent())
        {
            //отримуємо сам продукт
            var product = p.get();
            //Якщо користувач видадяв фото із списку - шукаємо фото по імені
            for (var name: model.getRemoveFiles()) {
                var pi = productImageRepository.findByName(name);
                if(pi!=null)
                {
                    productImageRepository.delete(pi); //видаляємо саме фото товару
                    storageService.removeFile(name); //видаляємо файли даного фото
                }
            }
            product.setName(model.getName()); //змінуюємо імя товару
            product.setDescription(model.getDescription());//змінуюємо опис товару
            product.setPrice(model.getPrice());//змінуюємо ціну товару

            var categories = new ArrayList<Category>();

            for(var c:model.getCategories()){
                var cNormal=categoryRepository.getById(c);
                categories.add(cNormal);
            }

            product.setCategories(categories);

            productRepository.save(product); //Зберігаємо дані про товар
            var productImages = product.getImages(); //Отримуємо список нових фото до товару
            int priority=1; //визначаємо пріорітет фото у послідовнссті
            for (var pi : productImages)
            {
                if(pi.getPriority()>priority) //шукаємо макисальний пріорітет
                    priority=pi.getPriority(); //нові фото ставимо у кінець черги.
            }
            priority++;
            ///Зберігаємо нові фото
            for (var img : model.getFiles()) {
                var file = storageService.saveMultipartFile(img);
                ProductImage pi = new ProductImage();
                pi.setName(file);
                pi.setDateCreated(new Date(System.currentTimeMillis()));
                pi.setPriority(priority);
                pi.setDelete(false);
                pi.setProduct(product);
                productImageRepository.save(pi);
                priority++;
            }
        }

        return null;
    }
}

package edu.yacoubi.inventoryapp.brand;

import edu.yacoubi.inventoryapp.category.CategoryRepository;
import edu.yacoubi.inventoryapp.exception.NotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public BrandService(final BrandRepository brandRepository,
                        final CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<BrandDTO> findAll() {
        final List<Brand> brands = brandRepository.findAll(Sort.by("id"));
        return brands.stream()
                .map(brand -> mapToDTO(brand, new BrandDTO()))
                .toList();
    }

    public BrandDTO get(final Integer id) {
        return brandRepository.findById(id)
                .map(brand -> mapToDTO(brand, new BrandDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final BrandDTO brandDTO) {
        final Brand brand = new Brand();
        mapToDTO(brand, brandDTO);
        return brandRepository.save(brand).getId();
    }

    public void update(final Integer id, final BrandDTO brandDTO) {
        final Brand brand = brandRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(brandDTO, brand);
        brandRepository.save(brand);
    }

    public void delete(final Integer id) {
        brandRepository.deleteById(id);
    }

    /* mapper */

    private BrandDTO mapToDTO(final Brand brand, final BrandDTO brandDTO) {
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        return brandDTO;
    }

    private Brand mapToEntity(final BrandDTO brandDTO, final Brand brand) {
        brand.setName(brandDTO.getName());
        return brand;
    }
}

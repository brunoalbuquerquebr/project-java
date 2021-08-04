package br.com.caelum.carangobom.impl;

import br.com.caelum.carangobom.domain.Brand;
import br.com.caelum.carangobom.form.BrandForm;
import br.com.caelum.carangobom.repository.BrandRepository;
import br.com.caelum.carangobom.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandsImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandForm brandForm;

    @Override
    public BrandForm saveBrand(BrandForm brandForm) {
        Brand brandSave = brandRepository.save(BrandForm.convertTypeDom(brandForm));
        return BrandForm.converDomainType(brandSave);
    }

    @Override
    public List<BrandForm> findAll() {
        List<Brand> listBrand = brandRepository.findAll();
        return BrandForm.converDomainDto(listBrand);
    }

    @Override
    public BrandForm findById(Long id) {
        Optional<Brand> brandById = brandRepository.findById(id);
        if(brandById.isPresent()) {
            return BrandForm.converDomainType(brandById.get());
        }
        else {
            return null;
        }
    }

    @Override
    public BrandForm updateBrand(Long id, BrandForm brandForm) {
        Optional<Brand> updateBrand = brandRepository.findById(id);
        if(updateBrand.isPresent()) {
            Brand brandSave = updateBrand.get();
            brandSave.setName(brandForm.getName());
            return BrandForm.converDomainType(brandRepository.saveAndFlush(brandSave));
        }
        else {
            return null;
        }


    }

    @Override
    public BrandForm deleteBrand(Long id) {
        Optional<Brand> deleteBrand = brandRepository.findById(id);
        if(deleteBrand.isPresent()) {
            Brand deleteBrans = deleteBrand.get();
            brandRepository.delete(deleteBrans);
            return BrandForm.converDomainType(deleteBrans);

        }
        else {
            return null;
        }
    }
}

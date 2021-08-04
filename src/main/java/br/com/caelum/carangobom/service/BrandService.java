package br.com.caelum.carangobom.service;

import br.com.caelum.carangobom.form.BrandForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    public BrandForm saveBrand(BrandForm brandForm);

    public List<BrandForm> findAll();

    public BrandForm findById(Long id);

    public BrandForm updateBrand(Long id, BrandForm brandForm);

    public BrandForm deleteBrand(Long id);



}

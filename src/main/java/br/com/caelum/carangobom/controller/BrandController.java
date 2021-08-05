package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.BrandForm;
import br.com.caelum.carangobom.repository.BrandRepository;
import br.com.caelum.carangobom.service.BrandService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandsService;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<List<BrandForm>> listBrand() {
        return new ResponseEntity<>(brandsService.findAll(), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<BrandForm> listBrandById(@PathVariable Long id) {
        BrandForm brandForm = brandsService.findById(id);
        if(brandForm != null) {
            return new ResponseEntity<>(brandForm, null, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(brandForm, null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<BrandForm> createBrand(@Valid @RequestBody BrandForm brandForm) {
        return new ResponseEntity<>(brandsService.saveBrand(brandForm), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<BrandForm> updateBrand(@PathVariable long id,  @Valid @RequestBody BrandForm brandForm) {
        BrandForm brand = brandsService.updateBrand(id, brandForm);
        if(brand != null) {
            return new ResponseEntity<>(brand, null, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(brand, null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<BrandForm> deleteBrand(@PathVariable Long id) {
        BrandForm brand = brandsService.deleteBrand(id);
        if (brand != null) {
            return new ResponseEntity<>(brand, null, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(brand, null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        List<ErroDeParametroOutputDto> l = new ArrayList<>();
        excecao.getBindingResult().getFieldErrors().forEach(e -> {
            ErroDeParametroOutputDto d = new ErroDeParametroOutputDto();
            d.setParametro(e.getField());
            d.setMensagem(e.getDefaultMessage());
            l.add(d);
        });
        ListaDeErrosOutputDto l2 = new ListaDeErrosOutputDto();
        l2.setErros(l);
        return l2;
    }

}

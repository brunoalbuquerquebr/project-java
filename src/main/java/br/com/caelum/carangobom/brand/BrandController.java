package br.com.caelum.carangobom.brand;

import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping()
    @ResponseBody
    @Transactional
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Brand> id(@PathVariable Long id) {
        Optional<Brand> m1 = brandRepository.findById(id);
        if (m1.isPresent()) {
            return ResponseEntity.ok(m1.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<Brand> cadastra(@Valid @RequestBody Brand m1, UriComponentsBuilder uriBuilder) {
        Brand m2 = brandRepository.save(m1);
        URI h = uriBuilder.path("/brands/{id}").buildAndExpand(m1.getId()).toUri();
        return ResponseEntity.created(h).body(m2);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Brand> altera(@PathVariable Long id, @Valid @RequestBody Brand m1) {
        Optional<Brand> m2 = brandRepository.findById(id);
        if (m2.isPresent()) {
            Brand m3 = m2.get();
            m3.setName(m1.getName());
            return ResponseEntity.ok(m3);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Brand> deleta(@PathVariable Long id) {
        Optional<Brand> m1 = brandRepository.findById(id);
        if (m1.isPresent()) {
            Brand m2 = m1.get();
            brandRepository.delete(m2);
            return ResponseEntity.ok(m2);
        } else {
            return ResponseEntity.notFound().build();
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
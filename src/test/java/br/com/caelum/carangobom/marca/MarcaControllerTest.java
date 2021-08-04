//package br.com.caelum.carangobom.marca;
//
//import br.com.caelum.carangobom.brand.Brand;
//import br.com.caelum.carangobom.brand.BrandController;
//import br.com.caelum.carangobom.brand.BrandRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//class MarcaControllerTest {
//
//    private BrandController marcaController;
//    private UriComponentsBuilder uriBuilder;
//
//    @Mock
//    private BrandRepository marcaRepository;
//
//    @BeforeEach
//    public void configuraMock() {
//        openMocks(this);
//
//        marcaController = new BrandController(marcaRepository);
//        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
//    }
//
//    @Test
//    void deveRetornarListaQuandoHouverResultados() {
//        List<Brand> marcas = List.of(
//            new Brand(1L, "Audi"),
//            new Brand(2L, "BMW"),
//            new Brand(3L, "Fiat")
//        );
//
//        when(marcaRepository.findAllByOrderByName())
//            .thenReturn(marcas);
//
//        List<Brand> resultado = marcaController.list();
//        assertEquals(marcas, resultado);
//    }
//
//    @Test
//    void deveRetornarMarcaPeloId() {
//        Brand audi = new Brand(1L, "Audi");
//
//        when(marcaRepository.findById(1L))
//            .thenReturn(Optional.of(audi));
//
//        ResponseEntity<Brand> resposta = marcaController.id(1L);
//        assertEquals(audi, resposta.getBody());
//        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//    }
//
//    @Test
//    void deveRetornarNotFoundQuandoRecuperarMarcaComIdInexistente() {
//        when(marcaRepository.findById(anyLong()))
//                .thenReturn(Optional.empty());
//
//        ResponseEntity<Brand> resposta = marcaController.id(1L);
//        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
//    }
//
//    @Test
//    void deveResponderCreatedELocationQuandoCadastrarMarca() {
//        Brand nova = new Brand("Ferrari");
//
//        when(marcaRepository.save(nova))
//            .then(invocation -> {
//                Brand marcaSalva = invocation.getArgument(0, Brand.class);
//                marcaSalva.setId(1L);
//
//                return marcaSalva;
//            });
//
//        ResponseEntity<Brand> resposta = marcaController.cadastra(nova, uriBuilder);
//        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
//        assertEquals("http://localhost:8080/marcas/1", resposta.getHeaders().getLocation().toString());
//    }
//
//    @Test
//    void deveAlterarNomeQuandoMarcaExistir() {
//        Brand audi = new Brand(1L, "Audi");
//
//        when(marcaRepository.findById(1L))
//            .thenReturn(Optional.of(audi));
//
//        ResponseEntity<Brand> resposta = marcaController.altera(1L, new Brand(1L, "NOVA Audi"));
//        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//
//        Brand marcaAlterada = resposta.getBody();
//        assertEquals("NOVA Audi", marcaAlterada.getName());
//    }
//
//    @Test
//    void naoDeveAlterarMarcaInexistente() {
//        when(marcaRepository.findById(anyLong()))
//                .thenReturn(Optional.empty());
//
//        ResponseEntity<Brand> resposta = marcaController.altera(1L, new Brand(1L, "NOVA Audi"));
//        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
//    }
//
//    @Test
//    void deveDeletarMarcaExistente() {
//        Brand audi = new Brand(1l, "Audi");
//
//        when(marcaRepository.findById(1L))
//            .thenReturn(Optional.of(audi));
//
//        ResponseEntity<Brand> resposta = marcaController.deleta(1L);
//        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        verify(marcaRepository).delete(audi);
//    }
//
//    @Test
//    void naoDeveDeletarMarcaInexistente() {
//        when(marcaRepository.findById(anyLong()))
//                .thenReturn(Optional.empty());
//
//        ResponseEntity<Brand> resposta = marcaController.deleta(1L);
//        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
//
//        verify(marcaRepository, never()).delete(any());
//    }
//
//}
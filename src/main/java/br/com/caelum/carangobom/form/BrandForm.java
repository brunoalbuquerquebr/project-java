package br.com.caelum.carangobom.form;

import br.com.caelum.carangobom.domain.Brand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class BrandForm {

    private Long id;

    @NotBlank
    private String name;

    public static List<BrandForm> converDomainDto(List<Brand> brand) {
        List<BrandForm> listType = new ArrayList<>();
        brand.forEach(brandDomain -> listType.add(converDomainType(brandDomain)));
        return listType;
    }

    public static BrandForm converDomainType(Brand brandDomain) {
        BrandForm brandType = new BrandForm();
        brandType.setId(brandDomain.getId());
        brandType.setName(brandDomain.getName());

        return brandType;
    }

    public static Brand convertTypeDom(BrandForm brand) {
        Brand brandDom = new Brand();
        brandDom.setId(brand.getId());
        brandDom.setName(brand.getName());

        return brandDom;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}

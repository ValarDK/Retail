package com.target.myretail.services;

import com.target.myretail.Model.Price;
import com.target.myretail.Model.Product;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceImplTests {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    public ProductServiceImplTests() {
    }

    @Before
    public void initiallize()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findProductsById() {
        Product product = getProduct();
        String id = product.getId();
        Optional<Product> optional = Optional.of(product);
        doReturn(optional).when(productRepository).findById(id);
        optional = productService.findProductsById(id);

        assertTrue("valid results returned", optional.isPresent());
        assertNotNull(optional.get());
        assertEquals(id, optional.get().getId());
        assertEquals("USD", optional.get().getCurrentPrice().getCurrencyCode());

        verify(productRepository).findById(id);
    }

    private Product getProduct() {
        Product product = new Product();
        Price price = new Price();
        product.setId("13860428");
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        price.setCurrencyCode("USD");
        price.setValue(13.49);
        product.setCurrentPrice(price);

        return product;
    }
}

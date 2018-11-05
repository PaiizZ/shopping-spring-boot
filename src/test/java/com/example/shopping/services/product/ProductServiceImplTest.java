package com.example.shopping.services.product;

import com.example.shopping.entities.Product;
import com.example.shopping.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    private ProductService productService;
    private Product product;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productService = new ProductServiceImpl(productRepository);
        product = new Product().setName("Water").setPrice(10F);
    }

    @Test
    public void createProduct() {
        //Arrange
        when(productRepository.save(any())).thenReturn(this.product);

        //Act
        Product productResponse = productService.createProduct(this.product);

        //Assert
        assertThat(productResponse.getName()).isEqualTo("Water");
        assertThat(productResponse.getPrice()).isEqualTo(10F);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void getProductById() {
        //Arrange
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(this.product));

        //Act
        Product productResponse = productService.getProductById(anyLong());

        //Assert
        assertThat(productResponse.getName()).isEqualTo("Water");
        assertThat(productResponse.getPrice()).isEqualTo(10F);

        verify(productRepository, times(1)).findById(anyLong());
    }
}
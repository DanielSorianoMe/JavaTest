package com.company.commodity;

import com.company.commodity.controller.request.PriceRequest;
import com.company.commodity.controller.response.PriceResponse;
import com.company.commodity.entity.Brand;
import com.company.commodity.entity.Price;
import com.company.commodity.repository.PriceRepository;
import com.company.commodity.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @InjectMocks
    private PriceService priceService;
    private List<Price> prices = new ArrayList<>();
    private LocalDateTime appDate = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("ZARA");

        for (int i = 0; i < 2; i++) {
            int valor = i + 1;
            Price price = new Price();
            price.setId(Long.valueOf(valor));
            price.setPriority(valor);
            price.setPriceList(Long.valueOf(valor));
            price.setPrice(Double.valueOf(20 * valor));
            price.setProductId(35455L);
            price.setBrand(brand);
            prices.add(price);
            System.out.println(price);
        }
    }

    @Test
    public void givenPriceRequest_whenGettingPrice_thenReturnPriceResponse() {
        given(priceRepository.findByBrandProductAndDate(1L, 35455L, appDate)).willReturn(prices);

        PriceRequest priceRequest = PriceRequest.builder().appDate(appDate).brandId(1L).productId(35455L).build();
        PriceResponse priceResponse = priceService.getPrice(priceRequest);

        assertEquals(1L, priceResponse.getBrandId());
        assertEquals(2L, priceResponse.getPrice());
        assertEquals(appDate, priceResponse.getAppDate());
        assertEquals(40L, priceResponse.getFinalPrice());
        assertEquals(35455L, priceResponse.getProductId());
    }

}
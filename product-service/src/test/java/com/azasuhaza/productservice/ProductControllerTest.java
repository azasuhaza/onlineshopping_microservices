package com.azasuhaza.productservice;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.azasuhaza.productservice.controller.ProductController;
import com.azasuhaza.productservice.dto.ErrorResponse;
import com.azasuhaza.productservice.dto.ProductResponse;
import com.azasuhaza.productservice.exceptions.ProductException;
import com.azasuhaza.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = ProductControllerTest.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-unittest.properties")
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;

	@Before
	public void before() {
	    MockitoAnnotations.initMocks(this);
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}
	
	@Test
	public void shouldGetAllProducts() throws Exception {

		List<ProductResponse> expectedSuccessGetProductResponse = getAllProductsResponse();
		
		Mockito.when(productService.getAllProducts()).thenReturn(expectedSuccessGetProductResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/product").accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
				
		String contentAsString = mvcResult.getResponse().getContentAsString();
		
		@SuppressWarnings("unchecked")
		List<ProductResponse> prdResponse= (List<ProductResponse>) new ObjectMapper().readValue(contentAsString, ProductResponse.class);
		assertThat(prdResponse).isEqualTo(expectedSuccessGetProductResponse);
		
	}
	
	private List<ProductResponse> getAllProductsResponse() {
		
		List<ProductResponse> expected = new ArrayList<ProductResponse>();
		expected.add(new ProductResponse("1","Handbag","Prelove LV", new BigDecimal("50008.15")));
		expected.add(new ProductResponse("2","Shirt","polka dots", new BigDecimal("508.95")));
		return expected;
	}

    @Test
    void shouldFailGetAllProducts() throws Exception {
//        api: GET /api/product ==> 400 : ErrorResponse
        ErrorResponse expected = getErrorResponse("1001", "Products not found");
        when(productService.getAllProducts()).thenThrow(new ProductException("1001", "Products not found"));

        MvcResult mvcResult = mockMvc.perform(get("/api/product")).andDo(print()).andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse = new ObjectMapper().readValue(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isEqualTo(expected);

    }

	private ErrorResponse getErrorResponse(String string, String string2) {
		
		ErrorResponse expected= new ErrorResponse();
		expected.setCode(string);
		expected.setMessage(string2);
		return expected;
	}
}

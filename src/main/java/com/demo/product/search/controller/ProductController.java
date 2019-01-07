package com.demo.product.search.controller;

import com.demo.product.search.domain.Product;
import com.demo.product.search.repository.ProductRepository;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API to query and ingest product info
 */
@Validated
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductRepository productRepository;

  @Autowired
  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Ingest list of products to the index
   */
  //@PreAuthorize(value = "hasRole('ADMIN')")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> save(@Valid @RequestBody List<Product> products) {
    return this.productRepository.saveAll(products);
  }

  /**
   * Update an existing product
   */
  //@PreAuthorize(value = "hasRole('ADMIN')")
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product update(@PathVariable("id") String id, @Valid @RequestBody Product product) {
    return this.productRepository.save(product);
  }

  /**
   * Partial update of product
   */
  //@PreAuthorize(value = "hasRole('ADMIN')")
  @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product patch(@PathVariable("id") String id, @RequestBody Map<String, Object> productMap) {
    Product existingProduct = this.productRepository.findById(id)
        .get();
    existingProduct.update(productMap);
    return this.productRepository.save(existingProduct);
  }

  /**
   * Query the index to retrieve the product information
   *
   * @param query query based on Elastic Search <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html">Query
   * Search Query</a>
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<Product> query(@RequestParam(value = "query", defaultValue = "*") String query,
      Pageable pageable) {
    return productRepository.search(QueryBuilders.queryStringQuery(query), pageable);
  }

}

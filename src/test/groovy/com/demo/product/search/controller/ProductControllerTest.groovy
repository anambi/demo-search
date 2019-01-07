package com.demo.product.search.controller


import spock.lang.Specification

class ProductControllerTest extends Specification {
    com.demo.product.search.repository.ProductRepository productRepository = Mock()
    ProductController productController = new ProductController(productRepository)

    def 'ingest product'() {
        given:
        com.demo.product.search.domain.Product product = new com.demo.product.search.domain.Product(id: "id123", name: "Test", description: "test description",
                manufacturer: "test manufacturer", price: 12.99)
        when:
        def returned = productController.save([product])

        then:
        1 * productRepository.saveAll([product]) >> [product]
        returned == [product]
    }
}

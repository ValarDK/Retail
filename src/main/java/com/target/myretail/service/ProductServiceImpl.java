package com.target.myretail.service;

import com.google.common.collect.Lists;
import com.target.myretail.Model.Product;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.utils.CommonConstants;
import com.target.myretail.utils.PageableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private PageableInfo pageInfo = null;

    @PostConstruct
    public void init() {
        pageInfo = new PageableInfo();
    }

    @Override
    public Optional<Product> findProductsById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductsById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteProducts() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> findAll(int page, int size) {
        if (page <= 0)
            page = CommonConstants.PAGE_DEFAULT;

        if (size <= 0)
            size = CommonConstants.SIZE_DEFAULT;
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by("updateDate").descending());
        Page<Product> pageObj = productRepository.findAll(pageable);

        pageInfo.setPage(pageObj.getPageable().getPageNumber()).setMaxSize(pageObj.getSize())
                .setCount(pageObj.getNumberOfElements()).setCount((int) pageObj.getTotalElements());
        return Lists.newArrayList(pageObj);
    }

    @Override
    public List<Product> findAll() { return productRepository.findAll(); }

    @Override
    public Product updateProduct(Product product) { return productRepository.save(product); }

}

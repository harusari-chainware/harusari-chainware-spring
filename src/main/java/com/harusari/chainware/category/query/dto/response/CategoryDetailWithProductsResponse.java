package com.harusari.chainware.category.query.dto.response;

import com.harusari.chainware.common.dto.Pagination;
import com.harusari.chainware.product.query.dto.response.ProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryDetailWithProductsResponse {
    private CategoryMetaInfoWithCountResponse categoryMeta;

    private TopCategoryOnlyWithCountResponse topCategory;

    private List<ProductDto> products;

    private Pagination pagination;
}
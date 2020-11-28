package com.itgarden.service.bo;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.common.staticdata.Constants;
import com.itgarden.dto.CategoryInfo;
import com.itgarden.entity.Category;
import com.itgarden.mapper.CategoryMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.CategoryRepository;
import com.itgarden.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Service
public class CategoryService extends BaseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    public ResponseMessage save(CategoryInfo categoryInfo) {
        Category category = CategoryMapper.INSTANCE.categoryInfoToCategory(categoryInfo);
        if(StringUtils.isEmpty(category.getCategoryCode())) {
            String categoryCode = codeGenerator.newCode(CodeType.CATEGORY_CODE);
            category.setCategoryCode(categoryCode);
        }
        Category newCategory = categoryRepository.save(category);
        CategoryInfo categoryInfoResponse = CategoryMapper.INSTANCE.categoryToCategoryInfo(newCategory);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfoResponse, "Category Saved" +
                " Successfully", Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(String id) throws Exception {
        Category category = categoryRepository.findById(Long.parseLong(id)).orElse(null);
        CategoryInfo categoryInfoResponse = CategoryMapper.INSTANCE.categoryToCategoryInfo(category);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfoResponse, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category:categories) {
            CategoryInfo categoryInfo = CategoryMapper.INSTANCE.categoryToCategoryInfo(category);
            categoryInfos.add(categoryInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfos,Constants.SUCCESS_STATUS,Constants.INFO_TYPE);
        return responseMessage;
    }
}

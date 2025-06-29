package com.example.LibManagerSys.service.interfaces;

import com.example.LibManagerSys.dto.request.CategorySaveRequest;
import com.example.LibManagerSys.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    CategoryResponse save(CategorySaveRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id, CategorySaveRequest request);

    String delete(Long id);
}


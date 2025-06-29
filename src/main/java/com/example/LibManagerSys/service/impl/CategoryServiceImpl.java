package com.example.LibManagerSys.service.impl;

import com.example.LibManagerSys.core.exception.NotFoundException;
import com.example.LibManagerSys.dto.request.CategorySaveRequest;
import com.example.LibManagerSys.dto.response.CategoryResponse;
import com.example.LibManagerSys.entity.Category;
import com.example.LibManagerSys.repository.BookRepository;
import com.example.LibManagerSys.repository.CategoryRepository;
import com.example.LibManagerSys.service.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse save(CategorySaveRequest request) {
        Category category = modelMapper.map(request, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CategoryResponse.class))
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Long id, CategorySaveRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public String delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found: " + id);
        }

        boolean hasBooks = bookRepository.existsByCategories_Id(id);
        if (hasBooks) {
            return "This category has associated books. Unable to delete.";
        }

        categoryRepository.deleteById(id);
        return "Category has been deleted successfully.";
    }
}


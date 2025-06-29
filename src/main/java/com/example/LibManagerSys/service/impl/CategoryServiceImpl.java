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

/**
 * Service implementation for managing categories in the library system.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    /**
     * Repository for Category entity operations, injected via constructor.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Repository for Book entity operations, injected via constructor.
     */
    private final BookRepository bookRepository;

    /**
     * ModelMapper for mapping between DTOs and entities, injected via constructor.
     */
    private final ModelMapper modelMapper;

    /**
     * Saves a new category using data from CategorySaveRequest and returns the response DTO.
     *
     * @param request the request object containing category data
     * @return the saved category as a response DTO
     */
    @Override
    public CategoryResponse save(CategorySaveRequest request) {
        Category category = modelMapper.map(request, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    /**
     * Retrieves all categories and maps them to response DTOs.
     *
     * @return a list of all categories as response DTOs
     */
    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CategoryResponse.class))
                .toList();
    }

    /**
     * Retrieves a category by ID, throws NotFoundException if not found.
     *
     * @param id the ID of the category to retrieve
     * @return the found category as a response DTO
     */
    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));
        return modelMapper.map(category, CategoryResponse.class);
    }

    /**
     * Updates an existing category by ID with new data from CategorySaveRequest.
     *
     * @param id      the ID of the category to update
     * @param request the request object containing new category data
     * @return the updated category as a response DTO
     */
    @Override
    public CategoryResponse update(Long id, CategorySaveRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    /**
     * Deletes a category by ID, checks for associated books, and returns a message.
     *
     * @param id the ID of the category to delete
     * @return a message indicating the result of the delete operation
     */
    @Override
    public String delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found: " + id);
        }

        // Check if the category has associated books before deleting
        boolean hasBooks = bookRepository.existsByCategories_Id(id);
        if (hasBooks) {
            return "This category has associated books. Unable to delete.";
        }

        categoryRepository.deleteById(id);
        return "Category has been deleted successfully.";
    }
}


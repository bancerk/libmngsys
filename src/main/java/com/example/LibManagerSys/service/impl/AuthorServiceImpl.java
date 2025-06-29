package com.example.LibManagerSys.service.impl;

import com.example.LibManagerSys.core.exception.NotFoundException;
import com.example.LibManagerSys.dto.request.AuthorSaveRequest;
import com.example.LibManagerSys.dto.response.AuthorResponse;
import com.example.LibManagerSys.entity.Author;
import com.example.LibManagerSys.repository.AuthorRepository;
import com.example.LibManagerSys.service.interfaces.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing authors.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    /**
     * Repository for Author entity operations, injected via constructor.
     */
    private final AuthorRepository authorRepository;

    /**
     * ModelMapper for mapping between DTOs and entities, injected via constructor.
     */
    private final ModelMapper modelMapper;

    /**
     * Saves a new author using data from AuthorSaveRequest and returns the response DTO.
     *
     * @param request the request object containing author data
     * @return the response DTO containing saved author data
     */
    @Override
    public AuthorResponse save(AuthorSaveRequest request) {
        Author author = modelMapper.map(request, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    /**
     * Retrieves all authors and maps them to response DTOs.
     *
     * @return a list of response DTOs containing all authors
     */
    @Override
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream().map(author -> modelMapper.map(author, AuthorResponse.class)).toList();
    }

    /**
     * Retrieves an author by ID, throws NotFoundException if not found.
     *
     * @param id the ID of the author to retrieve
     * @return the response DTO containing the author data
     */
    @Override
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found: " + id));
        return modelMapper.map(author, AuthorResponse.class);
    }

    /**
     * Updates an existing author by ID with new data from AuthorSaveRequest.
     *
     * @param id      the ID of the author to update
     * @param request the request object containing new author data
     * @return the response DTO containing updated author data
     */
    @Override
    public AuthorResponse update(Long id, AuthorSaveRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found: " + id));

        author.setName(request.getName());
        author.setBirthDate(request.getBirthDate());
        author.setCountry(request.getCountry());

        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    /**
     * Deletes an author by ID, throws NotFoundException if not found.
     *
     * @param id the ID of the author to delete
     */
    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found: " + id);
        }
        authorRepository.deleteById(id);
    }
}
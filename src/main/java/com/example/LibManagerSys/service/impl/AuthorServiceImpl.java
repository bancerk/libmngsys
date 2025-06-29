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

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorResponse save(AuthorSaveRequest request) {
        Author author = modelMapper.map(request, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream().map(author -> modelMapper.map(author, AuthorResponse.class)).toList();
    }

    @Override
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found: " + id));
        return modelMapper.map(author, AuthorResponse.class);
    }

    @Override
    public AuthorResponse update(Long id, AuthorSaveRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found: " + id));

        author.setName(request.getName());
        author.setBirthDate(request.getBirthDate());
        author.setCountry(request.getCountry());

        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found: " + id);
        }
        authorRepository.deleteById(id);
    }
}
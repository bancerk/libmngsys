package com.example.LibManagerSys.service.interfaces;

import com.example.LibManagerSys.dto.request.AuthorSaveRequest;
import com.example.LibManagerSys.dto.response.AuthorResponse;

import java.util.List;

public interface IAuthorService {

    AuthorResponse save(AuthorSaveRequest request);

    List<AuthorResponse> getAll();

    AuthorResponse getById(Long id);

    AuthorResponse update(Long id, AuthorSaveRequest request);

    void delete(Long id);
}


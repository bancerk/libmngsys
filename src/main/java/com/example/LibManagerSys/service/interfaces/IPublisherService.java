package com.example.LibManagerSys.service.interfaces;

import com.example.LibManagerSys.dto.request.PublisherSaveRequest;
import com.example.LibManagerSys.dto.response.PublisherResponse;

import java.util.List;

public interface IPublisherService {

    PublisherResponse save(PublisherSaveRequest request);

    List<PublisherResponse> getAll();

    PublisherResponse getById(Long id);

    PublisherResponse update(Long id, PublisherSaveRequest request);

    void delete(Long id);
}

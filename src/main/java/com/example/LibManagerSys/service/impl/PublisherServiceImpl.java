package com.example.LibManagerSys.service.impl;

import com.example.LibManagerSys.core.exception.NotFoundException;
import com.example.LibManagerSys.dto.request.PublisherSaveRequest;
import com.example.LibManagerSys.dto.response.PublisherResponse;
import com.example.LibManagerSys.entity.Publisher;
import com.example.LibManagerSys.repository.PublisherRepository;
import com.example.LibManagerSys.service.interfaces.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing publishers.
 */
@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements IPublisherService {

    /**
     * Repository for Publisher entity operations, injected via constructor.
     */
    private final PublisherRepository publisherRepository;

    /**
     * ModelMapper for mapping between DTOs and entities, injected via constructor.
     */
    private final ModelMapper modelMapper;

    /**
     * Saves a new publisher using data from PublisherSaveRequest and returns the response DTO.
     *
     * @param request the request object containing publisher data
     * @return the saved publisher as a response DTO
     */
    @Override
    public PublisherResponse save(PublisherSaveRequest request) {
        Publisher publisher = modelMapper.map(request, Publisher.class);
        return modelMapper.map(publisherRepository.save(publisher), PublisherResponse.class);
    }

    /**
     * Retrieves all publishers and maps them to response DTOs.
     *
     * @return a list of all publishers as response DTOs
     */
    @Override
    public List<PublisherResponse> getAll() {
        return publisherRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PublisherResponse.class))
                .toList();
    }

    /**
     * Retrieves a publisher by ID, throws NotFoundException if not found.
     *
     * @param id the ID of the publisher to retrieve
     * @return the found publisher as a response DTO
     */
    @Override
    public PublisherResponse getById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publisher not found: " + id));
        return modelMapper.map(publisher, PublisherResponse.class);
    }

    /**
     * Updates an existing publisher by ID with new data from PublisherSaveRequest.
     *
     * @param id      the ID of the publisher to update
     * @param request the request object containing new publisher data
     * @return the updated publisher as a response DTO
     */
    @Override
    public PublisherResponse update(Long id, PublisherSaveRequest request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publisher not found: " + id));

        publisher.setName(request.getName());
        publisher.setEstablishmentYear(request.getEstablishmentYear());
        publisher.setAddress(request.getAddress());

        return modelMapper.map(publisherRepository.save(publisher), PublisherResponse.class);
    }

    /**
     * Deletes a publisher by ID, throws NotFoundException if not found.
     *
     * @param id the ID of the publisher to delete
     */
    @Override
    public void delete(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new NotFoundException("Publisher not found: " + id);
        }
        publisherRepository.deleteById(id);
    }
}

package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private EventRepository repository;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable){
        Page<EventDTO> list = service.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
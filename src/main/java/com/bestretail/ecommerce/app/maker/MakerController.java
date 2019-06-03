package com.bestretail.ecommerce.app.maker;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MakerController {
    private final MakerService service;

    public MakerController(MakerService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Maker add(@Valid @RequestBody Maker maker) {
        return service.addMaker(maker);
    }

    @GetMapping("/{id}")
    public Maker getMaker(@PathVariable("id") int id) {
        return service.getMaker(id);
    }

    @GetMapping("/all")
    public List<Maker> getALl() {
        return service.getAll();
    }

}

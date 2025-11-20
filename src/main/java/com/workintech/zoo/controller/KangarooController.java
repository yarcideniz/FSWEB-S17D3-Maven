package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }
    public KangarooController() {
        System.out.println("KangarooController initialized");
    }

    @GetMapping("")
    public List<Kangaroo> getKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable("id") Integer id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if(kangaroo == null) {
            throw new ZooException("Kangaroo bulunamadı", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping("")
    //@ResponseStatus(HttpStatus.CREATED)
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getId() == null) {
            throw  new ZooException("Kangaroo id'siz gönderilemez", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo replaceOrCreate(@PathVariable("id") Integer id, @RequestBody Kangaroo kangaroo) {
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo removeKangaroo(@PathVariable("id") Integer id) {
        Kangaroo kangaroo = kangaroos.remove(id);
        return kangaroo;
    }
}

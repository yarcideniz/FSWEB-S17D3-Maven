package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }
    public KoalaController() {
        System.out.println("KoalaController initialized");
    }

    @GetMapping("")
    public List<Koala> getKoala() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable("id") Integer id) {
        if(!koalas.containsKey(id)) {
            throw new ZooException("İlgili id mevcut değil: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping("")
    //@ResponseStatus(HttpStatus.CREATED)
    public Koala createKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala replaceOrCreate(@PathVariable("id") Integer id, @RequestBody Koala koala) {
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala removeKoala(@PathVariable("id") Integer id) {
        Koala koala = koalas.remove(id);
        return koala;
    }
}

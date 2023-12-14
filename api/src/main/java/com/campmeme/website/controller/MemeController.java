package com.campmeme.website.controller;

import com.campmeme.website.repository.MemeRepository;
import com.campmeme.website.entity.Meme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/meme")
public class MemeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemeController.class);

    MemeRepository memeRepository;

    @Autowired
    public MemeController(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    @GetMapping("/{id}")
    public Meme getPersonById(@PathVariable long id) {
        return memeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Meme> getAll() {
        return memeRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("suh");
    }


    @PostMapping("/post")
    public ResponseEntity<?> createPerson(@RequestBody Meme meme) {
        LOGGER.debug("MEME POSTED!");

        Map<String, Boolean> response = new HashMap<>();
        response.put("acceptance", true);
        return ResponseEntity.ok(response);
        //return memeRepository.save(meme);
    }
}

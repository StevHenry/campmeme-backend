package com.campmeme.website.controller;

import com.campmeme.website.entity.Meme;
import com.campmeme.website.request.MemeCreationResponse;
import com.campmeme.website.request.MemePostRequest;
import com.campmeme.website.request.OperationFailed;
import com.campmeme.website.request.QueryRequest;
import com.campmeme.website.service.MemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


@RestController
@RequestMapping("/meme")
public class MemeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemeController.class);

    private final MemeService service;

    @Autowired
    public MemeController(MemeService memeService) {
        this.service = memeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable long id) {
        Optional<Meme> meme = service.getMemeInfo(id);
        if (meme.isEmpty()) {
            return ResponseEntity.ok(new OperationFailed("Identifiant de meme \"%d\" inconnu.".formatted(id)));
        }
        return ResponseEntity.ok(meme.get());
    }


    @PostMapping("/post")
    public ResponseEntity<?> createMeme(@RequestBody MemePostRequest postRequest) {
        Optional<OperationFailed> optFailed = service.checkRequest(postRequest);
        if (optFailed.isPresent()) {
            return ResponseEntity.ok(optFailed.get());
        }
        if (service.checkFilePathExistence(postRequest.file_path())) {
            return ResponseEntity.ok(new OperationFailed("Le meme existe déjà sous l'identifiant %d"
                    .formatted(service.getMemeId(postRequest.file_path()))));
        }
        if (!service.hasHTTPPrefix(postRequest.file_path())) {
            return ResponseEntity.ok(new OperationFailed("L'URL doit être de protocole HTTP ou HTTPS!"));
        }
        if (!service.hasExtension(postRequest.file_path(), "jpg", "jpeg", "png", "gif", "svg", "webp", "apng")) {
            return ResponseEntity.ok(new OperationFailed("L'URL doit mener vers un fichier jpg / " +
                    "jpeg / png / gif / svp / webp / apng !"));
        }
        Meme meme = service.createMeme(postRequest);
        return ResponseEntity.ok(new MemeCreationResponse(meme.getId()));
    }

    @PostMapping("/query")
    public List<Meme> query(@RequestBody QueryRequest query) {
        List<String> tags = List.of(query.query().toLowerCase(Locale.ROOT).split(" "));
        return service.findByTags(tags);
    }

    @GetMapping("/trend")
    public ResponseEntity<?> trends() {
        return ResponseEntity.ok(service.getTrendMemes());
    }

    @GetMapping("/random")
    public ResponseEntity<?> random(@RequestParam int quantity) {
        return ResponseEntity.ok(service.getRandomDocuments(quantity));
    }
}

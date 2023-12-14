package com.campmeme.website.service;

import com.campmeme.website.entity.Meme;
import com.campmeme.website.repository.MemeRepository;
import com.campmeme.website.request.MemePostRequest;
import com.campmeme.website.request.OperationFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MemeService {

    private final MemeRepository memeRepository;
    private final UserService userService;

    @Autowired
    public MemeService(MemeRepository memeRepository, UserService userService) {
        this.memeRepository = memeRepository;
        this.userService = userService;
    }

    public Optional<Meme> getMemeInfo(long memeId) {
        return memeRepository.findById(memeId);
    }

    public Meme createMeme(MemePostRequest request) {
        Meme meme = new Meme(request.file_path(), request.tags(), 0, request.contributor());
        memeRepository.save(meme);
        return meme;
    }

    public boolean checkFilePathExistence(String filePath) {
        return memeRepository.findByFilePath(filePath).isPresent();
    }

    public long getMemeId(String filePath) {
        Meme meme = memeRepository.findByFilePath(filePath).orElseThrow();
        return meme.getId();
    }

    public Optional<OperationFailed> checkRequest(MemePostRequest postRequest) {
        if (isStringEmpty(postRequest.contributor())) {
            return Optional.of(new OperationFailed("Le champs \"contributor\" ne peut pas être vide."));
        } else if (userService.getUserById(postRequest.contributor()).isEmpty()) {
            return Optional.of(new OperationFailed("Le contributeur {\"id\":\"%s\"} n'existe pas!"
                    .formatted(postRequest.contributor())));
        }
        if (isStringEmpty(postRequest.file_path())) {
            return Optional.of(new OperationFailed("Le champs \"file_path\" ne peut pas être vide!"));
        }
        if (postRequest.tags() == null || postRequest.tags().isEmpty()) {
            return Optional.of(new OperationFailed("Le champs \"tags\" ne peut pas être vide!"));
        } else {
            Iterator<String> tags = postRequest.tags().iterator();
            while (tags.hasNext()) {
                String tag = tags.next();
                if (isStringEmpty(tag)) {
                    tags.remove();
                }
            }
            if (postRequest.tags().isEmpty()) {
                return Optional.of(new OperationFailed("Le champs \"tags\" ne peut " +
                        "pas contenir uniquement des champs vides!"));
            }
        }
        return Optional.empty();
    }

    private boolean isStringEmpty(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    public boolean hasHTTPPrefix(String filePath){
        return filePath.startsWith("http://") || filePath.startsWith("https://");
    }
    public boolean hasExtension(String filePath, String... extensions){
        for(String str : extensions){
            if(filePath.endsWith('.' + str))
                return true;
        }
        return false;
    }

    public List<Meme> getTrendMemes(){
        return memeRepository.findByOrderByLikeCountDesc(Limit.of(15));
    }
}

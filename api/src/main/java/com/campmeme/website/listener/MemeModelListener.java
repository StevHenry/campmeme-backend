package com.campmeme.website.listener;

import com.campmeme.website.entity.Meme;
import com.campmeme.website.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MemeModelListener extends AbstractMongoEventListener<Meme> {

    private final SequenceGeneratorService service;

    @Autowired
    public MemeModelListener(SequenceGeneratorService service) {
        this.service = service;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Meme> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(service.generateSequence(Meme.SEQUENCE_NAME));
        }
    }

}

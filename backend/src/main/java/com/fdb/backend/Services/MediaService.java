package com.fdb.backend.Services;

import com.fdb.backend.Entities.Media;
import com.fdb.backend.Entities.Question;
import com.fdb.backend.Repositories.MediaRepository;
import com.fdb.backend.Repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Media> getMediaById(int mediaId) {
        return mediaRepository.findById(mediaId);
    }

    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }

    public void assignMediaToQuestion(int questionId, int mediaId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new EntityNotFoundException("Media not found with id: " + mediaId));

        media.setQuestion(question);
        mediaRepository.save(media);
    }

}


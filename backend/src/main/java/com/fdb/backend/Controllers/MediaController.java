package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Media;
import com.fdb.backend.Services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<Media> getMediaById(@PathVariable int mediaId) {
        Optional<Media> media = mediaService.getMediaById(mediaId);
        return media.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Media> saveMedia(@RequestBody Media media) {
        Media savedMedia = mediaService.saveMedia(media);
        return new ResponseEntity<>(savedMedia, HttpStatus.CREATED);
    }

    @PostMapping("{mediaID}/assignTo/{questionId}")
    public ResponseEntity<String> assignMediaToQuestion(@PathVariable int questionId, @PathVariable int mediaID) {
        mediaService.assignMediaToQuestion(questionId, mediaID);
        return ResponseEntity.ok("Media assigned to question successfully.");
    }

}
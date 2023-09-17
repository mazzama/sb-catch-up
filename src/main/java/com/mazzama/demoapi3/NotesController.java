package com.mazzama.demoapi3;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NotesRepository repository;

    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }


    @GetMapping()
    public List<Notes> getNotes() {
        try {
            List<Notes> notes = this.repository.findAll();
            return notes;
        } catch (Exception ex) {
            return Arrays.asList();
        }
    }

    @GetMapping("/{id}")
    public Notes getNoteById(@PathVariable("id") Long id) {
        try {
            Optional<Notes> note = this.repository.findById(id);

            return note.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @PostMapping
    public String post(@RequestBody Notes note) {
        this.repository.save(note);
        return "Success";
    }




}

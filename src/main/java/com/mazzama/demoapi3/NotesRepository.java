package com.mazzama.demoapi3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
}

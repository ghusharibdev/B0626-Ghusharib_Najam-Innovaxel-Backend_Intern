package org.example.eventregistrationapi.repository;

import jakarta.persistence.LockModeType;
import org.example.eventregistrationapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventNameIgnoreCase(String eventName);

    List<Event> findAllByOrderByEventDateAsc();

    List<Event> findByEventDateAfterOrderByEventDateAsc(LocalDateTime now);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Event> findByEventId(Long eventId);
}

package jf.service.weddinginvitationservice.repository;

import jf.service.weddinginvitationservice.model.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsvpRepository extends JpaRepository<Rsvp, Integer> {
}

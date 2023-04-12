package jf.service.weddinginvitationservice.repository;

import jf.service.weddinginvitationservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findById(UUID id);
}

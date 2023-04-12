package jf.service.weddinginvitationservice.repository;

import jf.service.weddinginvitationservice.model.UserWishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserWishRepository extends JpaRepository<UserWishes, Integer> {
}

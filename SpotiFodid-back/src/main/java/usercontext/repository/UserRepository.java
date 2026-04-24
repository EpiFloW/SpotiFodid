package usercontext.repository;

import java.util.Optional;

import fodid.SpotiFodid_back.usercontext.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import usercontext.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}

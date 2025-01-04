package com.hay.user.registrationservices.repository;

import com.hay.user.registrationservices.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findByIdAndIsDeletedFalse(Long id);
}

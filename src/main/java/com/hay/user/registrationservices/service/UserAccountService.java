package com.hay.user.registrationservices.service;

import com.hay.user.registrationservices.dto.UserAccountDTO;
import com.hay.user.registrationservices.model.UserAccount;
import com.hay.user.registrationservices.repository.UserAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private EmailService emailService;

	public UserAccountDTO saveAccount(UserAccountDTO userAccountDto) {

		UserAccount userAccount = new UserAccount();
		UserAccountDTO response = new UserAccountDTO();

		BeanUtils.copyProperties(userAccountDto, userAccount);
		userAccount = userAccountRepository.save(userAccount);
		BeanUtils.copyProperties(userAccount, response);
		emailService.sendWelcomeEmail(userAccount.getEmailAddress(), userAccount.getUserName());
		return response;
	}

	public Optional<UserAccount> getRegistrationById(Long id) {
		UserAccountDTO response = new UserAccountDTO();
		Optional<UserAccount> userAccount = userAccountRepository.findByIdAndIsDeletedFalse(id);
		userAccount.ifPresent(account -> BeanUtils.copyProperties(account, response));
		response.setPassword("");

		return userAccount;
	}

	public void deleteUserAccountById(Long id) {
		UserAccount userAccount = userAccountRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("User Account not found"));
		userAccount.setDeleted(true);
		userAccountRepository.save(userAccount);
	}

	public void softDeleteUserAccounts(List<Long> ids) {
		List<UserAccount> userAccounts = userAccountRepository.findAllById(ids);
		for (UserAccount userAccount : userAccounts) {
			userAccount.setDeleted(true);
		}

		userAccountRepository.saveAll(userAccounts);
	}
}

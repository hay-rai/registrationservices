package com.hay.user.registrationservices;

import com.hay.user.registrationservices.dto.UserAccountDTO;
import com.hay.user.registrationservices.model.UserAccount;
import com.hay.user.registrationservices.repository.UserAccountRepository;
import com.hay.user.registrationservices.service.EmailService;
import com.hay.user.registrationservices.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountServiceTest {

	@Mock
	private UserAccountRepository userAccountRepository;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private UserAccountService userAccountService;

	private UserAccountDTO userAccountDto;

	private UserAccount userAccount;

	@BeforeEach
	public void setUp() {
		userAccount = new UserAccount(1L, "juan1", "juan", "dela cruz", "test@gmail.com", "+639123456789", "123456", false);
		userAccountDto = new UserAccountDTO(1L, "", "", "", "alice@example.com", "", "");
	}

	@Test
	public void testCreateUser() {
		Mockito.when(userAccountRepository.save(Mockito.any(UserAccount.class))).thenReturn(userAccount);

		UserAccountDTO createdUser = userAccountService.saveAccount(userAccountDto);

		assertNotNull(createdUser);
		assertEquals("juan1", createdUser.getUserName());
	}

	@Test
	public void testGetUserAccount() {
		Mockito.when(userAccountRepository.findByIdAndIsDeletedFalse(1L)).thenReturn(Optional.ofNullable(userAccount));

		Optional<UserAccount> retrievedUser = userAccountService.getRegistrationById(1L);

		assertTrue(retrievedUser.isPresent());
		retrievedUser.ifPresent(user -> assertEquals("juan1", user.getUserName()));
	}

	@Test
	public void testDeleteUser() {
		Mockito.when(userAccountRepository.save(Mockito.any(UserAccount.class))).thenReturn(userAccount);
		Mockito.when(userAccountRepository.findByIdAndIsDeletedFalse(1L)).thenReturn(Optional.ofNullable(userAccount));

		userAccountService.deleteUserAccountById(1L);

		Mockito.verify(userAccountRepository, Mockito.times(1)).save(Mockito.any(UserAccount.class));
		Mockito.verify(userAccountRepository, Mockito.times(1)).findByIdAndIsDeletedFalse(1L);
	}
}

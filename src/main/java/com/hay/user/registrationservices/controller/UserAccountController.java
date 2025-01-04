package com.hay.user.registrationservices.controller;

import com.hay.user.registrationservices.dto.UserAccountDTO;
import com.hay.user.registrationservices.model.UserAccount;
import com.hay.user.registrationservices.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/useraccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;


	@PostMapping("/register")
	public ResponseEntity<UserAccountDTO> saveAccount(@RequestBody @Valid UserAccountDTO useraccount) {
		try {
			UserAccountDTO userAcct = userAccountService.saveAccount(useraccount);
			return ResponseEntity.ok(userAcct);
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@Operation(summary = "Get all users", description = "Returns a list of all users that are not soft-deleted.")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved list of users", content = @Content(mediaType = "application/json"))
	@GetMapping("/{id}")
	public ResponseEntity<UserAccountDTO> getAccount(@PathVariable Long id) {
		Optional<UserAccount> userAccount = userAccountService.getRegistrationById(id);
		UserAccountDTO response = new UserAccountDTO();
		userAccount.ifPresent(account -> BeanUtils.copyProperties(account, response));
		response.setPassword("");
		return userAccount.map(value -> new ResponseEntity<>(response, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping
	public ResponseEntity<UserAccountDTO> updateUserAccount(@RequestBody @Valid UserAccountDTO useraccount) {
		try {
			UserAccountDTO userAcct = userAccountService.saveAccount(useraccount);
			return ResponseEntity.ok(userAcct);
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserAccount(@PathVariable Long id) {
		try {
			userAccountService.deleteUserAccountById(id);
			return ResponseEntity.noContent().build();  // 204 No Content
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());  // 404 Not Found
		}
	}

	@DeleteMapping("/batch")
	public ResponseEntity<Void> deleteUsers(@RequestBody List<Long> ids) {
		if (ids.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		userAccountService.softDeleteUserAccounts(ids);
		return ResponseEntity.noContent().build();
	}

}

package com.litrc.votingsystem.vote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "username or password wrong")
public class PasswordOrUsernameWrongException extends RuntimeException {
}

package com.example.demo.dto;

public record JwtResponse(
		String accessToken,
		String refreshToken,
		UserDto dto
		) {

}

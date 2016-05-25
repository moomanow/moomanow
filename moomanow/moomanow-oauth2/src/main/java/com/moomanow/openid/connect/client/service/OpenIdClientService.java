package com.moomanow.openid.connect.client.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface OpenIdClientService {

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException, IOException, ServletException;

}

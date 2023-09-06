package com.controller.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/users")
public class JsonExampleServlet extends HttpServlet {

	private static final long serialVersionUID = -473118414090586412L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, JsonProcessingException, IOException {
		BufferedReader reader = request.getReader();
		String messageBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println("====MessageBody====");
		System.out.println(messageBody);
		System.out.println("===================");

		ObjectMapper objectMapper = new ObjectMapper();;
		UserData userData = objectMapper.readValue(messageBody, UserData.class);
		System.out.println(userData);
		
		String payload = objectMapper.writeValueAsString(userData);

		response.setContentType("application/json");
		response.getWriter().write(payload);
	}
	
	
}

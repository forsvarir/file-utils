package com.forsvarir.file.utils.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FileServicesApplicationIT {
	public static final String FILE_SERVICE_URL = "http://localhost:8080/file-utils/files/0";

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Test
	void contextLoads() {
	}

	@Test
	void canHitFileEndPoint() {
		var response = restTemplateBuilder
				.build()
				.getForEntity(FILE_SERVICE_URL, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

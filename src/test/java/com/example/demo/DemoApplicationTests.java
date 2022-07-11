package com.example.demo;

import com.example.demo.api.CommentApi;
import com.example.demo.api.PostApi;
import com.example.demo.api.TodoApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	PostApi postApi;

	@Autowired
	CommentApi commentApi;

	@Autowired
	TodoApi todoApi;

	@Test
	void contextLoads() {
		Assertions.assertThat(postApi).isNot(null);
		Assertions.assertThat(commentApi).isNot(null);
		Assertions.assertThat(todoApi).isNot(null);
	}

}

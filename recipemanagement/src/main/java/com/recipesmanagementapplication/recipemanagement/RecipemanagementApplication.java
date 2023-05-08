package com.recipesmanagementapplication.recipemanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class RecipemanagementApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RecipemanagementApplication.class, args);
		log.info("============================================================================================");
		log.info(" ----- :: Recipe Management Application Running Successfully... :: -----");
		log.info("  MySQL Database UserName is {} database....", context.getEnvironment().getProperty("spring.datasource.username"));
		log.info("============================================================================================");
	}

}

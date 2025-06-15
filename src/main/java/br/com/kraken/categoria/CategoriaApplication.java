package br.com.kraken.categoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CategoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriaApplication.class, args);
	}

	@GetMapping("/")
	public static String ValueReturn() {
		return "<!DOCTYPE html>\n" +
				"<html lang=\"pt-BR\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <title>Status do Sistema</title>\n" +
				"    <style>\n" +
				"        body {\n" +
				"            font-family: Arial, sans-serif;\n" +
				"            background-color: #f0fff0;\n" +
				"            color: #2e7d32;\n" +
				"            text-align: center;\n" +
				"            margin-top: 20%;\n" +
				"        }\n" +
				"        h1 {\n" +
				"            font-size: 2.5em;\n" +
				"        }\n" +
				"    </style>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h1>✅ O sistema está funcionando corretamente!</h1>\n" +
				"</body>\n" +
				"</html>\n";
	}
}

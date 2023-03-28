package hug58.todo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class TodoApplication{

	@Value("\${SPRING_DATASOURCE_URL}")
	private val datasourceUrl: String? = null

	@Bean
	fun databaseInitializer(userRepository: UserRepository,
							articleRepository: TodoRepository) = ApplicationRunner {
		val hugo = userRepository.save(User("hu58", "Hugo", "Montanez"))
		articleRepository.save(Todo(
			name = "Jhezuann lava los platos",
			description = "platos",
			image = "imagen.png",
			author = hugo
		))
	}
}

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}

@RestController
@RequestMapping("/api/todos")
class MessageController (private  val repository: TodoRepository, private val userRepository: UserRepository) {
	@GetMapping("/")
	fun findAll() = repository.findAll()
	//fun index(@RequestParam("name") name: String) = "Helo, $name!"

	@PostMapping("/")
	fun create(@RequestBody req: RequestTodo): Todo {
		var todo = Todo(
			name = req.name,
			description = req.description,
			image = req.image,
		)

		if (req.author > 0) {
			var author = userRepository.findById(req.author)
			todo.author = author.get()
		}

		return repository.save(todo)
	}
}




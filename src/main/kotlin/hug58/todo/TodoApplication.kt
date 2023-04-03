package hug58.todo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.jvm.optionals.getOrNull

@SpringBootApplication
class TodoApplication{

	//@Value("\${SPRING_DATASOURCE_URL}")
	//private val datasourceUrl: String? = null

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

	@PostMapping("/")
	fun create(@RequestBody req: RequestTodo): Todo {

		println("test")

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

	@DeleteMapping("/{id}/")
	fun delete(@PathVariable id:Long): ResponseEntity<Void> {
		repository.deleteById(id)
		return ResponseEntity.noContent().build()
	}

	@GetMapping("/test/")
	fun index(@RequestParam("name") name: String) = "Helo, $name!"


	@PutMapping("/{id}/")
	fun updateResource(@PathVariable id: Long, @RequestBody req: RequestTodo): ResponseEntity<Todo> {
		var todo = repository.findById(id).get()

		todo.description = req.description
		todo.name = req.name
		todo.image = req.image

		val updatedResource = repository.save(todo)
		return ResponseEntity.ok(updatedResource)
	}

	@GetMapping("/{id}/")
	fun detail(@PathVariable id: Long): ResponseEntity<Todo> {
		var todo = repository.findById(id).get()
		return ResponseEntity.ok(todo)
	}

}





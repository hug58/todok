package hug58.todo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Controller
class HtmlController (private val repository: TodoRepository){

    @GetMapping("/")
    fun todo(model: Model): String {
        model["title"] = "ToDo"
        model["todo"] = repository.findAll().map { it.render() }

        //ToDo corresponde al nombre del archivo de mustache.
        return "todo"
    }

    fun Todo.render() = author?.let {
        RenderedArticle(
        name,
        description,
        image,
            it,
    )
    }

    data class RenderedArticle(
        val name: String,
        val description: String,
        val image: String,
        val author: User,
    )

}


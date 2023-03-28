package hug58.todo

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne


class RequestTodo(
    var name: String = "",
    var description: String="",
    var author: Long = 0,
    var image: String ="",
)
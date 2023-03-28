package hug58.todo

import jakarta.persistence.*
import java.util.*

@Entity
class Todo(
    var name: String = "",
    var description: String="",
    @ManyToOne var author: User? = null,
    var image: String ="",
    @Id
    @GeneratedValue()
    var id: Long? = null,
    )


@Entity
class User(
    var login: String = "",
    var firstname: String = "",
    var lastname: String = "",
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Long? = null,
 )
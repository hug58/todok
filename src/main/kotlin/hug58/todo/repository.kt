package hug58.todo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : CrudRepository<Todo, Long> {
}
@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}

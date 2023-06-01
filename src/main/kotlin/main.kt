data class Post(
    var id: Int,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "text",
    val postType: String = "postType",
    val isPinned: Int = 0,
    val markedAsAds: Boolean = true,
    val isFavorite: Boolean = true,
    val likes: Likes = Likes()
)

data class Likes(
    var count: Int = 0
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = newPost.likes.copy())
                return true
            }
        }
        return false
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}

fun main() {
    val likes = Likes(5)
    val post = Post(0, likes = likes)
    WallService.add(post)
    likes.count = 10
    WallService.add(Post(1))
    WallService.add(Post(2))
    WallService.add(Post(2))
    WallService.print()
    println(WallService.update(Post(4, likes = Likes(50))))
    WallService.print()

}
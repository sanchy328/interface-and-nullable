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
    val original: Post?,
    val likes: Likes = Likes(),
    val attachment: Array<Attachment> = arrayOf(
        PhotoAttachment(Photo(1, 0)),
        VideoAttachment(Video(0, 1)),
        AudioAttachment(Audio(2, 2))
    )
)

data class Likes(
    var count: Int = 0
)

interface Attachment {
    val type: String
}

data class Photo(
    val id: Int,
    val ownerId: Int
)

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type = "photo"
}

data class Video(
    val id: Int,
    val ownerId: Int
)

data class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}

data class Audio(
    val id: Int,
    val ownerId: Int
)

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type = "audio"
}

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun repost(post: Post): Post {
        val original = if (post.original == null) post else post.original
        TODO()
    }

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
    val post = Post(0, original = null, likes = likes)
    WallService.add(post)
    likes.count = 10
    WallService.add(Post(1, original = null))
    WallService.add(Post(2, original = null))
    WallService.add(Post(2, original = null))
    WallService.print()
    println(WallService.update(Post(4, original = null, likes = Likes(50))))
    WallService.print()

}
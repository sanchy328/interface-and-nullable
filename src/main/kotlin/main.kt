data class Post(
    var id: Int,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "text",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Int = 0,
    val comments: Comments = Comments(),
    val copyright: Copyright = Copyright(1),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val postSource: PostSource = PostSource(),
    val geo: Geo = Geo(),
    val signerId: Int = 9,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = true,
    val markedAsAds: Boolean = true,
    val isFavorite: Boolean = true,
    val donut: Donut = Donut(),
    val postponedId: Int = 11,
    val original: Post?,
    val likes: Likes = Likes(),
    val attachment: Array<Attachment> = arrayOf(
        PhotoAttachment(Photo(1, 0)),
        VideoAttachment(Video(0, 1)),
        AudioAttachment(Audio(2, 2))
    )
)

data class Donut(
    val isDonut: Boolean = true,
    val paidDuration: Int = 10,
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = "all"
)

data class Geo(
    val type: String = "type",
    val coordinates: String = "gde-to_tam",
    val place: Place = Place(0)
)

data class Place(
    val id: Int,
    val title: String = "u_magazine",
    val latitude: Int = 1,
    val longitude: Int = 2,
    val created: Int = 3,
    val icon: String = "https://icon",
    val checkins: Int = 4,
    val updated: Int = 5,
    val type: Int = 6,
    val country: Int = 7,
    val city: Int = 8,
    val address: String = "Here"
)

data class PostSource(
    val type: String = "vk",
    val platform: String = "iphone",
    val data: String = "profile_photo",
    val url: String = "https://m.vk.com/theterminator"
)

data class Views(
    var count: Int = 0
)

data class Reposts(
    var count: Int = 0,
    val userReposted: Boolean = false
)
data class Copyright(
    val id: Int,
    val link: String = "link",
    val name: String = "name",
    val type: String = "type"
)

data class Comments(
    var count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class Likes(
    var count: Int = 0,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
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
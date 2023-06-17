import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {
        val (id) = WallService.add(Post(0, original = null))

        val result = id

        assertEquals(1, result)
    }

    @Test
    fun updateTestTrue() {
        WallService.add(Post(0, original = null))
        WallService.add(Post(1, original = null))
        WallService.add(Post(1, original = null))

        val update = Post(1, original = null)

        val result = WallService.update(update)

        assertTrue(result)
    }

    @Test
    fun updateTestFalse() {
        WallService.add(Post(0, original = null))
        WallService.add(Post(1, original = null))
        WallService.add(Post(1, original = null))

        val update = Post(4, original = null)

        val result = WallService.update(update)

        assertFalse(result)
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    public void setUp(){
        user = new User();
    }

    @Test
    public void incProgressShouldThrowExceptionWhenArgIsIllegal(){
        assertThrows(IllegalArgumentException.class, ()->user.incProgress(-9));
        assertThrows(IllegalArgumentException.class, ()->user.incProgress(9));
        assertThrows(IllegalArgumentException.class, ()->user.incProgress(0));
    }

    @Test
    public void incProgressShouldSetPropRank(){
        //given
        user.incProgress(3);
        //then
        assertEquals(3,user.rank);
    }

    @Test
    public void rankShouldNotBeIncIfProgressLowerThan100(){
        //given
        user.incProgress(-5);
        //then
        assertEquals(-8,user.rank);
    }

    @Test
    public void rankShouldBeMaxAndProgressEqual0(){
        //given
        user.incProgress(8);
        //then
        assertEquals(8,user.rank);
        assertEquals(0,user.progress);
    }


}
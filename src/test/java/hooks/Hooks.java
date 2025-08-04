package hooks;

import Base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

    @Before
    public void setUp()
    {
        initiatedriver();
    }

    @After
    public void teardown()
    {
        quitdriver();
    }

}

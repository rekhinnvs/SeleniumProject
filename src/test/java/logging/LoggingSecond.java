package logging;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.Base;

public class LoggingSecond extends Base {

    @Test
    public void oneFromSecondClass() {
        Logger logger = getLogger(this.getClass().getName());

        logger.trace("This is from second class trace");
        logger.debug("This is from second class debug");
        logger.warn("This is from second class warn");
        logger.error("This is from second class error");
        logger.fatal("This is from second class fatal");
    }
}

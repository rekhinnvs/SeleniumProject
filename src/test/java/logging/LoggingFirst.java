package logging;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.Base;

public class LoggingFirst extends Base {

    @Test
    public void oneFromFirstClass() {
        Logger logger = getLogger(this.getClass().getName());

        logger.trace("This is from first class trace");
        logger.debug("This is from first class debug");
        logger.warn("This is from first class warn");
        logger.error("This is from first class error");
        logger.fatal("This is from first class fatal");
    }
}

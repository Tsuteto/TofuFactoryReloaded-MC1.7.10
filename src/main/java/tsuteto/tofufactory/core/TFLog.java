package tsuteto.tofufactory.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;

public class TFLog
{
    private final Logger logger;
    private final boolean isDebug;
    private final MessageFactory messageFactory;

    public TFLog(String modId, boolean isDebug)
    {
        this.logger = LogManager.getLogger(modId);
        this.isDebug = isDebug;
        try
        {
            messageFactory = ParameterizedMessageFactory.class.newInstance();
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }

    public void log(Level level, String msg, Object... params)
    {
        this.logger.log(level, msg, params);
    }

    public void log(Level level, Throwable t, String msg, Object... params)
    {
        this.logger.log(level, msg, t, params);
    }

    public void info(String msg, Object... params)
    {
        this.logger.info(msg, params);
    }

    public void warn(String msg, Object... params)
    {
        this.logger.warn(msg, params);
    }

    public void warn(Throwable t, String msg, Object... params)
    {
        this.logger.warn(messageFactory.newMessage(msg, params), t);
    }

    public void debug(String msg, Object... params)
    {
        if (isDebug)
        {
            this.logger.info("(DEBUG) " + msg, params);
        }
    }
}

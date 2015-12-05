package logger;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class MyLogger {
	 static private FileHandler fileTxt;
	  static private SimpleFormatter formatterTxt;

	  static private FileHandler fileHTML;
	  static private Formatter formatterHTML;

	  static public void setup() throws IOException {

	    // get the global logger to configure it
	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	    // suppress the logging output to the console
	    Logger rootLogger = Logger.getLogger("");
	    Handler[] handlers = rootLogger.getHandlers();


	    logger.setLevel(Level.INFO);
	    //fileTxt = new FileHandler("Logging.txt");
	    
		File f = new File(System.getProperty("user.dir")+"\\Logger\\Logging.html");
	    if(!f.exists()) { 
	    	 fileHTML = new FileHandler(System.getProperty("user.dir")+"\\Logger\\"+"Logging.html");
	    }else {
	    	f.delete();
	    	 fileHTML = new FileHandler(System.getProperty("user.dir")+"\\Logger\\"+"Logging.html");
		}
	    

	    // create a TXT formatter
	    //formatterTxt = new SimpleFormatter();
	    //fileTxt.setFormatter(formatterTxt);
	    //logger.addHandler(fileTxt);

	    // create an HTML formatter
	    formatterHTML = new MyHtmlFormatter();
	    fileHTML.setFormatter(formatterHTML);
	    logger.addHandler(fileHTML);
	  }
	  
}

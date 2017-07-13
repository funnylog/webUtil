package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {
	static Logger logger = Logger.getLogger(Log4jInit.class);

	public Log4jInit() {

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j_init_file");
		String logFile = config.getInitParameter("log4j_file_path");

		if (file != null) {
			Properties prop = new Properties();
			try {
				String logFileName = path + file;
				logFileName = logFileName.replace("/", File.separator);
				logFileName = logFileName.replace("\\", File.separator);
				prop.load(new FileInputStream(logFileName)); // 加载log4j.properties
				String logFilePath = path + logFile + prop.getProperty("log4j.appender.R.File");
				logFilePath = logFilePath.replace("/", File.separator);
				logFilePath = logFilePath.replace("\\", File.separator);
				prop.setProperty("log4j.appender.R.File", logFilePath); // 设置日志文件的输出路径
				PropertyConfigurator.configure(prop); // 加载配置项
			}
			catch (Exception e) {
				logger.info("初始化log4j日志输入路径异常，请检查web.xml参数配置是否正常，异常发生在" + this.getClass().getName()
						+ "类的public void init()方法，异常原因是：" + e.getMessage(), e.fillInStackTrace());
			}
		}
	}
}

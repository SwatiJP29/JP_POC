package com.jp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jp.stream.ReadStream;

@PropertySource("classpath:config.properties")
@Controller
public class LinuxCommandExecute {
	
	final static Logger logger = Logger.getLogger(LinuxCommandExecute.class);

	@Value( "${servername}" )
	private String servername;
	
	public String getServername() {
		return servername;
	}


	public void setServername(String servername) {
		this.servername = servername;
	}


	@Value( "${command}" )
	private String command;
	
	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("Begin Executing commands on Linux.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("servername",getServername());
		return modelAndView;

	}
	
		
	@RequestMapping(value = "/executeCommand", method = RequestMethod.GET)
	public ModelAndView executeCommand() throws IOException {
		logger.info("Begin Executing commands on Linux.");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displaydetails");
		
		//ProcessBuilder processBuilder = new ProcessBuilder();
		//processBuilder.command(getCommand());
		Process process = Runtime.getRuntime().exec(getCommand()) ;  
		ReadStream s1 = new ReadStream("stdin", process.getInputStream());
		ReadStream s2 = new ReadStream("stderr", process.getErrorStream());
		try {

			//Process process = processBuilder.start();
			s1.start();
			s2.start();
			//process.waitFor();
			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(output);
				modelAndView.addObject("output", output.toString());
				//System.exit(0);
			} else {
				//abnormal...
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		    if(process != null)
		        process.destroy();
		}
		
		return modelAndView;
		
	}

}

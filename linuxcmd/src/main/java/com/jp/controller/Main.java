package com.jp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jp.stream.ReadStream;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Process process = Runtime.getRuntime().exec("cmd /c dir C:\\logs", null, new File("C:\\Users\\a579482\\"));  
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
				//modelAndView.addObject("output", output.toString());
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

	}

}

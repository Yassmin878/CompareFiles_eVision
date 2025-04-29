package com.eVision.CompareFiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@SpringBootApplication
public class CompareFilesApplication {

	public static void main(String[] args) {
		readFiles();
		SpringApplication.run(CompareFilesApplication.class, args);
	}
	static void readFiles()
	{

		String folderPath= "src/main/resources/Files";
		String[]files= {"/File_A","/File_B","/File_C"};

		ArrayList<String> FilesContent = new ArrayList<String>();
//		reading files and add content in list
		for(int i=0;i<files.length;i++)
		{
			Path fileA= Path.of(folderPath+files[i]);
			String FileA_Content= null;
			try {
				FileA_Content = Files.readString(fileA);
				FilesContent.add(FileA_Content);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		ArrayList<Double> scores = new ArrayList<Double>();
		for(int i=0;i<FilesContent.size()-1;i++)
		{
			double score=compareStrings(FilesContent.get(0),FilesContent.get(i+1));
			scores.add(score);
		}

		System.out.println(scores);
	}
	static double compareStrings(String file1, String file2)
	{
//		spliting String in list
		String[] str1Split = file1.split(" ");
		String[] str2Split = file2.split(" ");
		double score=0;
		int length=str1Split.length;
		ArrayList<String> words = new ArrayList<String>();

		for(int i=0;i<str1Split.length;i++)
		{
			for(int j=0;j<str2Split.length;j++)
			{
//				if text contain only Letters
				if(str1Split[i].matches("[a-zA-Z]+"))
				{
//					if Word is common and not Repeated
					if (str1Split[i].equalsIgnoreCase(str2Split[j])&&!words.contains(str1Split[i]))
					{
						words.add(str1Split[i]);
						score++;
					}
				}
				else{
					length--;
				}

			}
		}
		return (score/length)*100;
	}
}

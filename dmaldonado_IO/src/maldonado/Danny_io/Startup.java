/**
 * 
 */
package maldonado.Danny_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmaldonado
 *
 */
public class Startup 
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		System.out.println("Welcom to Digital Contacts");
		choice();
	}

	
	public static void choice() throws Exception
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to do?\n1)See all contacts\n2)See specific contact\n3)Enter new contact\n4)Quit");
		switch(scan.nextInt())
		{
		case 1:
		{
			printAll();
		}
		case 2:
		{
			printOne();
		}
		case 3:
		{
			output();
		}
		case 4:
		{
			scan.close();
			quit();
		}
		}
	}

	public static void output() throws Exception
	{
		Scanner scan = new Scanner(System.in);
		FileWriter output = new FileWriter("contacts.txt", true);
		BufferedWriter buff = new BufferedWriter(output);
		System.out.println("Enter new contact");
		String line = scan.nextLine();
		String regex = "([0-9]+[-])([A-Z][a-z]+ [A-Z][a-z]+),([A-Za-z]+@[a-z]+.[a-z]+),([0-9]{3}-[0-9]{3}-[0-9]{4})";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(line);
		if(match.find())
		{
			output.append(line);
		}
		choice();
		scan.close();
		output.close();
	}

	public static void printAll() throws IOException
	{
		FileReader input = new FileReader("contacts.txt");
		BufferedReader buffer = new BufferedReader(input);

		String line = "";
		while(buffer.ready())
		{
			line = buffer.readLine();
			System.out.println(line);
		}
		try 
		{
			choice();
		} 
		catch (Exception e) 
		{
			System.err.println("An error has occured");
		}
		buffer.close();
	}

	public static void printOne() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		FileReader input = new FileReader("contacts.txt");
		BufferedReader buffer = new BufferedReader(input);
		String line = "";
		System.out.println("Enter the contact number");
		while(buffer.ready())
		{
			line = buffer.readLine();
			String num = scan.next();
			Pattern pattern = Pattern.compile("(["+ num +"][-])([A-Z][a-z]+ [A-Z][a-z]+),([A-Za-z]+@[a-z]+.[a-z]+),([0-9]{3}-[0-9]{3}-[0-9]{4})");
			Matcher matcher = pattern.matcher(line);
			while(matcher.find())
			{
				String reg = (",");
				String[] res = line.split(reg);
				System.out.println(Arrays.toString(res));
			}
		}
		try 
		{
			choice();
		} 
		catch (Exception e) 
		{
			System.err.println("Error has occurred");
		}
		finally
		{
			scan.close();
			buffer.close();
		}
	}

	public static void quit()
	{
		System.out.println("Goodbye.");
	}
}
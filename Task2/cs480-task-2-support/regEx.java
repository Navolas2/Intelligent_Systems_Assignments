import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class regEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		String choice = "z";
		while(choice != "quit" && choice != "q")
		{
			menu();
			System.out.println("Please made a selection");
			choice = kb.nextLine();
			System.out.println("please enter your string to test:");
			String tester = kb.nextLine();
			String regex = "";
			switch (choice) {
				case "a":
						regex = "[0-9]{3}( [0-9]{2} )[0-9]{4}|[0-9]{3}(\\-[0-9]{2}\\-)[0-9]{4}";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(tester);
						if (m.matches())
						{
							System.out.println("This is a valid Social Security Number");
						}
						else
						{ 
							System.out.println("This is an invalid Social Security Number");
						}
					break;
				case "b":
						regex = "(1(\\.|\\-)?)?(\\(?[0-9]{3}\\)?)(\\.|\\-|\\s)?([0-9]{3})(\\.|\\-|\\s)?([0-9]{4})";
						Pattern p1 = Pattern.compile(regex);
						Matcher m1 = p1.matcher(tester);
						if (m1.matches())
						{
							System.out.println("This is a valid US Phone Number");
						}
						else
						{ 
							System.out.println("This is an invalid US Phone Number");
						}
					break;
				case "c":
						regex = "(([A-Z]|[a-z]|[0-9]|#|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|:|\\\")|(\\(\\2*\\)))+([A-Z]|[a-z]|[0-9]|#|-|_|~|!|\\$|&|'|(\\(\\2*\\))|\\*|\\+|,|;|=|:|\\.{1}|(\\.\\.){0}|(\\.\\\"\\2*\\\"\\.)){0,253}\\2{1}@([A-Z]|[a-z]|[0-9]|#|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|:|\\.{1}|(\\.\\.){0})+";
						Pattern p13 = Pattern.compile(regex);
						Matcher m13 = p13.matcher(tester);
						if (m13.matches())
						{
							System.out.println("This is a valid Email Address");
						}
						else
						{ 
							System.out.println("This is an invalid Email Address");
						}
					break;
				case "d":
						regex = "[A-Z]{1}([a-z]|-){1,30}, [A-Z]{1}[a-z]{1,15}(, ([A-Z]\\. )){0,4}";
						Pattern p11 = Pattern.compile(regex);
						Matcher m11 = p11.matcher(tester);
						if (m11.matches())
						{
							System.out.println("This is a valid name");
						}
						else
						{ 
							System.out.println("This is an invalid name");
						}
					break;
				case "e":
						regex = "((1[0-9])|([0]?[1-9]))( |-){1}([1-9]|[1-2][0-9]|3[0-1])\\4{1}([1-2][0-9]){1}[0-9][0-9]";
					Pattern p3 = Pattern.compile(regex);
					Matcher m3 = p3.matcher(tester);
					if (m3.matches())
						{
							System.out.println("This is a valid date");
						}
						else
						{ 
							System.out.println("This is an invalid date");
						}
					break;
				case "f":
						regex = "((N|W|E|S) [0-9]{1,7})|([0-9]{1,7} (N|W|E|S)) [A-Za-z]{14} [A-Za-z]{0,4}\\.?";
						Pattern p4 = Pattern.compile(regex);
						Matcher m4 = p4.matcher(tester);
						if (m4.matches())
						{
							System.out.println("This is a valid address");
						}
						else
						{ 
							System.out.println("This is an invalid address");
						}
					break;
				case "g":
						regex = "[A-Z]?([a-z]| |-|,){1,22},{1} (AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY) [0-9]{5}((-| )?[0-9]{4})?";
						Pattern p5 = Pattern.compile(regex);
						Matcher m5 = p5.matcher(tester);
						if (m5.matches())
						{
							System.out.println("This is a valid City & State");
						}
						else
						{ 
							System.out.println("This is an invalid City & State");
						}
					break;
				case "h":
						regex = "(([0-1][0-9])|([2][0-3])){1}:?[0-6]{1}[0-6]{1}:{1}[0-6]{2}";
						Pattern p6 = Pattern.compile(regex);
						Matcher m6 = p6.matcher(tester);
						if (m6.matches())
						{
							System.out.println("This is a valid military time");
						}
						else
						{ 
							System.out.println("This is an invalid military time");
						}
					break;
				case "i":
						regex = "\\$?[1-9]{1}((,[0-9]{3})|([0-9]{0,2}))+(\\.[0-9]{0,2})?";
						Pattern p7 = Pattern.compile(regex);
						Matcher m7 = p7.matcher(tester);
						if (m7.matches())
						{
							System.out.println("This is a valid currency");
						}
						else
						{ 
							System.out.println("This is an invalid currency");
						}
					break;
				case "j":
						regex = "((http:\\/\\/)|(www\\.)){1,2}([a-z]|[A-Z]|-|_|[0-9])+\\.([a-z]|[A-Z]|-|_|[0-9]|\\%[0-9]{1,2}|\\/|\\.|\\?|\\=|\\#|\\$|\\\")+";
						Pattern p8 = Pattern.compile(regex);
						Matcher m8 = p8.matcher(tester);
						if (m8.matches())
						{
							System.out.println("This is a valid URL");
						}
						else
						{ 
							System.out.println("This is an invalid URL");
						}
					break;
				case "k":
						regex = "[\\.\\+\\=\\-\\_\\!\\@\\#\\$\\%\\^\\&\\*\\<\\>\\?\\\\\\/\\|\\~\\(\\)\\:\\;\\da-zA-Z]";//check for 10 characters
						Pattern p9 = Pattern.compile(regex);
						Matcher m9 = p9.matcher(tester);
						if (m9.find())
						{
							regex = "[A-Z]";//check for Uppercase character
							p9 = Pattern.compile(regex);
							m9 = p9.matcher(tester);
							if (m9.find())
							{
								regex = "[\\d]";//check for number
								p9 = Pattern.compile(regex);
								m9 = p9.matcher(tester);
								if (m9.find())
								{
									regex = "[\\.\\+\\=\\-\\_\\!\\@\\#\\$\\%\\^\\&\\*\\<\\>\\?\\\\\\/\\|\\~\\(\\)\\:\\;]";//check for symbol
									p9 = Pattern.compile(regex);
									m9 = p9.matcher(tester);
									if (m9.find())
									{
										regex = "[a-z]{4,}";//check lowercase characters
										p9 = Pattern.compile(regex);
										m9 = p9.matcher(tester);
										if (m9.find())
										{
											System.out.println("This is an invalid Password");
										}
										else
										{ 
											System.out.println("This is a valid Password");
										}
									}
									else
									{ 
										System.out.println("This is an invalid Password");
									}
								}
								else
								{ 
									System.out.println("This is an invalid Password");
								}
							}
							else
							{ 
								System.out.println("This is an invalid Password");
							}
						}
						else
						{ 
							System.out.println("This is an invalid Password");
						}
					break;
				case "l":
						regex = "[a-zA-Z]([a-zA-Z][a-zA-Z])*ion";
						Pattern p12 = Pattern.compile(regex);
						Matcher m12 = p12.matcher(tester);
						if (m12.matches())
						{
							System.out.println("This is a valid word");
						}
						else
						{ 
							System.out.println("This is an invalid word");
						}
					break;
			}
		}
	}

	private static void menu()
	{
		System.out.println("a .Social Security Number");
		System.out.println("b. US Phone number");
		System.out.println("c. E-mail address");
		System.out.println("d. Name on a class roster, assuming one or more middle initials - Last name, First name, MI");
		System.out.println("e. Date in MM-DD-YYYY format");
		System.out.println("f. House address - Street number, street name, abbreviation for road, street, boulevard or avenue");
		System.out.println("g. City followed by state followed by zip as it should appear on a letter");
		System.out.println("h. Military time, including seconds");
		System.out.println("i. US Currency down to the penny (ex: $123,456,789.23)");
		System.out.println("j. URL, including http:// (upper and lower case should be accepted)");
		System.out.println("k. A password that contains at least 10 characters and includes at least one upper case character, lower case character, digit, punctuation mark, and does not have more than 3 consecutive lower case characters");
		System.out.println("l. All words containing an odd number of alphabetic characters, ending in \"ion\"");
		System.out.println(" q. quit");
	}
}


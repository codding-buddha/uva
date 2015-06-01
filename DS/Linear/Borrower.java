import java.util.*;
import java.io.*;

public class Borrower
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		ArrayList<Book> books = new ArrayList<Book>();
		HashMap<String, Integer> lookup = new HashMap<String, Integer>();
		String book = null;
		while(!(book = sc.nextLine()).equals("END"))
		{
			String[] attrs = book.split("by");
			String title = attrs[0].trim();
			String author = attrs[1].trim();
			Book b = new Book(author, title);
			books.add(b);
		}

		Collections.sort(books);
		boolean[] borrowed = new boolean[books.size()];
		boolean[] returned = new boolean[books.size()];
		for(int i =0, len = books.size(); i < len; i++) 
		{
			lookup.put(books.get(i).getTitle(), i);
		}

		String operation = null;
		while(!(operation = sc.nextLine()).equals("END"))
		{
			int idx = operation.indexOf(' ');
			String op = null;
			String title = null;
			if(idx == -1)
				op = "SHELVE";
			else
			{
				title = operation.substring(idx + 1).trim();
				op = operation.substring(0, idx).trim();

			}
			
			if(op.equals("BORROW"))
			{
				borrowed[lookup.get(title)] = true;
			}
			else if(op.equals("RETURN"))
			{				
				returned[lookup.get(title)] = true;
			}
			else
			{
				int prev = -1;
				for(int i =0; i < borrowed.length; i++) 
				{
					if(!borrowed[i]) 
					{
						prev = i;
					} else if(returned[i]) {
						if(prev == -1) {
							out.println("Put " + books.get(i).getTitle() + " first");
						} else {
							out.println("Put " + books.get(i).getTitle() +  " after " + books.get(prev).getTitle());
						}

						returned[i] = false;
						borrowed[i] = false;
						prev = i;
					}
				}
				out.println("END");
			}
		}

		sc.close();
		out.close();
	}
}

class Book implements Comparable<Book>
{
	private String title;
	private String author;

	public String getTitle()
	{
		return title;
	}

	public String getAuthor() 
	{
		return author;
	}

	public Book(String author, String title)
	{
		this.title = title;
		this.author = author;
	}

	public int compareTo(Book b)
	{
		if(this.author.compareTo(b.author) == 0)
		{
			return this.title.compareTo(b.title);
		}
		else
		{
			return this.author.compareTo(b.author);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Book))
			return false;
		Book b = (Book) o;
		return this.title.equals(b.title) && this.author.equals(b.author);
	}

	@Override
	public String toString()
	{
		return "\"" + this.title + "\"" + "," +"\"" +  this.author + "\"";
	}

}
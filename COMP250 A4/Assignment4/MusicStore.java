

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	MyHashTable<String, ArrayList<Song>> table_Title;
    MyHashTable<String, ArrayList<Song>> table_Artist;
    MyHashTable<Integer, ArrayList<Song>> table_Year;
    //ADD YOUR CODE ABOVE HERE
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    		table_Title = new MyHashTable<String, ArrayList<Song>>(songs.size());
    		table_Artist = new MyHashTable<String, ArrayList<Song>>(songs.size());
    		table_Year = new MyHashTable<Integer, ArrayList<Song>>(songs.size());
    		
        for (Song currentTerm: songs) {
        		this.addSong(currentTerm);
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    		if(s != null) {
    			if(table_Title.get(s.getTitle())==null) {
    				ArrayList<Song> temp = new ArrayList<Song>();
    				temp.add(s);
    				table_Title.put(s.getTitle(), temp);
    			}else {
    				table_Title.get(s.getTitle()).add(s);
    			}
    			if(table_Artist.get(s.getArtist())==null) {
    				ArrayList<Song> temp = new ArrayList<Song>();
    				temp.add(s);
    				table_Artist.put(s.getArtist(), temp);
    			}else {
    				table_Artist.get(s.getArtist()).add(s);
    			}
    			if(table_Year.get(s.getYear())==null) {
    				ArrayList<Song> temp = new ArrayList<Song>();
    				temp.add(s);
    				table_Year.put(s.getYear(), temp);
    			}else {
    				table_Year.get(s.getYear()).add(s);
    			}
    		}

        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    		if(title == null) {
    			return null;
    		}
    		Song output = null;
    		if(table_Title.get(title)!=null) {
    			output = table_Title.get(title).get(0);
    		}
        return output;
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    		if(artist == null) {
    			return null;
    		}
    		ArrayList<Song> output = table_Artist.get(artist);
    		if(output == null) {
    			output = new ArrayList<Song>(0);
    		}
        return output;//remove
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    		if(year.compareTo(new Integer(0))<=0||year == null) {
    			return null;
    		}
    		ArrayList<Song> output = table_Year.get(year);
    		if(output == null) {
    			output = new ArrayList<Song>(0);
    		}
        return output;//remove
        //ADD CODE ABOVE HERE
        
    }
}
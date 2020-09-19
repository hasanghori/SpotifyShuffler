//Client ID 36d6b08f158845dc9d4ea98cc3f48483
//Client Secret 9f5a8279b4b64fe8a09d4f175f7b6103
//https://accounts.spotify.com/authorize

public class MusicObject{
      private String songName;
      private String link;
      private int rating;
      
      public MusicObject(String songName, String link, int rating){
         this.songName = songName;
         this.link = link;
         this.rating = rating;
      }
      
      public String getSongName(){
         return songName;
      }
      
      public String getLink(){
         return link;
      }

      public int getRating(){
         return rating;
      }
      
      public String toString(){
         String result = "\nSong Name:\t\t" + songName
                    + "\nLink:\t" + link
                    + "\nRating\t\t" + rating
                    + "\n\n";
         return result;
      }
   
   }
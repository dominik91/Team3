/*
 * Main driver class for the command line version of the audio player.
 * Creates a playlist from the command line arguments and then loops
 * looking for simple commands to control playback.
 */

/*
*Group members: D.Cvjetkovic, M.Mijalic, M.Mustapic, A.Sutalo
*
*This class has been altered to accomodate for song input in form of a file.
*The main class determines which kind of an input it recieved from the user and than proceeds to create
*one of the two helper classes that accept that input and return a iterable list of names for the playlist
*In the same manner we can accomodate for retrieval of names from a database as well as XML or URL sources
*/

import edu.rit.se.swen383.audio.AudioSource ;

public class MP3Player {
   /*
    * Driver with a simple command language to control the
    * audio playback.
    */
   public static void main(String args[]) {
      /*
       * We need at least one file to play.
       */
      if( args.length < 1 ) {
         println("Usage: MP3Player mp3file ...") ;
         return ;
      }
      
      /*
      *Copied the code from the presentation
      *First we make an Iterable string array that will be used by the playlist
      *based on the user input one of two helper classes is created which serves to fill out
      *the Iterable string array with the list of song names
      *afterwards we proceed to make a playlist using this Iterable array
      */
      Iterable<String> mp3names ;
      if( args[0].equals("-d") ) {
         String filename = args[1] ;
         mp3names = new FromFile(filename) ;
      } 
      else {
         mp3names = new FromArray(args) ;
      }
      
      /*
       * Make the play list.
       */
      PlayList pl = new PlayList(mp3names) ;
   
      /*
       * Command loop.
       * Unrecognized commands are ignored.
       */
      char command = ' ' ;
      while( command != 'q' ) {
         String s = System.console().readLine() + " " ;
         command = s.charAt(0) ;
      
         if( command == '+' ) {
            int nextIndex = pl.getSourceIndex() + 1 ;
            /*
             * Don't move beyond the last play list element.
             */
            if( nextIndex < pl.size() ) {
               pl.play(nextIndex) ;
            }
         }
         if( command == '-' ) {
            int prevIndex = pl.getSourceIndex() - 1 ;
            /*
             * Don't move before the first play list element.
             */
            if( prevIndex >= 0 ) {
               pl.play(prevIndex) ;
            }
         }
         if( command == '@' ) {
            pl.play(pl.getSourceIndex()) ;
         }
         if( command == 'h'  || command == 'H' || command == '?') {
            println("+ = Play the file after the current one.");
            println("- = Play the file before the current one.");
            println("@ = Replay the current file.") ;
            println("h or H or ? = Print this help screen.") ;
            println("i [n] = Print information on file #'n'") ;
            println("        (or the current file if 'n' is omitted).") ;
            println("p [n] = Terminate any playback and start playing") ;
            println("        AudioSource #'n' (default 0).") ;
            println("P = Pause playback if any.") ;
            println("R = Resume playback if any.") ;
            println("t = Print the playback position in seconds.") ;
            println("s = Print number of playlist entries.") ;
            println("q = Quit the player.") ;
         }
         if( command == 'i') {
            AudioSource as = null ;
            int i = -1 ;
         
            try {
               String iv = s.substring(1).trim() ;
               i = Integer.parseInt(iv) ;
            } 
            catch(Exception e) {
               i = -1 ; // no integer argument.
            } ;
            if( i < 0 ) {
               i = pl.getSourceIndex() ;
            }
            as = pl.getSource(i) ;
         
            if( i == (-1) ) {
               println("Player is idle") ;
            } 
            else if( as != null ) {
               int duration = as.getDuration() ;
               int secs = duration % 60 ;
               int mins = duration / 60 ;
            
               println("Index:    " + i) ;
               println("File:     " + as.getFileName()) ;
               println("Title:    " + as.getTitle()) ;
               println("Artist:   " + as.getArtist()) ;
               println("Album:    " + as.getAlbum()) ;
               println("Genre:    " + as.getGenre()) ;
               System.out.printf ("Duration: %d:%02d\n", mins, secs) ;
            }
         }
         if( command == 'p' ) {
            int i = 0 ;
            try {
               String iv = s.substring(1).trim() ;
               i = Integer.parseInt(iv) ;
            } 
            catch(Exception e) {i = 0 ; }
            pl.play(i) ;
         }
         if( command == 'P' ) {
            pl.pause() ;
         }
         if( command == 'R' ) {
            pl.resume() ;
         }
         if( command == 's' ) {
            println("Playlist size: " + pl.size()) ;
         }
         if( command == 't' ) {
            int position = pl.getPosition() / 1000 ; // remove milliseconds
            int secs = position % 60 ;
            int mins = position / 60 ;
            System.out.printf("Source position: %d:%02d\n", mins, secs) ;
         }
      }
      /*
       * System.exit(0) rather than return as there is another thread
       * running and a return would only terminate the main thread.
       */
      System.exit(0) ;
   }

   private static void println(String s) {
      System.out.println(s) ;
   }
}

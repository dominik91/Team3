import java.util.*;
import static java.util.Arrays.asList; 

/*
*Group members: D.Cvjetkovic, M.Mijalic, M.Mustapic, A.Sutalo
*
*This is a helper class used to transform an array to an Iterable array
*/
public class FromArray implements Iterable<String> {
    List<String>args;
    
    //constructor takes a String[] array and transforms it into an iterable type or array, list
    public FromArray(String[] args){
       this.args = asList(args);
    }
    
    public Iterator<String> iterator(){
       return args.iterator() ;
    }
} 
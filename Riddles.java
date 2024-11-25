import java.util.Hashtable; 
import java.util.Scanner;

public class Riddles {

//attributes 
private Hashtable<String, String> riddleList; 
private Hashtable<String, String> answerList; 
Scanner Scan = new Scanner(System.in); 

//constructor 
public Riddles(){
    riddleList = new Hashtable<>();
    answerList = new Hashtable<>(); 
}

public Hashtable<String, String> getAnswerList() {
    return answerList;
}

public Hashtable<String, String> getRiddleList() {
    return riddleList;
}


}

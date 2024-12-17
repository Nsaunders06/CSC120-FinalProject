import java.util.Hashtable; 
import java.util.Scanner;

public class Riddles {

//attributes 
private Hashtable<String, String> riddleList; 
Scanner Scan = new Scanner(System.in); 

//constructor 
public Riddles(String question, String answer){
    //riddleList = new Hashtable< String, String>();
    riddleList.put(question, answer);
}

public Hashtable<String, String> getRiddleList() {
    return riddleList;
}

public String answerRiddle(Riddles riddle) {
    System.out.println("Please answer the following riddle: " + riddle);
    String answer = Scan.nextLine();
    return answer;
}

public boolean checkAnswer() {

    if (this.answerRiddle(null).contains(riddleList.get(this.answ))) {
        
    }

}

}

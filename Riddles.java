import java.util.Hashtable; 
import java.util.Scanner;

public class Riddles {

//attributes 
private Hashtable<String, String> riddleList; 
private Hashtable<String, String> answerList; 
Scanner Scan = new Scanner(System.in); 

//constructor 
public Riddles(){
    riddleList = new Hashtable<String, String>();
    answerList = new Hashtable<String, String>(); 
    addRiddles();
}

public Hashtable<String, String> getAnswerList() {
    return answerList;
}

public Hashtable<String, String> getRiddleList() {
    return riddleList;
}
private void addRiddles() {
    addRiddle(1, 1, "Riddle", "Answer");
    addRiddle(-1, -1, "Riddle", "Answer");
    addRiddle(0, 2, "Riddle", "Answer");
}

private void addRiddle(int x, int y, String riddle, String answer) {
    String key = x + "," + y;
    riddleList.put(key, riddle);
    answerList.put(key, answer);
}

public String getRiddleAt(int x, int y) {
    return riddleList.getOrDefault(x + "," + y, null);
}
public boolean checkAnswer(int x, int y, String answer) {
    String key = x + "," + y;
    return answerList.getOrDefault(key, "").equalsIgnoreCase(answer);
}

}

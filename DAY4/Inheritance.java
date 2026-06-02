import java.util.*;

class Store{
    String[] feedback={};
    public void viewAllFeedbacks(){
        System.out.println(Arrays.toString(feedback));
    }
}
class JavaBatch extends Store{
    String expertName="";
    public JavaBatch(int size){
        feedbacks=new String[size];
    }
    
    public void addFeedback(String comments){
        for(int index=0;index<feedbacks.length;index++){
            if(feedbacks[index]==null||feedbacks[index]==""){
                feedbacks[index]=comments;
                System.out.println("Feedback noted");
                return;
            }
            System.out.println("Buffer is full can't add your feedback");
        }
    }
    
}
public class Inheritance {
    public static void main(String[] args) {
        JavaBatch java =new JavaBatch(size:2);
        java.expertName="Razak Mohamed";
        java.addFeedBack("Expected Foundations not advance");
        java.addFeedBack("Need more time to write code");
        java.viewAllFeedbacks();
        SoftSkillbatch soft=new Softskillbatch();


        soft.read(9);
        soft.viewAllFeedbacks();
    }
}

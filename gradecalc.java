import java.util.Scanner;
class gradecalc{
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String studentName = scanner.nextLine();
        System.out.println("Enter your marks in 5 subjects");
        int[] marks = new int[5];
        int total=0;
        for(int i=0;i<=4;i++){
            marks[i]= scanner.nextInt(); 
            total+=marks[i];
        }
    
        //Average calculation
        
        double average = total/5;

        //Grade calculation
        char grade;
        if(average >=90){
            grade = 'A';
        }else if(average >=70 && average<90){
            grade = 'B';
        }else if(average>=60 && average<70 ){
            grade = 'C';
        }else if(average<=40 && average<60){
            grade = 'D';
        }else {
            grade = 'E';
        }
        //Displaying Results
        System.out.println("Displaying Results");
        System.out.println("Total marks : "+total);
        System.out.println("Average Percentage: " +average + "%");
        System.out.println("Grade: "+grade);
    }
}
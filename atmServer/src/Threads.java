
public class Threads{

private int number;
Threads(){
    number=0;
    }


public synchronized int getNumber(){
return number;
}

public synchronized void setNumber(int inNumber){
	number=inNumber;
}
}
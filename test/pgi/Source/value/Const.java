package value ;

public class Const extends  PGValue{
    int val ;

    public Const(int v){
	val = v ;
    }

    public  int GetVal() {return val;}

    public  String GetLabel() {return null;}
}

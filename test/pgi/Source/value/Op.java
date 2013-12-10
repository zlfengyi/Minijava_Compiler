package value ;

public class Op extends  PGValue{
    String name ;

    public Op(String v){
	name = v   ;
    }

    public  int GetVal() {return 0 ;}

    public  String GetLabel() {return name;}
}

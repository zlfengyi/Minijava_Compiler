package value ;

public class Label_pg extends  PGValue{
    String name ;

    public Label_pg(String v){
	name = v   ;
    }

    public  int GetVal() {return 0 ;}

    public  String GetLabel() {return name;}
}

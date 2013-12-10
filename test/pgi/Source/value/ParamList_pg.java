package value ;

public class ParamList_pg extends  PGValue{
    PGValue[] list ;
    int size ;

    public ParamList_pg(){
	list = new PGValue[20] ;
	size = 0 ;
    }

    public  int GetVal() {return size;}

    public  String GetLabel() {return null;}

    public void Insert(PGValue p) {
	list[size] = p ;
	size++;
    }

    public PGValue[] GetList(){
	PGValue[] aux_list = new PGValue[size] ;
	for(int i = 0 ; i < size ; i++)
	    aux_list[i] = list[i] ;
	return aux_list;
    }

    public PGValue GetFirst(){
	return list[0] ;
    }
}

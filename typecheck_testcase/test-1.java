class test107{
    public static void main(String[] a){
	   System.out.println(new A().hzz());
    }
}

class A extends B {
    public int hzz() {
        boolean f1;
        boolean f2;
        int ret;

        f1 = true;
        f2 = true;

        if (f1 && f2) {
            ret = 2;
        } else {
            ret = 0;
        }
        
        return ret;
    }
}

class B extends C  {

}

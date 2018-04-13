class Factorial{
    public static void main(String[] a) {
        System.out.println(new Fac().ComputeFac(10));
    }
}


class Fac2 {


    public int ComputeFac(int num) {
        int num_aux;
        if (num < 1)
            num_aux = 1;
        else
            num_aux = num * (this.ComputeFac(num - 1));
        return num_aux;
    }

}

class Fac1 extends Fac2 {


    public int ComputeFac(int num) {
        int num_aux;
        if (num < 1)
            num_aux = 1;
        else
            num_aux = num * (this.ComputeFac(num - 1));
        return num_aux;
    }

}

class Fac extends Fac1{
    int a;
    double ddd;
    int[] k;
    int num;

    public int ComputeFac(int num, int b, int[] c, double d,  int[] k) {

        Factorial t;
        double a;
        int num_aux;

        Fac tta;
        Fac3 ttaa;

        k[k[a + b+ k[k[k[a]]]] + k[b]] = k[k[a+b]];
        num = tta.ComputeFac();
        num = tta.ComputeFac2();
        num = new Fac3();
        a= b;
        b= a;
        b[tta]= a;

        while (new Fac())
            System.out.println(tta);

        if (num)
            num_aux = 1;
        else
            num_aux = num * (this.ComputeFac(num - 1));

        return d;
    }


}


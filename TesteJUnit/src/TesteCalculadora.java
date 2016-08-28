public class TesteCalculadora {
 
    public static void main(String[] a){
 
        Calculadora c = new Calculadora();
 
        int valorUm = 5;
        int valorDois = 5;
 
        double valorTotal = c.somar(valorUm, valorDois);
 
        if(valorTotal == 10){
            System.out.println("valor correto!");
        }
        else{
            System.out.println("valor errado!");
        }
    }
 
}

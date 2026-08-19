package br.com.cassio.eva;

import java.util.Scanner;

public class EntradaConsole {

    private final Scanner input = new Scanner(System.in);

    public String lerTexto(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();
            if (!entrada.isEmpty()){
                return entrada;
            }
            System.out.println("Entrada inválida. O texto não pode ficar vazio.");
        }
    }
    public double lerDoublePositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim().replace(",",".");

            try{
                double valor = Double.parseDouble(entrada);

                if(valor > 0){
                    return valor;
                }
                System.out.println("Valor invalido. Digite um número maior que zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número válido");
            }
        }
    }
    public int lerIntPositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            try{
                int valor = Integer.parseInt(entrada);

                if(valor > 0){
                    return valor;
                }

                System.out.println("Quantidade invalida. Digite um número maior que zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
        }
    }
    public int lerIntZeroOuPositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            try {
                int valor = Integer.parseInt(entrada);

                if(valor >= 0){
                    return valor;

                }

                System.out.println("Digite um número maior ou igual a zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
        }
    }

    private boolean isCancelar(String entrada){
        return entrada.equalsIgnoreCase("cancelar") || entrada.equals("0");
    }
    public String lerTextoOuCancelar(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            if(isCancelar(entrada)){
                return null;

            }

            if(!entrada.isEmpty()){
                return entrada;
            }

            System.out.println("Entrada inválida. O texto não pode ficar vazio.");

        }


    }
    public Double lerDoublePositivoOuCancelar(String mensagem){
        while (true) {
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            if(isCancelar(entrada)){
                return null;
            }

            entrada = entrada.replace(",", ".");

            try{
                double valor = Double.parseDouble(entrada);

                if (valor > 0){
                    return valor;
                }

                System.out.println("Valor inválido. Digite um número maior que zero.");


            }catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
    public Integer lerIntPositivoOuCancelar(String mensagem){
        while (true) {
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            if(isCancelar(entrada)){
                return null;
            }


            try{
                int valor = Integer.parseInt(entrada);

                if (valor > 0){
                    return valor;
                }

                System.out.println("Valor inválido. Digite um número maior que zero.");


            }catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
}

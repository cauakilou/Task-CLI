import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TaskList {
    public static void main(String[] args) {
       final Set<String> comandosValidos = new HashSet<>(List.of("add","sair","marktodo","list","delete","update","listdone","listtodo","markdone","markinprogress","listinprogress"));
        int existe;
        try {
            System.out.println("Seja bem vindo!!!, comece a adicionar suas tasks agora mesmo, escreva add e em seguida o que deseja adicionar");

            while (true) {
                Scanner s = new Scanner(System.in);
                try {
                    existe=0;
                    String a = s.nextLine();
                    String aLower = a.toLowerCase().replaceAll("\\s", "").trim();
                    for(String comparador:comandosValidos){
                        if (comparador.equals(aLower)){
                            existe++;
                        }
                    }
                    // Retorna ao começo caso o usuario não digite nada
                    if (existe==0){
                        System.out.println("digite um comando valido");
                    }


                    else if (aLower.subSequence(0,3).equals("add")&&existe>0) {
                        if (aLower.length() == 3) {
                            System.out.print(" adicione a descrição da tarefa ");
                            String desc = s.nextLine();
                            if (desc.isEmpty()) {
                                System.out.println("descrição invalida ");
                            } else {
                                Task.add(desc);

                            }
                        } else {
                            String desc = aLower.subSequence(3, aLower.length()).toString();
                            Task.add(desc);
                        }
                    }

                    //metodo para sair
                    else if (aLower.subSequence(0, 4).equals("sair")) {
                        System.out.println("Até logo, todos as tasks estão em Task_List.json");
                        break;
                    }

                    //metodo para chamar a lista geral
                    else if (aLower.subSequence(0, 4).equals("list") && aLower.length() == 4) {
                        Task.listall();
                    }

                    //metodo para chamar o delete
                    else if (aLower.subSequence(0, 6).equals("delete")) {
                        System.out.print(" escolha qual tarefa deseja apagar ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        Task.delete(desc);
                    }

                    // abaixo tem a operação de update
                    else if (aLower.subSequence(0, 6).equals("update")) {
                        System.out.print(" escolha qual tarefa deseja alterar ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        Task.update(desc);
                    }


                    // listagem de tarefas feitas
                    else if (aLower.subSequence(0, 8).equals("listdone")) {
                        Task.listDone();
                    }

                    // listagem de tarefas a fazer
                    else if (aLower.subSequence(0, 8).equals("listtodo")) {
                        Task.listToDo();
                    }

                    else if (aLower.subSequence(0, 8).equals("marktodo")) {
                        System.out.print(" escolha qual tarefa deseja colocar como a fazer ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        Task.markToDo(desc);

                    }


                    // marcar  tarefas como feitas
                    else if (aLower.subSequence(0, 8).equals("markdone")) {
                        System.out.print(" escolha qual tarefa deseja colocar como feita ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        Task.mark_done(desc);

                    }
                        //listagem de tarefas em progresso
                    else if (aLower.subSequence(0, 14).equals("listinprogress")) {
                        Task.listDoing();
                    }

                    //marcar tarefa como em progresso
                    else if (aLower.subSequence(0, 14).equals("markinprogress")) {
                        System.out.print(" escolha qual tarefa deseja colocar como fazendo ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        Task.mark_in_progress(desc);

                    }
                    else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("run Time Exception e");
                    throw new RuntimeException(e);
                }
            }
        } catch (RuntimeException e) {
            System.out.println("comando invalido");
            throw new RuntimeException(e);
        }

    }





}

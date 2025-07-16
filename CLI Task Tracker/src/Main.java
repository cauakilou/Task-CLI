import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Seja bem vindo!!!, comece a adicionar suas tasks agora mesmo, escreva add e em seguida o que deseja adicionar");

            while (true) {
                Scanner s = new Scanner(System.in);
                try {
                    String a = s.nextLine();

                    // Retorna ao começo caso o usuario não digite nada
                    if (a.isEmpty()) {
                        System.out.println("digite algo para começar ");
                    }
                    //metodos para adicionar a tarefa
                    else if (a.subSequence(0, 3).equals("add")) {
                        if (a.length() == 3) {
                            System.out.print(" adicione a descrição da tarefa ");
                            String desc = s.nextLine();
                            if (desc.isEmpty()) {
                                System.out.println("descrição invalida ");
                            } else {
                                Task.add(desc);

                            }
                        } else {
                            String desc = a.subSequence(4, a.length()).toString();
                            Task.add(desc);
                        }
                    }

                    //metodo para sair
                    else if (a.subSequence(0, 4).equals("sair")) {
                        System.out.println("Até logo, todos as tasks estão em Task_List.txt");
                        break;
                    }

                    //metodo para chamar a lista geral
                    else if (a.subSequence(0, 4).equals("list") && a.length() == 4) {
                        Task.listall();
                    }

                    //metodo para chamar o delete
                    else if (a.subSequence(0, 6).equals("delete")) {
                        System.out.print(" escolha qual tarefa deseja apagar ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        String id = String.valueOf(desc.charAt(0));
                        Task.delete(id);
                        System.out.println(" tarefa apagada");
                    }

                    // abaixo tem a operação de update
                    else if (a.subSequence(0, 6).equals("update")) {
                        System.out.print(" escolha qual tarefa deseja alterar ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        String id = String.valueOf(desc.charAt(0));
                        Task.update(id);
                        System.out.println(" tarefa atualizada");
                    }

                    // listagem de tarefas feitas
                    else if (a.subSequence(0, 8).equals("listdone") || a.subSequence(0, 9).equals("list done")) {
                        Task.listDone();
                    }

                    // listagem de tarefas a fazer
                    else if (a.subSequence(0, 8).equals("listtodo") || a.subSequence(0, 9).equals("list todo")) {
                        Task.listToDo();
                    }

                    // marcar  tarefas como feitas
                    else if (a.subSequence(0, 9).equals("mark done")) {
                        System.out.print(" escolha qual tarefa deseja colocar como feita ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        String id = String.valueOf(desc.charAt(0));
                        Task.mark_done(id);
                        System.out.println(" tarefa atualizada");
                    }

                    //listagem de tarefas em progresso
                    else if (a.subSequence(0, 14).equals("listinprogress") || a.subSequence(0, 16).equals("list in progress")) {
                        Task.listDoing();
                    }

                    //marcar tarefa como em progresso
                    else if (a.subSequence(0, 16).equals("mark in progress")) {
                        System.out.print(" escolha qual tarefa deseja colocar como fazendo ");
                        String desc = s.nextLine();
                        if (desc.isEmpty()) {
                            System.out.println("descrição invalida ");
                            continue;
                        }
                        String id = String.valueOf(desc.charAt(0));
                        Task.mark_in_progress(id);
                        System.out.println(" tarefa atualizada");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("a");
                    throw new RuntimeException(e);
                }
            }
        } catch (RuntimeException e) {
            System.out.println("comando invalido");
            throw new RuntimeException(e);
        }

    }
}

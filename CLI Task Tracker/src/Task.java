import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    public static int i=0;
    private final int id;
    private String desc;
    private enum status{TODO, DOING, DONE}
    private String created;
    private String update;
    private status alfa;
    public static ArrayList<Task> todoList = new ArrayList<>(100);

    public Task(int id, String update) {
        this.id = id;
        this.update = update;
        this.alfa = status.TODO;

    }

    private int getId() {
        return id;
    }

    public static void add(String desc) throws FileNotFoundException {
        String criado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy : hh:mm:ss"));
        if(todoList.size()>i) {
            while (todoList.size()>i && i == todoList.get(i).getId()) {
                ++i;
            }
        }
        Task a = new Task(i,criado);
        a.created = criado;
        a.desc = desc;
        todoList.add(i,a);
        System.out.format("Task added successfully (ID: %d) \n",a.id+1);

        //jonson abaixo
        try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.json"))) {
            jonson.println(todoList);
            jonson.flush();
        }

        i++;
    }

    public static void update(String idString) throws FileNotFoundException {
        //dd/MM/yyyy | hh:mm:ss
        if(idValido(idString)){
            int id = Integer.parseInt(idString);
            String criado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy : hh:mm:ss"));
            var a = todoList.get(id-1);
            todoList.remove(id-1);
            System.out.println("Digite a nova desc");
            var s = new Scanner(System.in);
            a.desc = s.nextLine();
            a.update = criado;
            todoList.add(id-1,a);

            try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.txt"))) {
                jonson.println(todoList);
                jonson.flush();
                System.out.println("Tarefa atualizada");
            }

        }
        else {
            System.out.println("ID INVALIDO");
        }
    }

    public static void delete(String idString) throws FileNotFoundException {
        if(idValido(idString)){
            var id = Integer.parseInt(idString);
            todoList.remove(id-1);
            i = id-1;
            try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.txt"))) {
                jonson.println(todoList);
                jonson.flush();
                System.out.println("Tarefa deletada");
            }
        }
        else {
            System.out.println("invalido");
        }
    }

    public static void mark_in_progress(String idString) throws FileNotFoundException {

        if(idValido(idString)) {
            var id = Integer.parseInt(idString);
            String criado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy : hh:mm:ss"));
            var a = todoList.get(id - 1);
            a.alfa = status.DOING;
            a.update = criado;
            todoList.remove(id - 1);
            todoList.add(id - 1, a);

            try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.txt"))) {
                jonson.println(todoList);
                jonson.flush();
                System.out.println(" tarefa atualizada");
            }
        }
        else {
            System.out.println("ID INVALIDO");
        }
    }

    public static void markToDo(String idString) throws FileNotFoundException {

        if(idValido(idString)) {
            var id = Integer.parseInt(idString);
            String criado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy : hh:mm:ss"));
            var a = todoList.get(id - 1);
            a.alfa = status.TODO;
            a.update = criado;
            todoList.remove(id - 1);
            todoList.add(id - 1, a);

            try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.txt"))) {
                jonson.println(todoList);
                jonson.flush();
                System.out.println(" tarefa atualizada");
            }
        }
        else {
            System.out.println("ID INVALIDO");
        }
    }

    public static void mark_done(String idString) throws FileNotFoundException {

        if (idValido(idString)) {
            int id = Integer.parseInt(idString);
            String criado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy | hh:mm:ss"));
            var a = todoList.get(id - 1);
            a.alfa = status.DONE;
            a.update = criado;
            todoList.remove(id - 1);
            todoList.add(id - 1, a);

            try (PrintWriter jonson = new PrintWriter(new FileOutputStream("Task_List.txt"))) {
                jonson.println(todoList);
                jonson.flush();
                System.out.println(" tarefa atualizada");
            }
        }
        else {
            System.out.println("ID INVALIDO");
        }
    }

    public static void listall(){
        for (Task a : todoList) {
            String status = (a.alfa.toString());

            System.out.format("task ID| %3d |%14s |status: %6s |%s |%s \n", a.id + 1, a.desc, status, a.created, a.update);
        }
        System.out.println("------");
    }

    public static void listToDo(){
        for (Task a : todoList) {
            if (a.alfa.equals(status.TODO)) {
                String status = (a.alfa.toString());

                System.out.format("task ID| %3d |%14s |status: %6s |%s |%s \n", a.id + 1, a.desc, status, a.created, a.update);

            }
        }
        System.out.println("------");
    }

    public static void listDoing(){
        for (Task a : todoList) {
            if (a.alfa.equals(status.DOING)) {
                String status = (a.alfa.toString());

                System.out.format("task ID| %3d |%14s |status: %6s  %s %s\n", a.id + 1, a.desc, status, a.created, a.update);

            }
        }
        System.out.println("------");
    }

    public static void listDone(){
        for (Task a : todoList) {
            if (a.alfa.equals(status.DONE)) {
                String status = (a.alfa.toString());

                System.out.format("task ID| %3d |%14s |status: %6s %s %s \n", a.id + 1, a.desc, status, a.created, a.update);

            }
        }
        System.out.println("------");
    }

    public static boolean idValido(String alfa){
        try {
            double alfaDouble = Double.parseDouble(alfa);
            if (alfaDouble%1!=0){
                return false;
            }
            var id = Integer.parseInt(alfa);
            for (Task task : todoList) {
                if (task.getId() == id-1) {
                    return true;
                }
            }
        }catch (Exception e ){
            System.out.println("!");
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("task ID: %3d " +
                "desc:%14s " +
                "status: %6s " +
                "criado em:%s " +
                "ultima atualização %s \n", id + 1, desc, alfa, created, update);
    }

}


package br.edu.ifnmg.data_persistencia;

import java.time.LocalDate;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.credential.CredentialDao;
import br.edu.ifnmg.librarian.Librarian;
import br.edu.ifnmg.librarian.LibrarianDao;
import br.edu.ifnmg.reader.Reader;
import br.edu.ifnmg.reader.ReaderDao;
import br.edu.ifnmg.role.Role;
import br.edu.ifnmg.role.RoleDao;
import br.edu.ifnmg.user.User;
import java.util.ArrayList;

public class Tests {
       public static void CasoA() {
        System.out.println("TEST A");
        try {
            Role role = new Role("Bibliotecario");
            Long role_id = new RoleDao().saveOrUpdate(role);
            role.setId(role_id);
            
            Credential cred1 = new Credential(null, "ana123", "3456", LocalDate.now(), null, null);
            
            Librarian ana = null;
            ana = new Librarian("ana", "ana_maria_das_bragas@email.com", LocalDate.now(), role, cred1);
    
            cred1.setUser(ana);
            Long user_id = new LibrarianDao().saveOrUpdate(ana);
            
            Librarian L = null;
            L = new LibrarianDao().findById(user_id);
            L.setEmail("ana@email.com");
            L.setBirthDate(LocalDate.of(1999, 01, 30));
            new LibrarianDao().saveOrUpdate(L);

            System.out.println("Bibliotecário Cadastrado!!");
            System.out.println(L.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void CasoB() {
        System.out.println("\nTEST B");
        try {
            Role role = new Role("Leitor");
            Long role_id = new RoleDao().saveOrUpdate(role);
            role.setId(role_id);

            Reader joao = null;
            joao = new Reader(
                    "joao",
                    "joaoK@email.com",
                    LocalDate.now(),
                    role,
                    new Credential(null, "JK", "4321", LocalDate.now(), true, joao));
            joao.getCredential().setUser(joao);
            Long user_id = new ReaderDao().saveOrUpdate(joao);

            joao = new ReaderDao().findById(user_id);
            joao.setEmail("jk2@email.com");
            joao.setBirthDate(LocalDate.of(2003, 05, 06));
            new ReaderDao().saveOrUpdate(joao);

            System.out.println("Leitor Cadastrado!!");
            System.out.println(joao.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void CasoC() {
        System.out.println("\nTEST C");
        try {
            Role role = new Role("Leitor");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Reader red1 = null;
            red1 = new Reader(
                    "Luiz",
                    "Luiz@email.com",
                    LocalDate.now(),
                    role,
                    new Credential(null, "LH", "qwer", LocalDate.now(), true, red1));
            red1.getCredential().setUser(red1);
            Long userId = new ReaderDao().saveOrUpdate(red1);

            red1 = new ReaderDao().findById(userId);
            red1.setEmail("LH@email.com");
            red1.setBirthDate(LocalDate.of(2004, 04, 30));
            new ReaderDao().saveOrUpdate(red1);

            System.out.println("Leitor Cadastrado!!");
            System.out.println(red1.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void CasoD() {
        System.out.println("\nTEST D");
        ArrayList<Librarian> allObjects = null;

        try {
            allObjects = new LibrarianDao().findAll();
            System.out.println("Bibliotecários: ");
            for (var x : allObjects) {
                System.out.println(x.getName());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void CasoE() {
        System.out.println("\nTEST E");
        ArrayList<Reader> allObjects = null;

        try {
            allObjects = new ReaderDao().findAll();
            System.out.println("Leitores: ");
            for (var x : allObjects) {
                System.out.println(x.getName());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void CasoF() {
        System.out.println("\nTEST F");
        try {
            Credential test = new Credential();
            test.setUsername("ana123");
            test.setPassword("3456");

            User user = new CredentialDao().authenticate(test);
            user.setCredential(new CredentialDao().findById(user.getId()));
            System.out.println(user.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
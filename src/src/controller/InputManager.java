package src.controller;

import src.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer decisionBranch() {
        System.out.println("\n O que deseja fazer?\n");
        Integer next = null;
        System.out.println("1. Ver tabela");
        System.out.println("2. Marcar um horário");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next != 1 && next != 2) {
                    next = null;
                    System.out.println("Preencha corretamente com número 1 ou 2.\n");
                }
            } catch (Exception e) {
                System.out.println("Preencha corretamente com números.\n");
            }
        }
        return next;
    }

    public static Integer visualize() {
        Integer next = null;
        System.out.println("\n Qual tabela deseja ver? \n");
        System.out.println("1. Doutores");
        System.out.println("2. Salas de cirurgia");
        System.out.println("3. Todos os horários e preços");
        System.out.println("4. Todos horários dentre um intervalo de tempo");
        System.out.println("5. Todas as reservas");
        System.out.println("6. Gastos de cada doutor");
        System.out.println("7. Gastos de cada sala de cirurgia");
        System.out.println("8. Sair");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next < 1 || next > 8) { next = null; }
                if (next == 8) {
                    System.out.println("Saindo...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Preencha corretamente com números.\n");
            }
        }
        return next;
    }

    public static Integer interactWith() {
        Integer next = null;
        System.out.println("1. Marcar horário");
        System.out.println("2. Cancelar horário");
        System.out.println("3. Exit");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next < 1 || next > 8) { next = null; }
                if (next == 3) {
                    System.out.println("Saindo...\n");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Preencha corretamente com números.\n");
            }
        }
        return next;
    }

    public static Allocation createAllocation(List <Doctor> doctorList, List <SurgeryRoom> surgeryRooms) {
        Doctor doctor = doctorSelection(doctorList);
        if (verifyObject(doctor)) { return null; }
        System.out.println("Escolha uma sala: ");
        SurgeryRoom room = roomSelection(
                surgeryRooms
                        .stream()
                        .filter( r -> doctor.getSpecialization() == Specialization.dermatologist ?
                                (r.getType() == SurgeryRoomType.small) :
                                (r.getType() == SurgeryRoomType.big || r.getType() == SurgeryRoomType.high_risk))
                        .collect(Collectors.toList())
        );
        if (verifyObject(room)) { return null; }

        System.out.println("Escolha um intervalo de tempo:");
        Period period = createPeriod();

        System.out.println("Pedido de reserva criado. Verificando o banco de dados para confirmar...");
        return (new Allocation(doctor, room, period));
    }

    public static boolean deleteReservation(List<Allocation> allocations) {
        List <Allocation> reservations = reservations(allocations);
        if (reservations.isEmpty()) {
            System.out.println("Não possui horário.");
            return false;
        }
        int exitIndex = reservations.size() + 1;
        int index = -1;
        System.out.println("Selecione algum para deletar: ");
        while (index < 1 || index > exitIndex) {
            for (int i = 1; i < exitIndex; i++) {
                System.out.println(i + ". " + reservations.get(i-1));
            }
            System.out.println(exitIndex + ". Sair");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return false; }
        return allocations.remove(reservations.get(index-1));
    }

    public static List<Allocation> reservations(List<Allocation> allocations) {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return allocations
                .stream()
                .filter( allocation -> allocation.getPeriod().day > day )
                .collect(Collectors.toList());
    }

    private static boolean verifyObject(Object obj) {
        if (obj == null) {
            System.out.println("Saindo...");
            return true;
        }
        return false;
    }

    private static Doctor doctorSelection(List <Doctor> doctorList) {
        int index = -1;
        int exitIndex = doctorList.size()+1;
        while (index < 1 || index > exitIndex) {
            System.out.println("Escolha um doutor:");
            for (int i = 1; i <= doctorList.size(); i++) {
                System.out.println(i + ". " + doctorList.get(i-1));
            }
            System.out.println(exitIndex + ". Sair");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return null; }
        return doctorList.get(index-1);
    }

    private static SurgeryRoom roomSelection(List <SurgeryRoom> surgeryRooms) {
        int index = -1;
        int exitIndex = surgeryRooms.size()+1;
        while (index < 1 || index > exitIndex) {
            System.out.println("Selecione um doutor");
            for (int i = 1; i <= surgeryRooms.size(); i++) {
                System.out.println(i + ". " + surgeryRooms.get(i-1));
            }
            System.out.println(exitIndex + ". Sair");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return null; }
        return surgeryRooms.get(index-1);
    }

    private static Period createPeriod() {
        int day = -1, startHour = -1, endHour = -1;
        System.out.println("Informe o dia:");
        while (day < 0) {
            day = scanner.nextInt();
            System.out.println();
        }
        System.out.println("Informe a hora de inicio: ");
        while (startHour < 6 || startHour > 20) {
            startHour = scanner.nextInt();
            System.out.println();
        }
        System.out.println("Informe a hora de término: ");
        while (endHour < 8 || endHour > 22) {
            endHour = scanner.nextInt();
            System.out.println();
        }
        if (endHour < startHour) {
            System.out.println("A hora de inicio não pode ser depois da hora de término. Preencha corretamente.");
            return createPeriod();
        }
        return (new Period(day, startHour, endHour));
    }

    public static int[] days() {
        System.out.println("\nInforme o primeiro dia");
        int d1 = scanner.nextInt();
        System.out.println("\nInforme o segundo dia");
        int d2 = scanner.nextInt();
        return (new int[] {Math.min(d1, d2), Math.max(d1, d2)});
    }

}

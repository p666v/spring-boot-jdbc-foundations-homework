package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final UserService userService;
    private final PetService petService;
    private final IOService ioService;


    @Override
    public void start() {
        System.out.println("Добро пожаловать в клуб любителей домашних животных");
        while (true) {
            System.out.println("Введите номер меню");
            printMenu();
            int menuNum = ioService.inputInt();
            if (menuNum == 1) {
                printAllUsers();
            } else if (menuNum == 2) {
                insertUser();
            } else if (menuNum == 3) {
                printAllPets();
            } else if (menuNum == 4) {
                insertPet();
            } else {
                System.exit(0);
            }

        }
    }

    @Override
    public void insertUser() {
        System.out.println("Добавление нового участника клуба:");
        System.out.println("Введите ИМЯ");
        String name = ioService.input();
        System.out.println("Введите ВОЗРАСТ");
        int age = ioService.inputInt();
        System.out.println("Введите питомца из списка:");
        printAllPets();
        String breed = ioService.input();
        Pet pet = petService.findByBreed(breed);

        User user = new User(name, age, pet);
        userService.create(user);
    }

    @Override
    public void insertPet() {
        System.out.println("Добавление нового питомца:");
        System.out.println("Введите ПИТОМЦА");
        String breed = ioService.input();

        Pet pet = new Pet(breed);
        petService.create(pet);
    }

    @Override
    public void printMenu() {
        System.out.println("Меню:\n" +
                "1. Вывести всех участников клуба\n" +
                "2. Добавить участника\n" +
                "3. Вывести всех домашних животных в клубе\n" +
                "4. Добавить домашнего питомца\n" +
                "Остальное выход\n");
    }

    @Override
    public void printAllPets() {
        List<Pet> petList = petService.printAllPets();
        System.out.println("Список питомцев:");
        for (Pet pets : petList) {
            System.out.println(pets);
        }
    }

    @Override
    public void printAllUsers() {
        List<User> userList = userService.printAllUsers();
        System.out.println("Список студентов:");
        for (User users : userList) {
            System.out.println(users);
        }
    }
}

package ru.ranepa;

import ru.ranepa.model.Employee;
import ru.ranepa.presentation.Menu;
import ru.ranepa.repository.EmployeeRepository;
import ru.ranepa.service.HRMService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HrmApplication {

    public static void main(String[] args) {
        // Создание репозитория для хранения данных
        EmployeeRepository repository = new EmployeeRepository();
        // Добавление тестовых сотрудников для демонстрации работы программы
        addTestEmployees(repository);
        // Создание сервиса с бизнес-логикой
        HRMService service = new HRMService(repository);
        // Создание и запуск консольного меню
        Menu menu = new Menu(service);
        menu.start();
    }

    // Добавляет тестовых сотрудников в репозиторий
    private static void addTestEmployees(EmployeeRepository repository) {
            repository.save(new Employee(null, "Sasha Kim", "Manager",
                    BigDecimal.valueOf(150000), LocalDate.of(2022, 1, 1)));
            repository.save(new Employee(null, "Maria Ivanova", "Tester",
                    BigDecimal.valueOf(80000), LocalDate.of(2024, 1, 1)));
            repository.save(new Employee(null, "Ivan Petrov", "Developer",
                    BigDecimal.valueOf(100000), LocalDate.of(2023, 1, 15)));
            repository.save(new Employee(null, "Elizaveta Podobed", "Developer",
                    BigDecimal.valueOf(120000), LocalDate.of(2023, 8, 1)));
    }
}
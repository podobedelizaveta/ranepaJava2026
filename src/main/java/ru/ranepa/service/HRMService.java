package ru.ranepa.service;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Сервисный слой приложения
// Содержит: расчеты, фильтрацию, поиск
public class HRMService {
    // Репозиторий для доступа к данным
    private final EmployeeRepository repository;

    // Репозиторий для работы с данными
    public HRMService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Добавляет нового сотрудника
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Возвращает список всех сотрудников
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Ищет сотрудника по ID
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    // Удаляет сотрудника по ID
    public boolean deleteEmployee(Long id) {
        return repository.delete(id);
    }

    // Рассчитывает среднюю зарплату по всем сотрудникам
    public double getAverageSalary() {
        return repository.findAll().stream()
                .mapToDouble(e -> e.getSalary().doubleValue())
                .average()
                .orElse(0.0);
    }

    // Находит сотрудника с самой высокой зарплатой
    public Optional<Employee> findHighestPaidEmployee() {
        return repository.findAll().stream()
                .max(Comparator.comparing(e -> e.getSalary().doubleValue()));
    }

    // Фильтрует сотрудников по должности
    public List<Employee> filterByPosition(String position) {
        return repository.findAll().stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    // Сортирует сотрудников по дате приема (от старых к новым)
    public List<Employee> sortByHireDate() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getHireDate))
                .collect(Collectors.toList());
    }

    // Сортирует сотрудников по имени (в алфавитном порядке)
    public List<Employee> sortByName() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
    }
}
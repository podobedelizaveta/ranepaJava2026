package ru.ranepa.repository;

import ru.ranepa.model.Employee;
import java.util.*;

public class EmployeeRepository {

    // Хранилище сотрудников: ключ - ID, значение - объект Employee
    private final Map<Long, Employee> employees = new HashMap<>();

    // Счетчик для генерации новых ID
    private long nextId = 1;

    // Сохраняет сотрудника в репозиторий
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(nextId++);
        }
        employees.put(employee.getId(), employee);
        return employee;
    }

    // Возвращает список всех сотрудников
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    // Ищет сотрудника по ID
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employees.get(id));
    }

    // Удаляет сотрудника по ID
    public boolean delete(Long id) {
        return employees.remove(id) != null;
    }

    // Проверяет существование сотрудника по ID
    public boolean existsById(Long id) {
        return employees.containsKey(id);
    }

    // Возвращает количество сотрудников
    public int count() {
        return employees.size();
    }
}
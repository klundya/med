package part1.part1_1;

/**
 * Задание 1.1 — Банковский счёт
 *
 * Тема: статические и экземплярные блоки инициализации, инкапсуляция.
 *
 * Ключевая теория:
 *   - static { ... } выполняется ОДИН РАЗ при загрузке класса (после статических полей, до new).
 *   - { ... } (блок экземпляра) выполняется при каждом new, после явной инициализации полей в объявлении, ДО тела конструктора.
 *   - Порядок при new: блок экземпляра → конструктор (статическая часть класса уже выполнена один раз).
 *   - static-поле принадлежит классу, а не объекту (общее для всех экземпляров).
 *   - Для форматирования double: String.format("%.2f", value)
 *
 * Как запустить: нажмите ▶ рядом с main.
 *
 * Ожидаемый вывод:
 *   Банковская система инициализирована
 *   Создание счёта #1
 *   Создание счёта #2
 *   [ACC-1] Алиса: 1000.00 руб.
 *   [ACC-2] Борис: 500.00 руб.
 *   После пополнения: [ACC-1] Алиса: 1500.00 руб.
 *   После снятия: [ACC-1] Алиса: 1300.00 руб.
 *   Ошибка: недостаточно средств
 *   Ошибка: сумма должна быть положительной
 *   Всего счетов: 2
 */
public class BankAccount {

    private String owner;
    private double balance;
    private String accountNumber;   // формат: "ACC-1", "ACC-2" и т.д.

    private static int totalAccounts;
    private static String bankName;

    static {
        // TODO: bankName = "Java Bank"; выведите "Банковская система инициализирована"
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        bankName = "Java Bank";
        System.out.println("Banking system initialized");
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    {
        // TODO: totalAccounts++; выведите "Создание счёта #" + totalAccounts
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        totalAccounts++;
        System.out.println("Creating account #" + totalAccounts);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Подсказка: к моменту конструктора блок экземпляра уже увеличил totalAccounts.
     * accountNumber = "ACC-" + totalAccounts
     */
    public BankAccount(String owner, double initialBalance) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.owner = owner;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + totalAccounts;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Если amount <= 0 — сообщение об ошибке и выход без изменения баланса.
     */
    public void deposit(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        if (amount <= 0) {
            System.out.println("Error: amount must be positive");
            return;
        }
        balance += amount;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Если amount <= 0 или balance < amount — соответствующее сообщение, без снятия.
     * Иначе уменьшите balance.
     */
    public void withdraw(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        if (amount <= 0) {
            System.out.println("Error: amount must be positive");
            return;
        }
        if (balance < amount) {
            System.out.println("Error: insufficient funds");
            return;
        }
        balance -= amount;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public static int getTotalAccounts() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return totalAccounts;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Формат: "[ACC-1] Алиса: 1500.00 руб."
     * Подсказка: String.format("[%s] %s: %.2f руб.", accountNumber, owner, balance)
     */
    @Override
    public String toString() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return String.format("[%s] %s: %.2f RUB.", accountNumber, owner, balance);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Alice", 1000);
        BankAccount a2 = new BankAccount("Boris", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("After deposit: " + a1);

        a1.withdraw(200);
        System.out.println("After withdrawal: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
    }
}
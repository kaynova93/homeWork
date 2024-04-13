# language: ru

@TestDB
@all
Функция: Проверка базы данных

  Предыстория:
    * Подключение к базе данных

  @Correct
  Сценарий: Запрос всех данных из таблицы FOOD
    * Получить данные в БД
    * Отключаем соединение с БД

  @Correct
  Сценарий: Проверка удаления данных из таблицы FOOD
    * Добавить запись в таблцу "Груша" "Фрукт" 0
    * Проверить, что последняя запись имеет данные "Груша" "Фрукт" 0 и удалить эту запись
    * Отключаем соединение с БД

  @Correct
  Структура сценария:  Добавление продуктов в БД
    * Добавить продукт в базу данных :
      | <name> | <type> | <exotic> |
    * Отключаем соединение с БД

    Примеры:
      | name           | type  | exotic |
      | Груша          | Фрукт | 0      |
      | Свекла         | Овощ  | 0      |
      | Синяя кукуруза | Овощ  | 1      |
      | Гуава          | Фрукт | 1      |
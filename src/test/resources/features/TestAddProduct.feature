# language: ru

@UI
@all
Функция: Добавление продуктов. UI

  Предыстория:
    * Подключить настройки для UI

  @Correct
  Сценарий: Проверка элементов в модальном окне
    Когда Кнопка добавить видна
    То Нажать кнопку добавить
    И Открылось модальное окно
    Тогда Поля видны
    Тогда Заголовки полей видны
    * Закрыть подключение для UI

  @Correct
  Сценарий: Проверка списка тип продукта
    Когда Кнопка добавить видна
    То Нажать кнопку добавить
    И Открылось модальное окно
    И Поля видны
    Тогда Открыть список тип продукта
    Тогда Проверить значения в списке "Фрукт" , "Овощ"
    * Закрыть подключение для UI

  @Correct
  Структура сценария: Валидация поля Наименование
    Когда Кнопка добавить видна
    То Нажать кнопку добавить
    И Открылось модальное окно
    И Поля видны
    Тогда Заполнить поле Наименование
      | <name> |
    Когда Кнопка сохранить видна
    То Нажать на кнопку сохранить
    Тогда Проверить наименование добавленного продукта в таблице
      | <name> |
    * Закрыть подключение для UI

    Примеры:
      | name                                                                                                      |
      | Яблоко                                                                                                    |
      | ГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГрушаГруша |
      | 1234567890                                                                                                |
      | Pear                                                                                                      |

  @Correct
  Структура сценария: Добавить продукт
    Когда Кнопка добавить видна
    То Нажать кнопку добавить
    И Открылось модальное окно
    И Поля видны
    Тогда Заполнить поле Наименование
      | <name> |
    Тогда Открыть список тип продукта
    Тогда Выбрать тип продукта
      | <type> |
    Тогда Проставить чекбокс
      | <exotic> |
    Когда Кнопка сохранить видна
    То Нажать на кнопку сохранить
    Тогда Проверить добавленный продукт в таблице
      | <name> | <type> | <exotic> |
    * Закрыть подключение для UI
    Примеры:
      | name           | type  | exotic |
      | Груша          | Фрукт | false  |
      | Свекла         | Овощ  | false  |
      | Синяя кукуруза | Овощ  | true   |
      | Гуава          | Фрукт | true   |
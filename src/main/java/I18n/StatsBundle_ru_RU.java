package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                { "Boolean", new String( "{0, choice, 0#нет | 1#да }")},
                { "Integer", new String( "{0}")},
                { "String", new String( "{0}")},
                { "LegSizeEnum", new String( "{0, choice, 0#Маленькие | 1#Средние| 2#Большие}")},

                { "true", new String( "да")},
                { "false", new String( "нет")},

                { "Small", new String( "Маленькие")},
                { "Medium", new String( "Средние")},
                { "Big", new String( "Большие")},

                { "Success", new String("Успешно!") },

                { "FileNotFound", new String("Файл не найден") },
                { "InputJSON", new String("Введите в поле ввода элеенты в json и убедитесь в наличии соотвествующего файла и установки переменной среды окружения ReadFileDir") },
                { "CannotCreateObject", new String("Невозможно создать объект класса. Возмжно, класс не имеет конструктора без параметров. Убедитесь в корректности файла") },
                { "AccessObjectDeny", new String("Невозможно создать объект класса. Возмжно, класс не имеет конструктора без параметров. Убедитесь в корректности файла") },
                { "ClassNotFound", new String("Класс не найден. Объекты данного класа не поддерживаются программой!") },
                { "UnknownException", new String("Неопознанная ошибка") },
                { "InputParams", new String("Введите в поле ввода параметров название класса") },
                { "Anons", new String("Анонс!") },


                { "File", new String("Файл!") },
                { "Name", new String("Имя") },
                { "LegCount", new String("Кол-во ног") },
                { "LegIndex", new String("Просматриваемая нога") },
                { "LegSize", new String("Размер ноги") },
                { "LegWashed", new String("Вымыт-3ость ноги") },
                { "LegBarefoot", new String("Обутость ноги") },
                { "LocationName", new String("Название местоположения") },
                { "Came", new String("Приход") },
                { "Wait", new String("Ожидание") },
                { "Delete", new String("УДАЛИТЬ!") },
                { "Actions", new String("Действие") },
                { "SelecetCommand", new String("Выберите команду") },
                { "Execute", new String("Выполнить") },

                { "MenuCame", new String("Фильтровать") },
                { "MenuWait", new String("Фильтровать") },
                { "MenuActions", new String("Фильтровать") },

                { "ButtonDelete", new String("Фильтровать") },

                { "Filter", new String("Фильтровать") },

                { "FilterChoose", new String("Выберите фильтры") },
                { "FilterName", new String("Имя") },
                { "FilterLocation", new String("Местоположение") },
                { "FilterLegCount", new String("Кол-во ног") },
                { "FilterSearchForAll", new String("Полные совпадения") },


                { "FilterLegSize", new String("Размер ног") },

                { "FilterBigLeg", new String("Большие") },
                { "FilterMiddleLeg", new String("Средние") },
                { "FilterSmallLeg", new String("Маленькие") },


                { "FilterLegWashed", new String("Вымытость ног") },

                { "FilterIsWashedTrue", new String("Вымыты") },
                { "FilterIsWashedFalse", new String("Не вымыты") },


                { "FilterLegBarefoot", new String("Обутость ног") },

                { "FilterIsBarefootTrue", new String("Обуты") },
                { "FilterIsBarefootFalse", new String("Разуты") },


                { "FilterCame", new String("Приход") },

                { "FilterIsCameTrue", new String("Произошёл") },
                { "FilterIsCameFalse", new String("Не произошёл") },


                { "FilterWait", new String("Ожидание") },

                { "FilterIsWaitTrue", new String("Ждёт") },
                { "FilterIsWaitFalse", new String("Не ждёт") }
        };
    }
}
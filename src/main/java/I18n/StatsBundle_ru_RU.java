package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                { "Boolean", new String( "{0, choice, 0#no | 1#yes }")},
                { "Integer", new String( "{0}")},
                { "String", new String( "{0}")},

                { "File", new String("Файл!") },
                { "Name", new String("Имя") },
                { "LegCount", new String("Кол-во ног") },
                { "LegIndex", new String("Просматриваемая нога") },
                { "LegSize", new String("Размер ноги") },
                { "LegWashed", new String("Вымытость ноги") },
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
                { "FilterIsWaitFalse", new String("Не ждёт") },
        };
    }
}
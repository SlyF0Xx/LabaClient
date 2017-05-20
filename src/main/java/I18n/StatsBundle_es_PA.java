package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_es_PA extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
       return new Object[][]{
                { "Boolean", new String( "{0, choice, 0#no | 1#sí }")},
                { "Integer", new String( "{0}")},
                { "String", new String( "{0}")},

                { "File", new String("archivo") },
                { "Name", new String("nombre") },
                { "LegCount", new String("Número de patas") },
                { "LegIndex", new String("Pierna actual") },
                { "LegSize", new String("tamaño del zapato") },
                { "LegWashed", new String("Limpieza de los pies") },
                { "LegBarefoot", new String("Disponibilidad de calzado") },
                { "LocationName", new String("Topónimo") },
                { "Came", new String("la venida") },
                { "Wait", new String("Espera") },
                { "Delete", new String("borrar") },
                { "Actions", new String("acción") },
                { "SelecetCommand", new String("Elegir comando") },
                { "Execute", new String("Ejecutar") },

                { "MenuCame", new String("Фильтровать") },
                { "MenuWait", new String("Фильтровать") },
                { "MenuActions", new String("Фильтровать") },

                { "ButtonDelete", new String("Фильтровать") },

                { "Filter", new String("Фильтровать") },

                { "FilterChoose", new String("Elija los filtros") },
                { "FilterName", new String("nombre") },
                { "FilterLocation", new String("ubicación") },
                { "FilterLegCount", new String("Número de pies") },
                { "FilterSearchForAll", new String("Concurrencias exactas") },


                { "FilterLegSize", new String("tamaño del zapato") },

                { "FilterBigLeg", new String("grande") },
                { "FilterMiddleLeg", new String("medio") },
                { "FilterSmallLeg", new String("pequeña") },


                { "FilterLegWashed", new String("Limpieza de los pies") },

                { "FilterIsWashedTrue", new String("limpio") },
                { "FilterIsWashedFalse", new String("sucio") },


                { "FilterLegBarefoot", new String("Disponibilidad de calzad") },

                { "FilterIsBarefootTrue", new String("tiene") },
                { "FilterIsBarefootFalse", new String("No tiene") },


                { "FilterCame", new String("La venida") },

                { "FilterIsCameTrue", new String("llegado") },
                { "FilterIsCameFalse", new String("No llegó") },


                { "FilterWait", new String("Espera") },

                { "FilterIsWaitTrue", new String("Eso espera") },
                { "FilterIsWaitFalse", new String("Eso no espera") },
        };
    }
}